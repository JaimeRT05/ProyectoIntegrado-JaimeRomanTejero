package com.daw.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarPass {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "usuario";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
