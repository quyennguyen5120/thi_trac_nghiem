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

import java.io.LineNumberReader;

@RequestMapping("/api/question")
@RestController
public class RestQuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> addQuestion(@PathVariable("id") Long id){
        if (questionService.getByID(id)!=null){
            return ResponseEntity.ok(questionService.getByID(id));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto){
        if (questionDto != null){
            questionService.insertNew(questionDto);
            return ResponseEntity.ok(questionDto);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editQuestion(@PathVariable("id") Long id,@RequestBody QuestionDto questionDto){
        if (questionDto != null){
            questionDto.setId(id);
            questionService.updateOld(questionDto);
            return ResponseEntity.ok(questionDto);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id){
        if (questionService.getByID(id) != null){
            questionService.deleteOld(id);
            return ResponseEntity.ok(id);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/getQuestionByExamId/{examId}")
    public ResponseEntity<?> getQuestionByExam(@PathVariable("examId")Long examId){
        return ResponseEntity.ok(questionService.getQuestionByExamId(examId));
    }
}
