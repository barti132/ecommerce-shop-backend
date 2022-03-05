package pl.bartoszsredzinski.ecommerceshopv1.exception;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */

public class InvalidProductIdException extends RuntimeException{
    public InvalidProductIdException(String message){
        super(message);
    }
}
