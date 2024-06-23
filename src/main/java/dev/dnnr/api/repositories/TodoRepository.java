package dev.dnnr.api.repositories;

import dev.dnnr.api.domain.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
    public List<Todo> findByUserId(UUID userId);
}
