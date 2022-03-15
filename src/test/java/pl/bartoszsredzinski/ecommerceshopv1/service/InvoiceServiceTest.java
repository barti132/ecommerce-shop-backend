package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.dto.InvoiceDto;
import pl.bartoszsredzinski.ecommerceshopv1.exception.InvalidIdException;
import pl.bartoszsredzinski.ecommerceshopv1.model.*;
import pl.bartoszsredzinski.ecommerceshopv1.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bartosz Średziński
 * created on 15.03.2022
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class InvoiceServiceTest{

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    private Invoice invoice1;

    @BeforeAll
    void init(){
        User user1 = User.builder()
                .email("email")
                .login("spring")
                .password("password")
                .name("name")
                .lastName("lastName")
                .role("user")
                .Addresses(new ArrayList<>())
                .enabled(true)
                .build();
        user1 = userRepository.save(user1);
        Address address = new Address(null, "", "", "", "");
        address = addressRepository.save(address);

        Product product1 = Product.builder()
                .category("category1")
                .producerName("producer1")
                .name("Special product")
                .description("")
                .img("")
                .priceNet(new BigDecimal(0))
                .priceGross(new BigDecimal(5))
                .build();
        product1 = productRepository.save(product1);

        CartItem cartItem = new CartItem(1L, product1, 1);
        cartItem = cartItemRepository.save(cartItem);
        CartItem cartItem1 = new CartItem(2L, product1, 1);
        cartItem1 = cartItemRepository.save(cartItem1);


        invoice1 = new Invoice(1L, new Date(System.currentTimeMillis()), user1, address, new BigDecimal(1), new BigDecimal(1), 1,
                "abc1", "abc1", List.of(cartItem));
        Invoice invoice2 = new Invoice(2L, new Date(System.currentTimeMillis()), user1, address, new BigDecimal(2),
                new BigDecimal(2), 2, "abc2", "abc2", List.of(cartItem1));
        invoice1 = invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);
    }

    @WithMockUser(username = "spring")
    @Test
    public void getAllUserInvoices_should_return_list_of_invoices(){
        List<InvoiceDto> invoiceDtos = invoiceService.getAllUserInvoices("spring");

        assertEquals(2, invoiceDtos.size());
        assertEquals("abc1", invoiceDtos.get(0).getCardNumber());
    }

    @WithMockUser(username = "spring")
    @Test
    public void getInvoicePDF_should_return_not_0_bytes(){
        byte[] bytes = invoiceService.getInvoicePDF("spring", 1L);
        assertTrue(bytes.length > 1);
    }


    @WithMockUser(username = "spring")
    @Test
    public void getInvoicePDF_should_throw_exception(){
        assertThrows(InvalidIdException.class, () -> invoiceService.getInvoicePDF("spring", 3L));
    }

}