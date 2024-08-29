package org.book.bookstore.webToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final MyUserRepository myUserRepository;
    private final JWTService jwtService;
    private final MyUserDetailsService myUserDetailsService;


    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        if (authentication.isAuthenticated()){
            return jwtService.getSigningKey((MyUser) myUserDetailsService.loadUserByUsername(loginForm.username()));

        }else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

}
