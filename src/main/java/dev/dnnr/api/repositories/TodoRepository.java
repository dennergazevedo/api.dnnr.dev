package dev.dnnr.api.repositories;

import dev.dnnr.api.domain.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
}
