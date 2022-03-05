package pl.bartoszsredzinski.ecommerceshopv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException(String message){
        super(message);
    }
}
