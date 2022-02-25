package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.bartoszsredzinski.ecommerceshopv1.service.ImageService;

import java.io.IOException;

/**
 * Image rest controller
 *
 * @author Bartosz Średziński
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/image")
public class ImageController{

    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    /**
     * @param filename image file name
     * @return ResponseEntity<byte [ ]>  - image with code 200 or 500 if error
     */
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename){
        log.info("GET image/" + filename);

        try{
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageService.getImageFromServer(filename));
        }catch(IOException e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while reading image.");
        }
    }
}
