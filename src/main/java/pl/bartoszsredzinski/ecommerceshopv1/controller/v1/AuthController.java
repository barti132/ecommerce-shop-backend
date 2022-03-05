package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.dto.AuthenticationResponse;
import pl.bartoszsredzinski.ecommerceshopv1.dto.LoginRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RefreshTokenRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RegisterRequest;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.RefreshTokenService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

/**
 * Auth rest controller
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        log.info("POST auth/signup");
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/account-verification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        log.info("GET auth/account-verification/{token}");
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", OK);
    }

    @Transactional
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        log.info("GET auth/login");
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        log.info("POST auth/refresh/token");
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        log.info("POST auth/logout");
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh token deleted successfully!");
    }
}
