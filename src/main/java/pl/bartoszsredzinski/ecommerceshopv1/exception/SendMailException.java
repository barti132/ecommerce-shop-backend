package pl.bartoszsredzinski.ecommerceshopv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SendMailException extends RuntimeException{
    public SendMailException(String message){
        super(message);
    }
}
