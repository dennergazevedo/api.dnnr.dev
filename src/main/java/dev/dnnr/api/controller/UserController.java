package dev.dnnr.api.controller;

import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.domain.user.UserRequestDTO;
import dev.dnnr.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserRequestDTO body){
        User newUser = this.userService.createUser(body);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam UUID id){
        User currentUser = this.userService.findUserById(id);
        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam UUID id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
}
