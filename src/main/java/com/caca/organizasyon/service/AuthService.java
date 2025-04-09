package com.caca.organizasyon.service;

import com.caca.organizasyon.dto.AuthRequest;
import com.caca.organizasyon.dto.AuthResponse;
import com.caca.organizasyon.dto.UserResponse;

public interface AuthService {

    UserResponse register(AuthRequest request);

    AuthResponse authenticate(AuthRequest request);

}
