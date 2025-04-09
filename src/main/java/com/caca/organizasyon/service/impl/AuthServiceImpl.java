package com.caca.organizasyon.service.impl;

import com.caca.organizasyon.dto.AuthRequest;
import com.caca.organizasyon.dto.AuthResponse;
import com.caca.organizasyon.dto.UserResponse;
import com.caca.organizasyon.entity.Role;
import com.caca.organizasyon.entity.User;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.security.jwt.JwtTokenProvider;
import com.caca.organizasyon.repository.UserRepository;
import com.caca.organizasyon.service.AuthService;
import com.caca.organizasyon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UserResponse register(AuthRequest request) {
        return userService.save(request, Role.USER);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse authenticate(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException(ErrorCode.BAD_CREDENTIALS.getDescription()));

        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
        );

        if (!user.isApproved()) {
            throw new AccessDeniedException(ErrorCode.USER_NOT_APPROVED.getDescription());
        }


        String accessToken = jwtTokenProvider.generateToken(user);

        return new AuthResponse(accessToken);
    }
}
