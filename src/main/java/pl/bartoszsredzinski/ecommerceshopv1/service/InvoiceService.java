package pl.bartoszsredzinski.ecommerceshopv1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.PDFGenerator;
import pl.bartoszsredzinski.ecommerceshopv1.dto.InvoiceDto;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.InvoiceMapper;
import pl.bartoszsredzinski.ecommerceshopv1.model.Invoice;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.InvoiceRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

import java.util.ArrayList;
import java.util.List;

/**
 * Invoice service
 *
 * @author Bartosz Średziński
 * created on 15.03.2022
 */
@Service
@AllArgsConstructor
public class InvoiceService{

    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final AuthService authService;
    private final PDFGenerator pdfGenerator;

    public List<InvoiceDto> getAllUserInvoices(String login){
        User user = authService.getCurrentUser(login);
        List<Invoice> invoices = invoiceRepository.findByUser(user);

        List<InvoiceDto> invoiceDtos = new ArrayList<>();

        for(Invoice in : invoices){
            invoiceDtos.add(invoiceMapper.invoiceToInvoiceDto(in));
        }

        return invoiceDtos;
    }

    public byte[] getInvoicePDF(String login, Long id){
        authService.getCurrentUser(login);

        Invoice invoice = invoiceRepository.getFullInvoiceByID(id).orElseThrow(() -> new InvalidIdException("Invalid invoice id"));

        return pdfGenerator.generateOrderInvoicePDF(invoice);
    }
}
