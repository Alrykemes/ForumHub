package com.devalrykemes.forumhub.infra.security;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElse(userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email)));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
