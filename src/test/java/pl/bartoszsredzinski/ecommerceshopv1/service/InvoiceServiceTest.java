package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.PDFGenerator;
import pl.bartoszsredzinski.ecommerceshopv1.mapper.InvoiceMapper;
import pl.bartoszsredzinski.ecommerceshopv1.repository.InvoiceRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

/**
 * @author Bartosz Średziński
 * created on 15.03.2022
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class InvoiceServiceTest{

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private PDFGenerator pdfGenerator;

}