package dev.dnnr.api.domain.chronometer;

import java.util.UUID;

public record ChronometerCreateDTO(String description,Float duration, Boolean interrupted, UUID user_id) {
}
