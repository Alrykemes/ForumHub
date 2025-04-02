package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.domain.user.UserUpdateDto;
import com.devalrykemes.forumhub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto getUserById(UUID userId) throws IllegalArgumentException {
        return new UserResponseDto(this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found: " + userId)));
    }

    public UserResponseDto updateUser(UserUpdateDto user) throws IllegalArgumentException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(this.userforProfileById(user.id()).getEmail().equals(userEmail)) {
            if(this.userRepository.existsByEmail(user.email())) {
                throw new IllegalArgumentException("Email already exists");
            }
            if(user.password().equals(user.confirmPassword())) {
                this.userRepository.updateUser(user.id(), user.name(), user.email(), passwordEncoder.encode(user.password()));
                return this.getUserById(user.id());
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("This user has not been created by this user!");
        }
    }

    public void deleteUser(UUID userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(this.userforProfileById(userId).getEmail().equals(userEmail)) {
            this.userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("This user has not been created by this user!");
        }
    }

    public User userforProfileById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("user not found: " + userId));
    }
}
