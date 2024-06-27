package dev.dnnr.api.controller;

import dev.dnnr.api.domain.todo.Todo;
import dev.dnnr.api.domain.todo.TodoCreateDTO;
import dev.dnnr.api.domain.todo.TodoRequestDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.service.TodoService;
import dev.dnnr.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tools/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Todo> create(@RequestHeader("Authorization") String token, @RequestBody @Valid TodoRequestDTO body){
        User user = userService.getAuthenticatedUser(token);
        var newTodoDTO = new TodoCreateDTO(body.description(), body.completed(), user.getId());
        Todo newTodo = this.todoService.createTodo(newTodoDTO);
        return ResponseEntity.ok(newTodo);
    }

    @GetMapping
    public ResponseEntity<Optional<List<Todo>>> getAllByUser(@RequestHeader("Authorization") String token){
        User user = userService.getAuthenticatedUser(token);
        Optional<List<Todo>> todoList = this.todoService.getTodoByUserId(user.getId());
        return ResponseEntity.ok(todoList);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @Valid UUID todo_id){
        this.todoService.deleteTodo(todo_id);
        return ResponseEntity.ok(null);
    }
}