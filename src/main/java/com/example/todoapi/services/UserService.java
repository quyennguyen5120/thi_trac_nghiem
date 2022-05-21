package com.example.todoapi.services;


import com.example.todoapi.dtos.SignupRequest;
import com.example.todoapi.entities.RoleEntity;
import com.example.todoapi.entities.UserEntity;
import com.example.todoapi.repositories.RoleRepository;
import com.example.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity addNewUser(SignupRequest signupRequest){
        RoleEntity roleUser = roleRepository.findByName("ROLE_USER");
        Optional<UserEntity> existUser = userRepository.findByUsername(signupRequest.getUsername());
        Optional<UserEntity> existUserEmail = userRepository.findByEmail(signupRequest.getEmail());
        if (existUser.isEmpty() && existUserEmail.isEmpty()){
            UserEntity user = new UserEntity();
            user.setUsername(signupRequest.getUsername());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setEmail(signupRequest.getEmail());
            user.setRoles(Set.of(roleUser));
            user.setFullname(signupRequest.getFullname());
            user.setAge(signupRequest.getAge());
            userRepository.save(user);
            return user;
        }
        return existUser.orElseGet(existUserEmail::get);
    }
}
