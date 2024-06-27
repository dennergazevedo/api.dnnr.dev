package dev.dnnr.api.service;

import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.domain.user.UserRequestDTO;
import dev.dnnr.api.infra.security.TokenService;
import dev.dnnr.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public User createUser(UserRequestDTO data){
        User newUser = new User();
        newUser.setEmail(data.email());
        newUser.setPassword(data.password());
        newUser.setFirstName(data.firstName());
        newUser.setLastName(data.lastName());
        newUser.setRoles(data.roles());

        userRepository.save(newUser);
        return newUser;
    }

    public User findUserById(UUID id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElse(null);
    }

    public Void deleteUser(UUID id){
        this.userRepository.deleteById(id);
        return null;
    }

    public User getAuthenticatedUser(String token){
        var email = tokenService.verifyToken(token.replace("Bearer ", ""));
        return this.userRepository.findAllByEmail(email);
    }
}
