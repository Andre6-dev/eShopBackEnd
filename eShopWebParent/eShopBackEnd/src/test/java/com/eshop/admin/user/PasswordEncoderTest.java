package com.eshop.admin.user;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    @Test
    public void testEncodePassword() {

        // Create a object who allow us to encrypt a variable.
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "nam2020";

        // Encrypting the variable "encodedPassword"
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodedPassword);

        // Matching the passwords to prove that both are the same
        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        assertThat(matches).isTrue();
    }
}
