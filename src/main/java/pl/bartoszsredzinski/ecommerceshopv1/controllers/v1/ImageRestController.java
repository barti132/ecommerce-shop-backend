package pl.bartoszsredzinski.ecommerceshopv1.controllers.v1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/image")
public class ImageRestController{

    private String FILE_PATH_ROOT = "C:\\workspaceIntellij\\ecommerceshopv1\\src\\main\\resources\\images\\";

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename){
        log.info("GET /" + filename);
        byte[] image = new byte[0];
        try{
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + filename));
        }catch(IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
