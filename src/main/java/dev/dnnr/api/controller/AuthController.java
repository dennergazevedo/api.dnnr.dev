package dev.dnnr.api.controller;

import dev.dnnr.api.domain.auth.AuthDTO;
import dev.dnnr.api.domain.auth.LoginResponseDTO;
import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.domain.user.UserRequestDTO;
import dev.dnnr.api.domain.user.UserResponseDTO;
import dev.dnnr.api.domain.user.UserRole;
import dev.dnnr.api.exceptions.AuthLoginException;
import dev.dnnr.api.infra.security.TokenService;
import dev.dnnr.api.repositories.UserRepository;
import dev.dnnr.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        if(token.isBlank()){
            throw new AuthLoginException();
        }

        User user = userService.getAuthenticatedUser(token);
        UserResponseDTO responseUser = new UserResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getRoles());

        return ResponseEntity.ok(new LoginResponseDTO(token, responseUser));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRequestDTO data){
        if(this.userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.firstName(), data.lastName(), data.email(), encryptedPassword, UserRole.USER);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
