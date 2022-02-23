package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RegisterRequest;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController{

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest){

    }
}
