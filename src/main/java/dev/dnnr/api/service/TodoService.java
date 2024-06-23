package dev.dnnr.api.service;

import dev.dnnr.api.domain.todo.Todo;
import dev.dnnr.api.domain.todo.TodoRequestDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.repositories.TodoRepository;
import dev.dnnr.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(TodoRequestDTO data){
        Optional<User> user = this.userRepository.findById(data.user_id());

        if(user.isPresent()){
            Todo newTodo = new Todo();
            Timestamp createdAt = Timestamp.from(Instant.now());

            newTodo.setUser(user.get());
            newTodo.setCreatedAt(createdAt);
            newTodo.setCompleted(data.completed());
            newTodo.setDescription(data.description());

            todoRepository.save(newTodo);
            return newTodo;
        }
        return null;
    }

    public Optional<List<Todo>> getTodoByUserId(UUID user_id){
        return Optional.ofNullable(this.todoRepository.findByUserId(user_id));
    }

    public void deleteTodo(UUID todo_id){
        this.todoRepository.deleteById(todo_id);
    }
}
