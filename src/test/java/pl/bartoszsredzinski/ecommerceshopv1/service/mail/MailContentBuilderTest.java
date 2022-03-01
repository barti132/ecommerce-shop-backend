package pl.bartoszsredzinski.ecommerceshopv1.service.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Bartosz Średziński
 * created on 01.03.2022
 */
@SpringBootTest
@ActiveProfiles("test")
class MailContentBuilderTest{

    @Autowired
    private MailContentBuilder builder;

    @Test
    public void build_should_return_templateMail(){
        String mail = builder.build("Test string should be in mail");
        assertEquals(true, mail.contains("<!DOCTYPE html>"));
        assertEquals(true, mail.contains("Test string should be in mail"));
    }
}