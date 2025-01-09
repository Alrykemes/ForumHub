package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.domain.user.UserRequestDto;
import com.devalrykemes.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequestDto user) throws IllegalArgumentException {
        if(user.password().equals(user.confirmPassword())) {
            userRepository.save(new User(user));
        } else {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }
}
