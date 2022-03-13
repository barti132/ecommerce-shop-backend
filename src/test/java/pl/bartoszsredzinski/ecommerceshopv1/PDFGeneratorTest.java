package pl.bartoszsredzinski.ecommerceshopv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.model.Invoice;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bartosz Średziński
 * created on 07.03.2022
 */
@SpringBootTest
@ActiveProfiles("test")
class PDFGeneratorTest{

    @Autowired
    private PDFGenerator pdfGenerator;

    @Test
    public void generateOrderInvoicePDF_should_return_PDF(){
        Invoice invoice = new Invoice();
        User user = User.builder().name("name").lastName("lastname").email("mail").phoneNumber("123").Addresses(new ArrayList<>())
                .id(1L).build();
        Address address = Address.builder().postalCode("postal").address("address").country("country").city("city").build();
        invoice.setUser(user);
        invoice.setAddress(address);
        invoice.setId(1L);
        invoice.setInvoiceDate(new Date(System.currentTimeMillis()));
        invoice.setTotalPriceNet(new BigDecimal(0));
        invoice.setTotalPriceGross(new BigDecimal(0));

        assertTrue(pdfGenerator.generateOrderInvoicePDF(invoice).length > 0);
    }
}