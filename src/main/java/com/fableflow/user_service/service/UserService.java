package com.fableflow.user_service.service;

import com.fableflow.user_service.dto.CreateUserRequest;
import com.fableflow.user_service.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    Page<UserResponse> getAllUsers(int page,int size);
}
