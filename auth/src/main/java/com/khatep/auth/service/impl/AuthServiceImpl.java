package com.khatep.auth.service.impl;

import com.khatep.auth.dto.AuthRequestDto;
import com.khatep.auth.entity.User;
import com.khatep.auth.enums.Role;
import com.khatep.auth.exceptions.business.InvalidCredentialsException;
import com.khatep.auth.exceptions.business.UserEmailAlreadyExistsException;
import com.khatep.auth.exceptions.business.UserNotFoundException;
import com.khatep.auth.mapper.UserMapper;
import com.khatep.auth.repository.UserRepository;
import com.khatep.auth.service.AuthService;
import com.khatep.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(AuthRequestDto registerRequestDto) {
        String email = registerRequestDto.getEmail();
        String password = passwordEncoder.encode(registerRequestDto.getPassword());

        if (userRepository.existsByEmail(email)) {
            throw new UserEmailAlreadyExistsException("user with email "+email+" already exist");
        }

        User user = userMapper.toUser(registerRequestDto);
        user.setPassword(password);
        user.setRole(Role.PARENT);

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(AuthRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User with email "+email+" not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password or email");
        }

        return jwtService.generateToken(user);
    }
}
