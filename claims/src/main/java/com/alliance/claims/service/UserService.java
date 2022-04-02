package com.alliance.claims.service;

import com.alliance.claims.entity.User;
import com.alliance.claims.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public User addUser(User user){

        Optional<User> tempUser = userRepository.findByEmail(user.getEmail());
            if(!tempUser.isPresent()) {
                return userRepository.save(user);
            }
        throw new IllegalStateException("Email already exist bitch");
    }
}
