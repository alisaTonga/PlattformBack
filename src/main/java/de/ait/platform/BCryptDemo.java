package de.ait.platform;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptDemo {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "Qwerty123";
        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
    }
}
