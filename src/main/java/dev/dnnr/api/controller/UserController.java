package dev.dnnr.api.controller;

import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.domain.user.UserResponseDTO;
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

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam UUID id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserByToken(@RequestHeader("Authorization") String token){
        User user = userService.getAuthenticatedUser(token);
        UserResponseDTO responseDTO = new UserResponseDTO(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getRoles()
        );

        return ResponseEntity.ok(responseDTO);
    }
}
