package pl.bartoszsredzinski.ecommerceshopv1.services;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 19.02.2022
 */

@Service
public class ImageService{

    private final String FILE_PATH_ROOT = "C:\\workspaceIntellij\\ecommerceshopv1\\src\\main\\resources\\images\\";

    public byte[] getImageFromServer(String name) throws IOException{
        return FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + name));
    }
}
