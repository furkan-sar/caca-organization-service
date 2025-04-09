package com.caca.organizasyon.service.impl;

import com.caca.organizasyon.dto.AuthRequest;
import com.caca.organizasyon.dto.UserResponse;
import com.caca.organizasyon.entity.Role;
import com.caca.organizasyon.entity.User;
import com.caca.organizasyon.exception.BusinessException;
import com.caca.organizasyon.exception.ErrorCode;
import com.caca.organizasyon.repository.UserRepository;
import com.caca.organizasyon.service.UserService;
import com.caca.organizasyon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        return userResponse;
    }

    @Override
    @Transactional
    public UserResponse save(AuthRequest request, Role role) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw BusinessException.of(ErrorCode.USERNAME_ALREADY_TAKEN);
        }

        User savedUser = userRepository.save(createUser(request, role));

        UserResponse createdUserResponse = new UserResponse();
        BeanUtils.copyProperties(savedUser, createdUserResponse);

        return createdUserResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        User sentRequestUser = userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> BusinessException.of(ErrorCode.USER_NOT_FOUND));

        if (SecurityUtil.isUser() && !userId.equals(sentRequestUser.getId())) {
            throw new AccessDeniedException(ErrorCode.FORBIDDEN.getDescription());
        }

        return toDto(userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    @Transactional
    public void approveUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(ErrorCode.USER_NOT_FOUND));

        if(user.isApproved()){
            throw BusinessException.of(ErrorCode.USER_ALREADY_APPROVED);
        }

        user.setApproved(true);
        userRepository.save(user);
    }

    private UserResponse updateRole(User user, Role role) {
        user.setRole(role);
        User updatedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(updatedUser, response);
        return response;
    }

    @Override
    @Transactional
    public UserResponse makeUserAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(ErrorCode.USER_NOT_FOUND));

        if (user.getRole().equals(Role.ADMIN)) {
            throw BusinessException.of(ErrorCode.USER_ALREADY_ADMIN);
        }

        return updateRole(user, Role.ADMIN);
    }

    @Override
    @Transactional
    public UserResponse removeAdminRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> BusinessException.of(ErrorCode.USER_NOT_FOUND));

        if (!user.getRole().equals(Role.ADMIN)) {
            throw BusinessException.of(ErrorCode.USER_NOT_ADMIN);
        }

        return updateRole(user, Role.USER);
    }

    private User createUser(AuthRequest request, Role role) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setApproved(false);

        return user;
    }

}
