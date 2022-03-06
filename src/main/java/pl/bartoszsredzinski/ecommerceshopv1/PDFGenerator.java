package pl.bartoszsredzinski.ecommerceshopv1;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.bartoszsredzinski.ecommerceshopv1.exception.CreateInvoiceException;
import pl.bartoszsredzinski.ecommerceshopv1.model.Invoice;

import java.io.FileOutputStream;

/**
 * PDF generator
 *
 * @author Bartosz Średziński
 * created on 06.03.2022
 */
@Component
public class PDFGenerator{


    private final TemplateEngine templateEngine;

    public PDFGenerator(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    private String generateInvoiceHtml(Invoice invoice){
        Context context = new Context();
        context.setVariable("invoice", invoice);
        context.setVariable("user", invoice.getUser());
        context.setVariable("address", invoice.getAddress());
        context.setVariable("products", invoice.getProducts());
        return templateEngine.process("invoiceTemplate", context);
    }

    public void generateOrderInvoicePDF(Invoice invoice){
        String invoiceHtml = generateInvoiceHtml(invoice);
        try{
            HtmlConverter.convertToPdf(invoiceHtml, new FileOutputStream("test.pdf"));
        }catch(Exception e){
            throw new CreateInvoiceException("Error while creating pdf");
        }
    }
}
