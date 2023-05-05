package com.sudo248.cartservice.service.impl;

import com.sudo248.cartservice.controller.dto.AddSupplierProductDto;
import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.internal.DiscoveryService;
import com.sudo248.cartservice.repository.CartRepository;
import com.sudo248.cartservice.repository.CartSupplierProductRepository;
import com.sudo248.cartservice.repository.entity.Cart;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import com.sudo248.cartservice.repository.entity.SupplierProductKey;
import com.sudo248.cartservice.service.CartService;
import com.sudo248.cartservice.service.CartSupplierProductService;
import com.sudo248.domain.util.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartSupplierProductServiceImpl implements CartSupplierProductService {
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartSupplierProductRepository cartSupplierProductRepository;
    private final DiscoveryService discoveryService;

    public CartSupplierProductServiceImpl(CartService cartService, CartRepository cartRepository, CartSupplierProductRepository cartSupplierProductRepository, DiscoveryService discoveryService) {
        this.cartService = cartService;
        this.cartRepository = cartRepository;
        this.cartSupplierProductRepository = cartSupplierProductRepository;
        this.discoveryService = discoveryService;
    }

    @Override
    public CartDto addSupplierProductToCart(String userId, AddSupplierProductDto addSupplierProductDto) {
        Cart cart = getActiveCart(userId);
        SupplierProductKey supplierProductKey = new SupplierProductKey(addSupplierProductDto.getProductId(), addSupplierProductDto.getSupplierId(), cart.getCartId());
        Optional<CartSupplierProduct> optionalCartSupplierProduct = cartSupplierProductRepository.findById(supplierProductKey);
        CartSupplierProduct cartSupplierProduct;
        double totalPrice = cart.getTotalPrice();
        int totalAmount = cart.getTotalAmount();
        if (optionalCartSupplierProduct.isPresent()) {
            cartSupplierProduct = optionalCartSupplierProduct.get();
            cartSupplierProduct.setAmount(cartSupplierProduct.getAmount() + addSupplierProductDto.getAmount());
            double price = getTotalPriceBySupplierProduct(addSupplierProductDto);
            cartSupplierProduct.setTotalPrice(cartSupplierProduct.getTotalPrice() + price);
            totalAmount += addSupplierProductDto.getAmount();
            totalPrice += price;
        } else {
            cartSupplierProduct = new CartSupplierProduct(
                    supplierProductKey,
                    addSupplierProductDto.getAmount(),
                    getTotalPriceBySupplierProduct(addSupplierProductDto),
                    cart
            );
            totalAmount += addSupplierProductDto.getAmount();
            totalPrice += cartSupplierProduct.getTotalPrice();
        }
        cartSupplierProductRepository.save(cartSupplierProduct);

        List<CartSupplierProduct> supplierProductList = new ArrayList<>();

        if (cart.getCartSupplierProducts() != null)
            supplierProductList = cart.getCartSupplierProducts();
        supplierProductList.add(cartSupplierProduct);

        cart.setCartSupplierProducts(supplierProductList);

        cart.setTotalPrice(totalPrice);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

        return new CartDto(
                cart.getCartId(),
                cart.getTotalPrice(),
                cart.getTotalAmount(),
                cart.getStatus(),
                cartService.getSupplierProduct(userId, cart.getCartId(), cart.getCartSupplierProducts(), false)
        );
    }

    private Cart getActiveCart(String userId) {
        try {
            Cart cart = cartRepository.findByUserIdAndStatus(userId, "active");
            if (cart == null) {
                throw new Exception("Not found cart for user " + userId);
            }
            return cart;
        } catch (Exception e) {
            return createNewCart(userId);
        }
    }

    private Cart createNewCart(String userId) {
        Cart cart = new Cart();
        cart.setCartId(Utils.createId());
        cart.setUserId(userId);
        cart.setStatus("active");
        cart.setTotalAmount(0);
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public CartDto updateSupplierProductToCart(String userId, String cartId, List<AddSupplierProductDto> addSupplierProductDtoList) {
        Cart cart = cartRepository.getReferenceById(cartId);

        double totalPrice = cart.getTotalPrice();
        int totalAmount = cart.getTotalAmount();

        for (AddSupplierProductDto addSupplierProductDto : addSupplierProductDtoList) {

            int index = getCartSupplierProductIndex(cart.getCartSupplierProducts(), addSupplierProductDto);

            CartSupplierProduct cartSupplierProduct = cart.getCartSupplierProducts().get(index);

            int currentAmount = cartSupplierProduct.getAmount();
            double currentPrice = getPriceBySupplierProduct(addSupplierProductDto);

            int updateAmount = addSupplierProductDto.getAmount();

            cartSupplierProduct.setAmount(updateAmount);
            cartSupplierProduct.setTotalPrice(updateAmount * currentPrice);

            totalAmount += (updateAmount - currentAmount);
            totalPrice += (updateAmount - currentAmount) * currentPrice;

            cart.getCartSupplierProducts().set(index, cartSupplierProduct);
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
        return new CartDto(
                cart.getCartId(),
                cart.getTotalPrice(),
                cart.getTotalAmount(),
                cart.getStatus(),
                cartService.getSupplierProduct(userId, cart.getCartId(), cart.getCartSupplierProducts(), false)
        );
    }

    @Override
    public CartDto deleteSupplierProduct(String userId, String cartId, String supplierId, String productId) throws Exception {
        Cart cart = cartRepository.getReferenceById(cartId);
        int index = getIndexCartSupplierProduct(cart, supplierId, productId);
        if (index == -1) throw new Exception("Not found item");
        List<CartSupplierProduct> currentList = cart.getCartSupplierProducts();
        CartSupplierProduct cartSupplierProduct = currentList.remove(index);
        cart.setCartSupplierProducts(currentList);
        cart.setTotalAmount(cart.getTotalAmount() - cartSupplierProduct.getAmount());
        cart.setTotalPrice(cart.getTotalPrice() - cartSupplierProduct.getTotalPrice());
        cartSupplierProductRepository.deleteById(new SupplierProductKey(productId, supplierId, cartId));
        cartRepository.save(cart);
        return new CartDto(
                cart.getCartId(),
                cart.getTotalPrice(),
                cart.getTotalAmount(),
                cart.getStatus(),
                cartService.getSupplierProduct(userId, cart.getCartId(), cart.getCartSupplierProducts(), false)
        );
    }

    private int getIndexCartSupplierProduct(Cart cart, String supplierId, String productId) {
        for (int i = 0; i < cart.getCartSupplierProducts().size(); i++) {
            CartSupplierProduct cartSupplierProduct = cart.getCartSupplierProducts().get(i);
            if (cartSupplierProduct.getId().getSupplierId().equals(supplierId) &&
                    cartSupplierProduct.getId().getProductId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    private int getCartSupplierProductIndex(List<CartSupplierProduct> cartSupplierProducts, AddSupplierProductDto addSupplierProductDto) {
        for (int i = 0; i < cartSupplierProducts.size(); i++) {
            if (cartSupplierProducts.get(i).getId().getProductId().equals(addSupplierProductDto.getProductId()) &&
                    cartSupplierProducts.get(i).getId().getSupplierId().equals(addSupplierProductDto.getSupplierId())) {
                return i;
            }
        }
        return -1;
    }

    private Double getTotalPriceBySupplierProduct(AddSupplierProductDto s) {
        return s.getAmount() * getPriceBySupplierProduct(s);
    }

    private Double getPriceBySupplierProduct(AddSupplierProductDto s) {
        return discoveryService.getSupplierProductPrice(s.getSupplierId(), s.getProductId());
    }

}
