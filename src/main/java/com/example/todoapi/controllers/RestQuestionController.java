package com.example.todoapi.controllers;

import com.example.todoapi.services.ServiceImpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/question")
@RestController
public class RestQuestionController {
    @Autowired
    QuestionServiceImpl questionServiceImpl;

    @GetMapping
    public ResponseEntity<?> getQuestionByExam(@RequestParam("examId")Long examId){
        return ResponseEntity.ok(questionServiceImpl.getQuestionByExamId(examId));
    }
}
