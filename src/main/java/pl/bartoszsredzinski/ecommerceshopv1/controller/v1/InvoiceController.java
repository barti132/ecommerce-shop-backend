package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.dto.InvoiceDto;
import pl.bartoszsredzinski.ecommerceshopv1.service.InvoiceService;

import java.util.List;

/**
 * Invoice controller
 *
 * @author Bartosz Średziński
 * created on 15.03.2022
 */

@RestController
@RequestMapping("/api/v1/invoices/{login}")
@AllArgsConstructor
@Slf4j
public class InvoiceController{

    private final InvoiceService invoiceService;

    @GetMapping("")
    public List<InvoiceDto> getInvoicesHistory(@PathVariable String login){
        log.info("GET invoices/" + login);
        return invoiceService.getAllUserInvoices(login);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<byte[]> getInvoicePdf(@PathVariable String login, @PathVariable Long id){
        log.info("GET invoices/" + login + "/id/" + id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(invoiceService.getInvoicePDF(login, id));
    }
}
