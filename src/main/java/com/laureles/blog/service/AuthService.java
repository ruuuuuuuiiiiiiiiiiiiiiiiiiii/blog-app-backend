package com.laureles.blog.service;

import com.laureles.blog.payload.LoginDto;
import com.laureles.blog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);

}
