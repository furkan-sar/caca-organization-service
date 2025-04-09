package com.caca.organizasyon.service;

import com.caca.organizasyon.dto.AuthRequest;
import com.caca.organizasyon.dto.UserResponse;
import com.caca.organizasyon.entity.Role;

import java.util.List;

public interface UserService {

    UserResponse save(AuthRequest request, Role role);

    List<UserResponse> findAll();

    UserResponse findById(Long userId);

    void approveUser(Long userId);

    UserResponse makeUserAdmin(Long userId);

    UserResponse removeAdminRole(Long userId);

}
