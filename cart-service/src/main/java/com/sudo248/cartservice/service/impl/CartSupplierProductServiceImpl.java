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
        CartSupplierProduct cartSupplierProduct = new CartSupplierProduct(
                supplierProductKey,
                addSupplierProductDto.getAmount(),
                getTotalPriceBySupplierProduct(addSupplierProductDto),
                cart
        );
        cartSupplierProductRepository.save(cartSupplierProduct);

        List<CartSupplierProduct> supplierProductList = new ArrayList<>();

        if (cart.getCartSupplierProducts() != null)
            supplierProductList = cart.getCartSupplierProducts();
        supplierProductList.add(cartSupplierProduct);

        cart.setCartSupplierProducts(supplierProductList);

        double totalPrice = cart.getTotalPrice() + cartSupplierProduct.getTotalPrice();
        int totalAmount = cart.getTotalAmount() + cartSupplierProduct.getAmount();

        cart.setTotalPrice(totalPrice);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

        return new CartDto(
                cart.getCartId(),
                cart.getTotalPrice(),
                cart.getTotalAmount(),
                cart.getStatus(),
                cartService.getSupplierProduct(cart.getCartId(), cart.getCartSupplierProducts())
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
    public CartDto updateSupplierProductToCart(String userId, List<AddSupplierProductDto> addSupplierProductDtoList) {
        Cart cart = getActiveCart(userId);

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
                cartService.getSupplierProduct(cart.getCartId(), cart.getCartSupplierProducts())
        );
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
