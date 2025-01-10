package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.domain.user.UserRequestDto;
import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto user) throws IllegalArgumentException {
        if (user.email().contains("@")) {
            if(user.password().equals(user.confirmPassword())) {
                if(userRepository.existsByEmail(user.email())) {
                    throw new IllegalArgumentException("Email already exists");
                }
                User newUser = userRepository.save(new User(user));
                return new UserResponseDto(newUser);
            } else {
                throw new IllegalArgumentException("Passwords do not match");
            }
        } else {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    public UserResponseDto updateUser(UserRequestDto user) throws IllegalArgumentException {
        return null;
    }
}
