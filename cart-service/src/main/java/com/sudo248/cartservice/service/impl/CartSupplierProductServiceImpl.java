package com.sudo248.cartservice.service.impl;

import com.sudo248.cartservice.controller.dto.AddSupplierProductDto;
import com.sudo248.cartservice.controller.dto.CartDto;
import com.sudo248.cartservice.controller.dto.CartSupplierProductDto;
import com.sudo248.cartservice.controller.dto.SupplierProductDto;
import com.sudo248.cartservice.repository.CartRepository;
import com.sudo248.cartservice.repository.CartSupplierProductRepository;
import com.sudo248.cartservice.repository.entity.Cart;
import com.sudo248.cartservice.repository.entity.CartSupplierProduct;
import com.sudo248.cartservice.repository.entity.SupplierProductKey;
import com.sudo248.cartservice.service.CartService;
import com.sudo248.cartservice.service.CartSupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartSupplierProductServiceImpl implements CartSupplierProductService {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartSupplierProductRepository cartSupplierProductRepository;

    @Override
    public CartDto addSupplierProductToCart(String userId, AddSupplierProductDto addSupplierProductDto) {
        CartDto cartDto = cartService.getActiveCartByUserId(userId);
        Cart cart = cartRepository.findById(cartDto.getCartId()).get();
        SupplierProductKey supplierProductKey = new SupplierProductKey(addSupplierProductDto.getProductId(), addSupplierProductDto.getSupplierId(), cart.getCartId());
        CartSupplierProduct cartSupplierProduct = new CartSupplierProduct(supplierProductKey, addSupplierProductDto.getAmount(), getTotalPriceBySupplierProduct(addSupplierProductDto),cart);
        cartSupplierProductRepository.save(cartSupplierProduct);

        List<CartSupplierProduct> supplierProductList = new ArrayList<>();
        if(cart.getCartSupplierProducts() != null)
            supplierProductList = cart.getCartSupplierProducts();
        supplierProductList.add(cartSupplierProduct);
        cart.setCartSupplierProducts(supplierProductList);

        cartRepository.save(cart);
        CartDto savedCartDto = cartService.getCartById(cart.getCartId());
        cart.setTotalAmount(savedCartDto.getTotalAmount());
        cart.setTotalPrice(savedCartDto.getTotalPrice());
        cartRepository.save(cart);
        return cartService.getCartById(cart.getCartId());
    }

    @Override
    public CartDto updateSupplierProductToCart(String userId, List<AddSupplierProductDto> addSupplierProductDtoList) {
        CartDto cartDto = cartService.getActiveCartByUserId(userId);
        cartRepository.deleteById(cartDto.getCartId());
        cartDto = cartService.creNewCart(userId);
        Cart cart = cartRepository.findById(cartDto.getCartId()).get();
        Double totalPrice = 0.0;
        int totalAmount = 0;
        List<CartSupplierProduct> supplierProductList = new ArrayList<>();


        for(AddSupplierProductDto addSupplierProductDto:addSupplierProductDtoList){
            SupplierProductKey supplierProductKey = new SupplierProductKey(addSupplierProductDto.getProductId(), addSupplierProductDto.getSupplierId(), cart.getCartId());
            CartSupplierProduct cartSupplierProduct = new CartSupplierProduct(supplierProductKey, addSupplierProductDto.getAmount(), addSupplierProductDto.getTotalPrice(),cart);
            supplierProductList.add(cartSupplierProduct);
            totalPrice += addSupplierProductDto.getTotalPrice();
            totalAmount += addSupplierProductDto.getAmount();
            cartSupplierProductRepository.save(cartSupplierProduct);
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalAmount(totalAmount);
        cart.setCartSupplierProducts(supplierProductList);
        cartRepository.save(cart);
        return cartService.getCartById(cart.getCartId());

    }
    private Double getTotalPriceBySupplierProduct(AddSupplierProductDto s){

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://127.0.0.1:8083/api/v1/discovery/service/supplierId/" + s.getSupplierId() + "/productId/" + s.getProductId();

            SupplierProductDto supplierProductDto = restTemplate.getForObject(url, SupplierProductDto.class);
            return s.getAmount() * supplierProductDto.getPrice();
    }

}
