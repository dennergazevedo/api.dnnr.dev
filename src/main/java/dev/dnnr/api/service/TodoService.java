package dev.dnnr.api.service;

import dev.dnnr.api.domain.todo.Todo;
import dev.dnnr.api.domain.todo.TodoCreateDTO;
import dev.dnnr.api.domain.todo.TodoListDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.repositories.TodoRepository;
import dev.dnnr.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
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

    public Todo createTodo(TodoCreateDTO data){
        Optional<User> user = this.userRepository.findById(data.user_id());

        Todo newTodo = new Todo();
        Timestamp createdAt = Timestamp.from(Instant.now());

        if(user.isPresent()){
            newTodo.setUser(user.get());
            newTodo.setCreatedAt(createdAt);
            newTodo.setCompleted(data.completed());
            newTodo.setDescription(data.description());

            todoRepository.save(newTodo);
            return newTodo;
        }else return null;
    }

    public Optional<List<Todo>> getTodoByUserId(UUID user_id){
        return Optional.ofNullable(this.todoRepository.findByUserId(user_id));
    }

    public void deleteTodo(UUID todo_id){
        this.todoRepository.deleteById(todo_id);
    }

    public Optional<Todo> getTodoById(UUID todo_id){
        return this.todoRepository.findById(todo_id);
    }

    @Transactional
    public void updateTodoById(UUID id, TodoListDTO todoUpdateDTO) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setDescription(todoUpdateDTO.description());
            todo.setCompleted(todoUpdateDTO.completed());
            todoRepository.save(todo);
        }
    }
}
