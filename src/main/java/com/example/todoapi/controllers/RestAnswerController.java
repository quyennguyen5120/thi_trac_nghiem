package com.example.todoapi.controllers;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.services.AnswerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/answer")
@RestController
public class RestAnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping(produces = "application/json",value = "/all")
    public ResponseEntity<?> getAllAnswer(){
        return ResponseEntity.ok(answerService.getAll());
    }
    @GetMapping(produces = "application/json",value = "/{id}")
    public ResponseEntity<?> getAnswerByID(@PathVariable("id") Long id){
        return ResponseEntity.ok(answerService.getByID(id));
    }
    @PostMapping(produces = "application/json",value = "/add")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDTO answerDTO){
        answerService.insertNew(answerDTO);
        return ResponseEntity.ok(answerDTO);
    }
    @PutMapping(produces = "application/json",value = "/edit/{id}")
    public ResponseEntity<?> editAnswer(@PathVariable("id") Long id, @RequestBody AnswerDTO answerDTO){
        answerDTO.setId(id);
        answerService.updateOld(answerDTO);
        return ResponseEntity.ok(answerDTO);
    }
    @DeleteMapping(produces = "application/json",value = "/delete/{id}")
    public ResponseEntity<?> delAnswer(@PathVariable("id") Long id){
        String response = answerService.getByID(id).toString();
        answerService.deleteOld(id);
        return ResponseEntity.ok(response);
    }
}
