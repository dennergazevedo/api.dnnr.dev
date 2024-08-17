package dev.dnnr.api.domain.user;

public record UserResponseDTO(String firstName, String lastName, String email, UserRole roles) {
}