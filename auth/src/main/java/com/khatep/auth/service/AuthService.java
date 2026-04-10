package com.khatep.auth.service;
import com.khatep.auth.dto.AuthRequestDto;

public interface AuthService {
    String register(AuthRequestDto registerRequestDto);
    String login(AuthRequestDto loginRequestDto);
}
