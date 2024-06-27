package dev.dnnr.api.domain.chronometer;

public record ChronometerRequestDTO(String description, Float duration, Boolean interrupted) {
}
