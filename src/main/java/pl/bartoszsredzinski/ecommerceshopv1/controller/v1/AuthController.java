package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RegisterRequest;
import pl.bartoszsredzinski.ecommerceshopv1.service.AuthService;

import static org.springframework.http.HttpStatus.OK;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        log.info("POST auth/signup");
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        log.info("GET accountVerification/{token}");
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", OK);
    }
}
