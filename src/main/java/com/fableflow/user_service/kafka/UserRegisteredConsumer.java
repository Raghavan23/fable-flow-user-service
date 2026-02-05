package com.fableflow.user_service.kafka;

import com.fableflow.events.UserRegisteredEvent;
import com.fableflow.user_service.entity.User;
import com.fableflow.user_service.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredConsumer {

    private final UserRepository userRepository;

    public UserRegisteredConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "user-registered", groupId = "user-service")
    public void onUserRegistered(UserRegisteredEvent event) {

        // idempotency protection
        if (userRepository.findByEmail(event.getEmail()).isPresent()) {
            return;
        }

        User user = User.builder()
                .email(event.getEmail())
                .name(event.getName())
                .active(true)
                .build();

        userRepository.save(user);
    }
}
