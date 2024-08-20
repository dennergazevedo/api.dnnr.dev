package dev.dnnr.api.domain.todo;

import java.util.UUID;

public record TodoListDTO(String description, Boolean completed, UUID id) {}
