package pl.bartoszsredzinski.ecommerceshopv1.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

/**
 * Jwt provider
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@Service
@RequiredArgsConstructor
public class JwtProvider{

    private final JwtEncoder jwtEncoder;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithLoginAndRole(principal.getUsername(), principal.getAuthorities());
    }

    public String generateTokenWithLoginAndRole(String login, Collection<GrantedAuthority> role){
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .subject(login)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
                .claim("scope", role.iterator().next().getAuthority())
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public long getJwtExpirationInMillis(){
        return jwtExpirationInMillis;
    }
}
