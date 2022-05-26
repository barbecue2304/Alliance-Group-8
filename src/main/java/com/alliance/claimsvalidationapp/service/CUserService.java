package com.alliance.claimsvalidationapp.service;

import com.alliance.claimsvalidationapp.entity.User;
import com.alliance.claimsvalidationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CUserService {
    private final UserRepository userRepo;

    public User login(String email, String password){
        if(userRepo.findByEmail(email).isPresent()){
            User user = userRepo.findByEmail(email).get();
            if(new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                return user;
            }
            throw new IllegalStateException("Inccorect Password. Please retry.");
        }
        throw new IllegalStateException("User not found!");
    }
    public void addUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
    }
    public List<User> getAllUser(){
        return userRepo.findAll();
    }
    public User updateUser(Long id, User user){
        User oldRecord = userRepo.findById(id).get();
        oldRecord.setFirstName(user.getFirstName());
        oldRecord.setLastName(user.getLastName());
        return userRepo.save(oldRecord);
    }
    public User updateEmployeeUser(Long id, User user){
        User oldEmployeeRecord = userRepo.findById(id).get();
        oldEmployeeRecord.setFirstName(user.getFirstName());
        oldEmployeeRecord.setLastName(user.getLastName());
        oldEmployeeRecord.setEmail(user.getEmail());
        return userRepo.save(oldEmployeeRecord);
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
    public void editEmployeePassword(Long id, String curr_password, String new_password){
        User user = userRepo.findById(id).get();
        if(new BCryptPasswordEncoder().matches(curr_password, user.getPassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(new_password));
            userRepo.save(user);
        }
    }
    public void editAccountingPassword(Long id, String curr_password, String new_password){
        User user = userRepo.findById(id).get();
        if(new BCryptPasswordEncoder().matches(curr_password, user.getPassword())){
            user.setPassword(new BCryptPasswordEncoder().encode(new_password));
            userRepo.save(user);
        }
    }
}
