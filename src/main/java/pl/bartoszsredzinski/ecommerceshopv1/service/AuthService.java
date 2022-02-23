package pl.bartoszsredzinski.ecommerceshopv1.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bartoszsredzinski.ecommerceshopv1.dto.RegisterRequest;
import pl.bartoszsredzinski.ecommerceshopv1.model.NotificationEmail;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.model.VerificationToken;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserJpaRepository;
import pl.bartoszsredzinski.ecommerceshopv1.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 23.02.2022
 */
@Service
public class AuthService{

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository UserRepository;
    private final VerificationTokenRepository tokenRepository;
    private final MailService mailService;

    public AuthService(PasswordEncoder passwordEncoder, UserJpaRepository userRepository, VerificationTokenRepository tokenRepository, MailService mailService){
        this.passwordEncoder = passwordEncoder;
        UserRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = User.builder().login(registerRequest.getLogin()).email(registerRequest.getEmail()).password(passwordEncoder.encode(registerRequest.getPassword())).name(registerRequest.getName()).lastName(registerRequest.getLastName()).role("user").enabled(false).build();

        UserRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please active your account", user.getEmail(),
                "Thank you for signing up. Please click on the below url to activate your" +
                        " account: http://localhost:8080/api/v1/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        tokenRepository.save(verificationToken);
        return token;
    }
}
