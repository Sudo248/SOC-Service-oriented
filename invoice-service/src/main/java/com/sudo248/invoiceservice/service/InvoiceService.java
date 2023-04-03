package com.sudo248.invoiceservice.service;

import com.sudo248.invoiceservice.Controller.dto.InvoiceDto;
import com.sudo248.invoiceservice.repository.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> getListInvoice(String userId);
    InvoiceDto getOrderByInvoiceId(String orderId);
    InvoiceDto addInvoice(InvoiceDto invoiceDto);
    InvoiceDto updateInvoice(InvoiceDto invoiceDto,String invoiceId);
    boolean deleteInvoice(String orderId);
    InvoiceDto toDto(Invoice invoice);
    Invoice toEntity(InvoiceDto invoiceDto);
    void updateInvoiceByField(String field, String fieldId);
}
