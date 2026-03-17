package com.viniciusdevassis.storelab.presentation.controllers;

import com.viniciusdevassis.storelab.application.services.AuthService;
import com.viniciusdevassis.storelab.presentation.dtos.LoginDTO;
import com.viniciusdevassis.storelab.presentation.dtos.RegisterDTO;
import com.viniciusdevassis.storelab.presentation.dtos.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid LoginDTO body) {
        return ResponseEntity.ok(service.login(body));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(
            @Valid @RequestBody RegisterDTO body
    ) {
        return ResponseEntity.ok(service.register(body));
    }
}
