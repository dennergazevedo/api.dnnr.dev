package dev.dnnr.api.domain.todo;

import java.util.UUID;

public record TodoUpdateDTO(String description, Boolean completed, UUID id) {}
