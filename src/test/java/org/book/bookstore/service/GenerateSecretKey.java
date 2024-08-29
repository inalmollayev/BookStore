package org.book.bookstore.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.util.Base64;


public class GenerateSecretKey {
    @Test
    public void generateKey() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Key: " + secretKey);

    }

}
