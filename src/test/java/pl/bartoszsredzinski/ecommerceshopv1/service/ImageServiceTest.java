package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.bartoszsredzinski.ecommerceshopv1.exception.ImageNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(ImageNotFoundException.class, () -> imageService.getImageFromServer("notOnTheSever.png"));
    }

    @Test
    public void getImageFromServer_should_work(){
        //laptop1.jpg have to be on the disk to run this test successfully
        byte[] image = imageService.getImageFromServer("laptop1.jpg");
        assertNotNull(image);
    }
}