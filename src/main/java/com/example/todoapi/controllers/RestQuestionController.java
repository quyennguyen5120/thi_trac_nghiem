package com.example.todoapi.controllers;

import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.services.ServiceImpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/question")
@RestController
public class RestQuestionController {
    @Autowired
    QuestionServiceImpl questionServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto){
        if (questionDto != null){
            questionServiceImpl.insertNew(questionDto);
            return ResponseEntity.ok(questionDto);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/getQuestionByExamId/{examId}")
    public ResponseEntity<?> getQuestionByExam(@PathVariable("examId")Long examId){
        return ResponseEntity.ok(questionServiceImpl.getQuestionByExamId(examId));
    }
}
