package com.devalrykemes.forumhub.infra.dto;

import java.util.UUID;

public record ResponseDto(String token, UUID id, String name, String email) {
}
