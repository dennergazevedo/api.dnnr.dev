package dev.dnnr.api.domain.todo;

import java.util.UUID;

public record TodoCreateDTO(String description, Boolean completed, UUID user_id) {
}
