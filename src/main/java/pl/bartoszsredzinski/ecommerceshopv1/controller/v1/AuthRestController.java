package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RegisterRequest;
import pl.bartoszsredzinski.ecommerceshopv1.service.AuthService;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController{

    private final AuthService authService;

    public AuthRestController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Reistration Successful", HttpStatus.OK);
    }
}
