package com.sudo248.cartservice.service.impl;

import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.controller.dto.CartSupplierProductDto;
import com.sudo248.cartservice.controller.dto.SupplierProductDto;
import com.sudo248.cartservice.internal.DiscoveryService;
import com.sudo248.cartservice.repository.CartRepository;
import com.sudo248.cartservice.repository.entity.Cart;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import com.sudo248.cartservice.service.CartService;
import com.sudo248.domain.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    private final DiscoveryService discoveryService;

    public CartServiceImpl(CartRepository cartRepository, DiscoveryService discoveryService) {
        this.cartRepository = cartRepository;
        this.discoveryService = discoveryService;
    }

    @Override
    public CartDto creNewCart(String userId) {
        Cart cart = new Cart();
        cart.setCartId(Utils.createId());
        cart.setUserId(userId);
        cart.setStatus("active");
        cart.setTotalAmount(0);
        cart.setTotalPrice(0.0);
        Cart savedCart = cartRepository.save(cart);
        CartDto cartDto = getCartById(savedCart.getCartId());
        return cartDto;
    }

    @Override
    public CartDto updateStatusCart(String cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setStatus("completed");
        Cart savedCart = cartRepository.save(cart);
        CartDto cartDto = getCartById(savedCart.getCartId());
        return cartDto;
    }

    @Override
    public CartDto getCartById(String cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        double totalPrice = 0.0;
        int totalAmount = 0;
        List<CartSupplierProductDto> cartSupplierProductDtoList = new ArrayList<>();
        if(cart.getCartSupplierProducts() != null){
            for(CartSupplierProduct cartSupplierProduct: cart.getCartSupplierProducts()){
                totalPrice += cartSupplierProduct.getTotalPrice();
                totalAmount += cartSupplierProduct.getAmount();
            }
            cartSupplierProductDtoList = getSupplierProduct(cartId, cart.getCartSupplierProducts());
        }
        CartDto cartDto = new CartDto(
                cart.getCartId(),
                totalPrice,
                totalAmount,
                cart.getStatus(),
                cartSupplierProductDtoList
        );
        return cartDto;
    }

    @Override
    public CartDto getActiveCartByUserId(String userId) {
        try {
            Cart cart = cartRepository.findByUserIdAndStatus(userId, "active");
            if (cart == null) {
                throw new Exception("Not found cart for user " + userId);
            }
            CartDto cartDto = new CartDto(
                    cart.getCartId(),
                    cart.getTotalPrice(),
                    cart.getTotalAmount(),
                    cart.getStatus(),
                    getSupplierProduct(cart.getCartId(), cart.getCartSupplierProducts())
            );
            return cartDto;
        } catch (Exception e) {
            return creNewCart(userId);
        }
    }

    @Override
    public List<CartSupplierProductDto> getSupplierProduct(String cartId, List<CartSupplierProduct> list){
        List<CartSupplierProductDto> supplierProductDtos = new ArrayList<>();
        for(CartSupplierProduct s: list){
            SupplierProductDto supplierProductDto = discoveryService.getSupplierProduct(s.getId().getSupplierId(), s.getId().getProductId());
            supplierProductDtos.add(new CartSupplierProductDto(
                    supplierProductDto,
                    s.getAmount(),
                    s.getAmount() * supplierProductDto.getPrice(),
                    cartId
            ));
        }

        return supplierProductDtos;
    }
}
