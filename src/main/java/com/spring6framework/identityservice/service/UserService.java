package com.spring6framework.identityservice.service;

import java.util.List;
import java.util.UUID;

import com.spring6framework.identityservice.dto.request.UserCreationRequest;
import com.spring6framework.identityservice.dto.request.UserUpdateRequest;
import com.spring6framework.identityservice.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);

    UserResponse updateUser(UUID userId, UserUpdateRequest request);

    void deleteUser(UUID userId);

    List<UserResponse> getUsers();

    UserResponse getUser(UUID id);

    UserResponse getMyInfo();
}
