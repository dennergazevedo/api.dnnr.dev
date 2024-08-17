package dev.dnnr.api.domain.auth;

import dev.dnnr.api.domain.user.UserResponseDTO;

public record LoginResponseDTO(String token, UserResponseDTO user) {
}
