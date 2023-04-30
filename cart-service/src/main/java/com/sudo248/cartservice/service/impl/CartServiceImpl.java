package com.sudo248.cartservice.service.impl;

import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.controller.dto.CartSupplierProductDto;
import com.sudo248.cartservice.controller.dto.SupplierProductDto;
import com.sudo248.cartservice.internal.DiscoveryService;
import com.sudo248.cartservice.repository.CartRepository;
import com.sudo248.cartservice.repository.entity.Cart;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import com.sudo248.cartservice.service.CartService;
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
        cart.setCartId(UUID.randomUUID().toString());
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
        Double totalPrice = 0.0;
        int totalAmount = 0;
        List<CartSupplierProductDto> cartSupplierProductDtoList = new ArrayList<>();
        if(cart.getCartSupplierProducts() != null   ){
            for(CartSupplierProduct cartSupplierProduct: cart.getCartSupplierProducts()){
                totalPrice += cartSupplierProduct.getTotalPrice();
                totalAmount += cartSupplierProduct.getAmount();
            }
            cartSupplierProductDtoList = getSupplierProduct(cart.getCartSupplierProducts());

        }
        CartDto cartDto = new CartDto(cart.getCartId(), totalPrice, totalAmount, cart.getStatus(), cart.getUserId(), cartSupplierProductDtoList);
        return cartDto;
    }

    @Override
    public CartDto getActiveCartByUserId(String userId) {
        List<Cart> carts = cartRepository.findAll();
        for(Cart cart:carts){
            if(cart.getUserId().equals(userId) && cart.getStatus().equals("active")){
                CartDto cartDto = new CartDto(cart.getCartId(), cart.getTotalPrice(), cart.getTotalAmount(), cart.getStatus(), cart.getUserId());
                return cartDto;
            }
        }
        CartDto newCart = creNewCart(userId);
        return newCart;
    }
    private List<CartSupplierProductDto> getSupplierProduct(List<CartSupplierProduct> list){
        List<CartSupplierProductDto> supplierProductDtos = new ArrayList<>();
        for(CartSupplierProduct s: list){
//            RestTemplate restTemplate = new RestTemplate();
//            String url = "http://DISCOVRY-SERVICE/api/v1/discovery/service/supplierId/" + s.getId().getSupplierId() + "/productId/" + s.getId().getProductId();
//
//            SupplierProductDto supplierProductDto = restTemplate.getForObject(url, SupplierProductDto.class);
            SupplierProductDto supplierProductDto = discoveryService.getSupplierProduct(s.getId().getSupplierId(), s.getId().getProductId());
            supplierProductDtos.add(new CartSupplierProductDto(supplierProductDto, s.getAmount(),s.getAmount() * supplierProductDto.getPrice()));
        }

        return supplierProductDtos;
    }
}
