package pl.bartoszsredzinski.ecommerceshopv1.service.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszsredzinski.ecommerceshopv1.model.User;
import pl.bartoszsredzinski.ecommerceshopv1.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * User Detail Service implementation
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 * @see UserDetailsService
 */
@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> userOptional = userRepository.findByLogin(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.isEnabled(),
                true, true, true, getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
