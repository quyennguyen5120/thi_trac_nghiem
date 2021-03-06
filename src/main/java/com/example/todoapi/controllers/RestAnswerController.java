package com.example.todoapi.controllers;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.services.AnswerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDTO answerDTO) throws IOException {
        answerService.insertNew(answerDTO);
        return ResponseEntity.ok(answerDTO);
    }
    @PutMapping(produces = "application/json",value = "/edit/{id}")
    public ResponseEntity<?> editAnswer(@PathVariable("id") Long id, @RequestBody AnswerDTO answerDTO){
        AnswerDTO answerDTO1 = answerService.getByID(id);
        answerDTO.setId(id);
        if(answerDTO.getIsright()!=null)
        answerDTO1.setIsright(answerDTO.getIsright());
        if(answerDTO.getAnswer_content()!=null){
            answerDTO1.setAnswer_content(answerDTO.getAnswer_content());
        }
        if(answerDTO.getQuestion_id()!=null){
            answerDTO1.setQuestion_id(answerDTO.getQuestion_id());
        }
        answerService.updateOld(answerDTO1);
        return ResponseEntity.ok(answerDTO1);

    }
    @DeleteMapping(produces = "application/json",value = "/delete/{id}")
    public ResponseEntity<?> delAnswer(@PathVariable("id") Long id){
        String response = answerService.getByID(id).toString();
        answerService.deleteOld(id);
        return ResponseEntity.ok(response);
    }
}
