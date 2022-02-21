package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ImageService test class
 *
 * @author Bartosz Średziński
 * created on 19.02.2022
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ImageServiceTest{

    @Autowired
    private ImageService imageService;

    @Test
    public void getImageFromServer_should_throw_exception(){
        assertThrows(IOException.class, ()-> imageService.getImageFromServer("notOnTheSever.png"));
    }

    @Test
    public void getImageFromServer_should_work(){
        try{
            //laptop1.jpg have to be on the disk to run this test successfully
            byte[] image = imageService.getImageFromServer("laptop1.jpg");
            assertNotNull(image);
        }
        catch(IOException e){
            fail("Service throws IOException.");
        }
    }
}