package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.domain.user.UserRequestDto;
import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.domain.user.UserUpdateDto;
import com.devalrykemes.forumhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto getUserById(UUID userId) throws IllegalArgumentException {
        return new UserResponseDto(this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found: " + userId)));
    }

    public UserResponseDto createUser(UserRequestDto user) throws IllegalArgumentException {
        if (user.email().contains("@")) {
            if(user.password().equals(user.confirmPassword())) {
                if(this.userRepository.existsByEmail(user.email())) {
                    throw new IllegalArgumentException("Email already exists");
                }
                User newUser = this.userRepository.save(new User(user));
                return new UserResponseDto(newUser);
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    public UserResponseDto updateUser(UserUpdateDto user) throws IllegalArgumentException {
        if (user.email().contains("@")) {
            if(user.password().equals(user.confirmPassword())) {
                this.userRepository.updateUser(user.id(), user.name(), user.email(), user.password());
                return this.getUserById(user.id());
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    public void deleteUser(UUID userId) {
        this.getUserById(userId);
        this.userRepository.deleteById(userId);
    }

    public User userforProfileById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found: " + userId));
    }
}
