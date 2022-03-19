package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.bartoszsredzinski.ecommerceshopv1.exception.ImageNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Image Service
 *
 * @author Bartosz Średziński
 * created on 19.02.2022
 */

@Service
public class ImageService{

    private static final String FILE_PATH_ROOT = "src\\main\\resources\\images\\";

    public byte[] getImageFromServer(String name){
        try{
            return FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + name));
        }
        catch(IOException e){
            throw new ImageNotFoundException("Reading file error");
        }
    }

    public void saveImage(MultipartFile image){

        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        if(!extension.equals("jpg")){
            throw new RuntimeException("Illegal file extension!");
        }

        Path uploadPath = Paths.get(FILE_PATH_ROOT + image.getOriginalFilename());

        try(InputStream inputStream = image.getInputStream()){
            Path filePath = uploadPath.resolve(StringUtils.cleanPath(image.getOriginalFilename()));
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Could not save file");
        }
    }
}
