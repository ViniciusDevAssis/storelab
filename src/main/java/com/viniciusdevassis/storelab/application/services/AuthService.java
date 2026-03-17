package com.viniciusdevassis.storelab.application.services;

import com.viniciusdevassis.storelab.domain.entities.User;
import com.viniciusdevassis.storelab.infrastructure.repositories.UserRepository;
import com.viniciusdevassis.storelab.infrastructure.security.TokenService;
import com.viniciusdevassis.storelab.presentation.dtos.LoginDTO;
import com.viniciusdevassis.storelab.presentation.dtos.RegisterDTO;
import com.viniciusdevassis.storelab.presentation.dtos.ResponseDTO;
import com.viniciusdevassis.storelab.presentation.mappers.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public ResponseDTO register(RegisterDTO dto) {
        User user = User.newUser(
                dto.getName(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword())
        );
        User savedUser = repository.save(user);
        String token = tokenService.generateToken(user);
        return new ResponseDTO(savedUser.getName(), token);
    }

    public ResponseDTO login(LoginDTO dto) {
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        dto.email(),
                        dto.password()
                );
        var authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        return new ResponseDTO(user.getName(), token);
    }

    public UUID getUserIdFromToken() {
        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof User user)) {
            throw new RuntimeException("User not authenticated");
        }
        return user.getId();
    }
}
