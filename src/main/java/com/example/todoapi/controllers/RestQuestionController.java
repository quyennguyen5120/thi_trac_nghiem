package com.example.todoapi.controllers;

import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.AnswerService;
import com.example.todoapi.services.QuestionService;
import com.example.todoapi.services.ServiceImpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/question")
@RestController
public class RestQuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto){
        if (questionDto != null){
            questionService.insertNew(questionDto);
            return ResponseEntity.ok(questionDto);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/getQuestionByExamId/{examId}")
    public ResponseEntity<?> getQuestionByExam(@PathVariable("examId")Long examId){
        return ResponseEntity.ok(questionService.getQuestionByExamId(examId));
    }

    @GetMapping("/getById/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable("questionId")Long questionId){
        return ResponseEntity.ok(questionService.getByID(questionId));
    }
}
