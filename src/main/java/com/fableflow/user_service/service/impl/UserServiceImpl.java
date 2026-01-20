package com.fableflow.user_service.service.impl;

import com.fableflow.user_service.dto.CreateUserRequest;
import com.fableflow.user_service.dto.UserResponse;
import com.fableflow.user_service.entity.User;
import com.fableflow.user_service.exception.DuplicateUserException;
import com.fableflow.user_service.exception.UserNotFoundException;
import com.fableflow.user_service.repository.UserRepository;
import com.fableflow.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        userRepository.findByEmail(request.getEmail())
            .ifPresent(user -> {
                throw new DuplicateUserException(request.getEmail());
            });

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return mapToResponse(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(int page, int size) {

        PageRequest pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
