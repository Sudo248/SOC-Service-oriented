package com.sudo248.invoiceservice.service.serviceImpl;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.util.Utils;
import com.sudo248.invoiceservice.Controller.dto.*;
import com.sudo248.invoiceservice.internal.CartService;
import com.sudo248.invoiceservice.internal.PaymentService;
import com.sudo248.invoiceservice.internal.PromotionService;
import com.sudo248.invoiceservice.internal.UserService;
import com.sudo248.invoiceservice.repository.InvoiceRepository;
import com.sudo248.invoiceservice.repository.entity.Invoice;
import com.sudo248.invoiceservice.repository.entity.OrderStatus;
import com.sudo248.invoiceservice.repository.entity.Shipment;
import com.sudo248.invoiceservice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final UserService userService;

    private final CartService cartService;

    private final PaymentService paymentService;

    private final PromotionService promotionService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, UserService userService, CartService cartService, PaymentService paymentService, PromotionService promotionService) {
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.promotionService = promotionService;
    }

    @Override
    public List<InvoiceDto> getListInvoice(String userId) {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtoList = new ArrayList<>();
        for (Invoice invoice : invoiceList) {
            if (invoice.getUserId().equals(userId))
                invoiceDtoList.add(toDto(invoice));
        }
        return invoiceDtoList;
    }

    @Override
    public InvoiceDto getInvoiceByInvoiceId(String invoiceId) {
        Invoice invoice = invoiceRepository.getReferenceById(invoiceId);
        return toDto(invoice);
    }

    @Override
    public InvoiceAddDto addInvoice(String userId, InvoiceAddDto invoiceAddDto) {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(Utils.createIdOrElse(invoiceAddDto.getInvoiceId()));
        invoice.setPaymentId(invoiceAddDto.getPaymentId());
        invoice.setCartId(invoiceAddDto.getCartId());
        invoice.setUserId(userId);
        invoice.setPromotionId(invoiceAddDto.getPromotionId());
        invoice.setStatus(invoiceAddDto.getStatus());
        invoice.setStatus(OrderStatus.PREPARE);

        CartDto cartDto = getCartById(userId, invoiceAddDto.getCartId(), true);
        log.info("cartDto: " + cartDto);
        invoice.setShipment(calculateShipment(cartDto));

        invoice.setTotalPrice(cartDto.getTotalPrice() + invoice.getShipment().getPriceShipment());
        invoice.setTotalPromotionPrice(0.0);
        invoice.setFinalPrice(invoice.getTotalPrice() - invoice.getTotalPromotionPrice());
        invoiceRepository.save(invoice);

        invoiceAddDto.setInvoiceId(invoice.getInvoiceId());
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
        if (invoiceRepository.findById(invoiceId) == null)
            return false;
        invoiceRepository.deleteById(invoiceId);
        return true;
    }

    @Override
    public InvoiceDto toDto(Invoice invoice) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setCart(getCartById(invoice.getUserId(), invoice.getCartId(), false));
        invoiceDto.setPayment(getPaymentById(invoice.getPaymentId()));
        invoiceDto.setPromotion(getPromotionById(invoice.getPromotionId()));
        invoiceDto.setUser(getUserById(invoice.getUserId()));
        invoiceDto.setStatus(invoice.getStatus());
        invoiceDto.setShipment(invoice.getShipment());
        invoiceDto.setTotalPrice(invoice.getTotalPrice());
        invoiceDto.setTotalPromotionPrice(invoiceDto.getPromotion().getValue());
        invoiceDto.setFinalPrice(invoice.getTotalPrice() - invoiceDto.getTotalPromotionPrice());
        return invoiceDto;
    }

    @Override
    public Invoice toEntity(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(Utils.createIdOrElse(invoiceDto.getInvoiceId()));
        invoice.setPaymentId(invoiceDto.getPayment().getPaymentId());
        invoice.setCartId(invoiceDto.getCart().getCartId());
        invoice.setUserId(invoiceDto.getUser().getUserId());
        invoice.setPromotionId(invoiceDto.getPromotion().getPromotionId());
        invoice.setTotalPrice(invoiceDto.getTotalPrice());
        invoice.setStatus(invoiceDto.getStatus());
        invoice.setTotalPromotionPrice(invoiceDto.getTotalPromotionPrice());
        invoice.setFinalPrice(invoiceDto.getFinalPrice());
        return invoice;
    }

    @Override
    public InvoiceDto updateInvoiceByField(String invoiceId, String field, String id) {
        Invoice invoice = invoiceRepository.getReferenceById(invoiceId);
        switch (field) {
            case "promotion":
                PromotionDto promotionDto = getPromotionById(id);
                invoice.setPromotionId(id);
                invoice.setTotalPromotionPrice(promotionDto.getValue());
                invoice.setFinalPrice(invoice.getTotalPrice() - invoice.getTotalPromotionPrice());
                invoiceRepository.save(invoice);
                return toDto(invoice);
        }
        return null;
    }

    @Override
    public void updateInvoicePayment(String invoiceId, String paymentId) {
        Invoice invoice = invoiceRepository.getReferenceById(invoiceId);
        invoice.setPaymentId(paymentId);
        invoiceRepository.save(invoice);
    }

    //Request Detail
    private PromotionDto getPromotionById(String promotionId) {
        if (promotionId == null) return new PromotionDto();
        return promotionService.getPromotionById(promotionId);
    }

    private UserDto getUserById(String userId) {
        ResponseEntity<BaseResponse<UserDto>> response = userService.getUserInfo(userId);
        if (response.getStatusCodeValue() == 200) {
            return response.getBody().getData();
        }
        return new UserDto();
    }

    private CartDto getCartById(String userId, String cartId, boolean hasRoute) {
        return cartService.getCartById(userId, cartId, hasRoute);
    }

    private PaymentDto getPaymentById(String paymentId) {
        if (paymentId == null) return null;
        return paymentService.getPaymentById(paymentId);
    }

    private Shipment calculateShipment(CartDto cartDto) {
        List<RouteDto> routes = cartDto.getCartSupplierProducts().stream().map(cpd -> cpd.getSupplierProduct().getRoute()).collect(Collectors.toList());
        RouteDto maxRoute = routes.stream().max(Comparator.comparingDouble(r -> r.getDistance().getValue())).get();
        return new Shipment(
                "Nhanh",
                (int) maxRoute.getDuration().getValue(),
                maxRoute.getDuration().getUnit(),
                calculateShipmentPrice(maxRoute.getDistance())
        );
    }

    private double calculateShipmentPrice(ValueDto distance) {
        if (distance.getUnit().equals("km")) {
            return distance.getValue() * 10000;
        } else {
            return distance.getValue() * 100;
        }
    }

}
