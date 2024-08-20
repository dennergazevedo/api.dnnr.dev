package dev.dnnr.api.controller;

import dev.dnnr.api.domain.todo.Todo;
import dev.dnnr.api.domain.todo.TodoCreateDTO;
import dev.dnnr.api.domain.todo.TodoListDTO;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<List<TodoListDTO>> getAllByUser(@RequestHeader("Authorization") String token){
        User user = userService.getAuthenticatedUser(token);
        Optional<List<Todo>> todoList = this.todoService.getTodoByUserId(user.getId());

        List<TodoListDTO> todoListDTOs = todoList
                .map(todos -> todos.stream()
                        .map(todo -> new TodoListDTO(todo.getDescription(), todo.getCompleted(), todo.getId()))
                        .collect(Collectors.toList()))
                .orElse(List.of());

        return ResponseEntity.ok(todoListDTOs);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @Valid UUID todo_id){
        this.todoService.deleteTodo(todo_id);
        return ResponseEntity.ok(null);
    }

    @PatchMapping
    public ResponseEntity<Todo> update(@RequestParam @Valid UUID todo_id, @RequestBody @Valid TodoRequestDTO body){
        Optional<Todo> currentTodo = this.todoService.getTodoById(todo_id);
        if(currentTodo.isPresent()){
            TodoListDTO updatedTodo = new TodoListDTO(
                    body.description(),
                    body.completed(),
                    currentTodo.get().getId()
            );
            this.todoService.updateTodoById(todo_id, updatedTodo);
        }
        return ResponseEntity.ok(null);
    }
}