package dev.dnnr.api.controller;

import dev.dnnr.api.domain.todo.Todo;
import dev.dnnr.api.domain.todo.TodoCreateDTO;
import dev.dnnr.api.domain.todo.TodoRequestDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.infra.security.TokenService;
import dev.dnnr.api.repositories.UserRepository;
import dev.dnnr.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Todo> create(@RequestHeader("Authorization") String token, @RequestBody TodoRequestDTO body){
        var email = tokenService.verifyToken(token.replace("Bearer ", ""));
        UserDetails user = userRepository.findByEmail(email);
        var newTodoDTO = new TodoCreateDTO(body.description(), body.completed(), user.getUsername());
        Todo newTodo = this.todoService.createTodo(newTodoDTO);
        return ResponseEntity.ok(newTodo);
    }

    @GetMapping
    public ResponseEntity<Optional<List<Todo>>> getAllByUser(@RequestParam UUID user_id){
        Optional<List<Todo>> todoList = this.todoService.getTodoByUserId(user_id);
        return ResponseEntity.ok(todoList);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam UUID todo_id){
        this.todoService.deleteTodo(todo_id);
        return ResponseEntity.ok(null);
    }
}