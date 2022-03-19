package pl.bartoszsredzinski.ecommerceshopv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 19.03.2022
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IllegalExtensionException extends RuntimeException{
    public IllegalExtensionException(String message){
        super(message);
    }
}
