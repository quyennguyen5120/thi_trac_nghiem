package com.example.todoapi.controllers;

import com.example.todoapi.dtos.UserDTO;
import com.example.todoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class RestUserController {

    @Autowired
    UserRepository userRepository;

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")Long id){
        return ResponseEntity.ok(new UserDTO(userRepository.getById(id)));
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(value = "/findAll")
    public ResponseEntity<?> getAllById(){
        return ResponseEntity.ok(userRepository.findAll().stream().map(x-> new UserDTO(x)));
    }


}
