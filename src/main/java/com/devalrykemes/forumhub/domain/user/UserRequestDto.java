package com.devalrykemes.forumhub.domain.user;

public record UserRequestDto(String name, String email, String password, String confirmPassword) {
}
