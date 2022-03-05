package pl.bartoszsredzinski.ecommerceshopv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String message){
        super(message);
    }
}
