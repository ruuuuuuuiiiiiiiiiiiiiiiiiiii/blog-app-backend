package com.laureles.blog.utils;

import com.laureles.blog.payload.RegisterDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        RegisterDto registerDto = new RegisterDto();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("admin: " +passwordEncoder.encode("admin"));
        System.out.println("user: " +passwordEncoder.encode("12345"));

        System.out.println("try: " +registerDto.getPassword());
    }
}
