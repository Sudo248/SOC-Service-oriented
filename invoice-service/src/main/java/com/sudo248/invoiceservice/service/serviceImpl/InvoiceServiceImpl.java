package com.sudo248.invoiceservice.service.serviceImpl;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.invoiceservice.Controller.dto.*;
import com.sudo248.invoiceservice.repository.InvoiceRepository;
import com.sudo248.invoiceservice.repository.entity.Invoice;
import com.sudo248.invoiceservice.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> getListInvoice(String userId) {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        for(Invoice invoice: invoiceList){
            if(invoice.getUserId().equals(userId))
            invoiceDtoList.add(toDto(invoice));
        }
        return invoiceDtoList;
    }

    @Override
    public InvoiceDto getInvoiceByInvoiceId(String invoiceId) {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        for(Invoice invoice: invoiceList){
            if(invoice.getInvoiceId().equals(invoiceId))
                return toDto(invoice);
        }
        return null;
    }

    @Override
    public InvoiceAddDto addInvoice(InvoiceAddDto invoiceAddDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setPaymentId(invoiceAddDto.getPaymentId());
        invoice.setCartId(invoiceAddDto.getCartId());
        invoice.setPromotionId(invoiceAddDto.getPromotionId());
        invoice.setAddress(invoiceAddDto.getAddress());
        invoice.setStatus(invoiceAddDto.getStatus());
        invoice.setUserId(invoiceAddDto.getUserId());
//        invoice.setTotalPrice(invoiceAddDto.getTotalPrice());
//        invoice.setTotalPromotionPrice(invoiceAddDto.getTotalPromotionPrice());
//        invoice.setFinalPrice(invoiceAddDto.getFinalPrice());
        Invoice savedInvoice = invoiceRepository.save(invoice);
        InvoiceDto invoiceDto = getInvoiceByInvoiceId(savedInvoice.getInvoiceId());
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        invoice.setTotalPromotionPrice(invoiceDto.getTotalPromotionPrice());
        invoice.setFinalPrice( invoiceDto.getFinalPrice());
        Invoice savedInvoice1 = invoiceRepository.save(invoice);
        invoiceAddDto.setTotalPromotionPrice(invoice.getTotalPromotionPrice());
        invoiceAddDto.setTotalPrice(invoice.getTotalPrice());
        invoiceAddDto.setFinalPrice(invoice.getFinalPrice());
        return invoiceAddDto;
    }

    @Override
    public InvoiceDto updateInvoice(InvoiceDto invoiceDto, String invoiceId) {
       Invoice invoicedelete = invoiceRepository.findById(invoiceId).get();
       invoiceRepository.deleteById(invoiceId);
       Invoice savedInvoice = invoiceRepository.save(toEntity(invoiceDto));
       return invoiceDto;
    }

    @Override
    public boolean deleteInvoice(String invoiceId) {
        if(invoiceRepository.findById(invoiceId) == null)
            return false;
        invoiceRepository.deleteById(invoiceId);
        return true;
    }

    @Override
    public InvoiceDto toDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
//        invoiceDto.setUserDto(getUserById(invoice.getUserId()));
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setPaymentDto(getPaymentById(invoice.getPaymentId()));
        invoiceDto.setCartDto(getCartById(invoice.getCartId()));
        invoiceDto.setPromotionDto(getPromotionById(invoice.getPromotionId()));
        invoiceDto.setAddress(invoice.getAddress());
        invoiceDto.setStatus(invoice.getStatus());
        invoiceDto.setTotalPrice(invoiceDto.getCartDto().getTotalPrice());
        invoiceDto.setTotalPromotionPrice(invoiceDto.getPromotionDto().getValue());
        invoiceDto.setFinalPrice(invoiceDto.getCartDto().getTotalPrice() - invoiceDto.getTotalPromotionPrice());
        return invoiceDto;
    }

    @Override
    public Invoice toEntity(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceDto.getInvoiceId());
        invoice.setPaymentId(invoiceDto.getPaymentDto().getPaymentId());
        invoice.setCartId(invoiceDto.getCartDto().getCartId());
//        invoice.setUserId(invoiceDto.getUserDto().getUserId());
        invoice.setPromotionId(invoiceDto.getPromotionDto().getPromotionId());
        invoice.setAddress(invoiceDto.getAddress());
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        invoice.setStatus(invoiceDto.getStatus());
        invoice.setTotalPromotionPrice(invoiceDto.getTotalPromotionPrice());
        invoice.setFinalPrice(invoiceDto.getFinalPrice());
        return invoice;
    }

    @Override
    public void updateInvoiceByField(String field, String id) {
        switch (field){
            case "promotion":
                List<Invoice> invoiceList = invoiceRepository.findAll();
                PromotionDto promotionDto = getPromotionById(id);
                for(Invoice invoice: invoiceList){
                    if(invoice.getPromotionId().equals(id)){
                        invoice.setTotalPromotionPrice(promotionDto.getValue());
                        invoice.setFinalPrice(invoice.getTotalPrice() - invoice.getTotalPromotionPrice());
                        invoiceRepository.save(invoice);
                    }
                }
                break;
        }
    }

    //Request Detail
    private PromotionDto getPromotionById(String promotionId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:8085/api/v1/promotion/service/" + promotionId;

        PromotionDto promotionDto = restTemplate.getForObject(url, PromotionDto.class);

        return promotionDto;
    }
//    private UserDto getUserById(String userId){
////        RestTemplate restTemplate = new RestTemplate();
////        String url = "http://localhost:8081/api/v1/user/{userId}";
////        UserDto userDto = restTemplate.exchange(url, HttpMethod.GET, null, UserDto.class, userId).getBody();
//        return new UserDto(userId);
//    }
    private CartDto getCartById(String cartId){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8086/api/v1/cart/service/{cartId}";
        CartDto cartDto = restTemplate.exchange(url, HttpMethod.GET, null, CartDto.class, cartId).getBody();
        System.out.println(cartDto.getCartId());
        return cartDto;
    }
    private PaymentDto getPaymentById(String paymentId){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8081/api/v1/payment/{paymentId}";
//        PaymentDto paymentDto = restTemplate.exchange(url, HttpMethod.GET, null, PaymentDto.class, paymentId).getBody();
        return new PaymentDto(paymentId);
    }

    //Update



}