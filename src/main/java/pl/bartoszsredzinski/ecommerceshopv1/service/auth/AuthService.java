package pl.bartoszsredzinski.ecommerceshopv1.service.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.dto.AuthenticationResponse;
import pl.bartoszsredzinski.ecommerceshopv1.dto.LoginRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RefreshTokenRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RegisterRequest;
import pl.bartoszsredzinski.ecommerceshopv1.model.NotificationEmail;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.model.VerificationToken;
import pl.bartoszsredzinski.ecommerceshopv1.repository.VerificationTokenRepository;
import pl.bartoszsredzinski.ecommerceshopv1.service.security.JwtProvider;
import pl.bartoszsredzinski.ecommerceshopv1.service.mail.MailService;

import java.util.Optional;
import java.util.UUID;

/**
 * Auth Service
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */
@Service
@AllArgsConstructor
public class AuthService{

    private final PasswordEncoder passwordEncoder;
    private final pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository userRepository;
    private final VerificationTokenRepository tokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = User.builder()
                .login(registerRequest.getLogin())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .role("user")
                .enabled(false)
                .build();

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please active your account", user.getEmail(),
                "Thank you for signing up.\n Please click on the below url to activate your" +
                        " account: http://localhost:8080/api/v1/auth/account-verification/" + token));
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + authentication.getName()));
    }


    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        tokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token){
        Optional<VerificationToken> verificationToken = tokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new RuntimeException("Invalid Token")); //add exception
        fetchUserAndEnable(verificationToken.get());
    }

    private void fetchUserAndEnable(VerificationToken verificationToken){
        String login = verificationToken.getUser().getLogin();
        User user = userRepository.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found with login - " + login));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(token, refreshTokenService.generateRefreshToken().getToken());
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithLoginAndRole(refreshTokenRequest.getLogin(), getCurrentUser().getRole());
        return new AuthenticationResponse(token, refreshTokenRequest.getRefreshToken());
    }
}
