package org.book.bookstore.webToken;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class RegistrationService {


    private final MyUserRepository myUserRepository;

    private final PasswordEncoder passwordEncoder;

    public MyUser createUser ( MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }
}
