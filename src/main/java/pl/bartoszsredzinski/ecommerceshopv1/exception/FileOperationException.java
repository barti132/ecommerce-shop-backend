package pl.bartoszsredzinski.ecommerceshopv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 19.03.2022
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileOperationException extends RuntimeException{
    public FileOperationException(String message){
        super(message);
    }
}
