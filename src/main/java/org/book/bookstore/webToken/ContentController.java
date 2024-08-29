package org.book.bookstore.webToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContentController {


//    private final AuthenticationManager authenticationManager;

//    private final JWTService jwtService;

    private final AuthenticationService authenticationService;

//    private final MyUserDetailsService myUserDetailsService;

    private final RegistrationService registrationService;


    @GetMapping("/home")
    public String handleWelcome() {
        return "Welcome to home!";
    }

    @GetMapping("/admin/home")
    public String handleHomeAdmin() {
        return "Welcome to Admin home!";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "Welcome to User home!";
    }

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user){
        return registrationService.createUser(user);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm){
        return authenticationService.authenticateAndGetToken(loginForm) ;
    }




}