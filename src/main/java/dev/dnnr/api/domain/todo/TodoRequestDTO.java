package dev.dnnr.api.domain.todo;

import java.util.UUID;

public record TodoRequestDTO(String description, Boolean completed, UUID user_id) {
}
