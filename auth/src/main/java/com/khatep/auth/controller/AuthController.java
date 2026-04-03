package com.khatep.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class AuthController {
    @PostMapping("/register")
    void register() {

    }
}
