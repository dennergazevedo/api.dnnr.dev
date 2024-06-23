package dev.dnnr.api.service;

import dev.dnnr.api.domain.user.User;
import dev.dnnr.api.domain.user.UserRequestDTO;
import dev.dnnr.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequestDTO data){
        User newUser = new User();
        newUser.setEmail(data.email());
        newUser.setPassword(data.password());
        newUser.setFirstName(data.firstName());
        newUser.setLastName(data.lastName());

        userRepository.save(newUser);
        return newUser;
    }
}
