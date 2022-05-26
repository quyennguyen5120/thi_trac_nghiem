package com.example.todoapi.controllers;

import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.repositories.ResultRepository;
import com.example.todoapi.services.AnswerService;
import com.example.todoapi.services.QuestionService;
import com.example.todoapi.services.ServiceImpl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.LineNumberReader;

@RequestMapping("/api/question")
@RestController
public class RestQuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<?> addQuestion(@PathVariable("id") Long id){
        if (questionService.getByID(id)!=null){
            return ResponseEntity.ok(questionService.getByID(id));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@ModelAttribute QuestionDto questionDto) throws IOException {
        if (questionDto != null){
            questionService.insertNew(questionDto);
            if(questionDto.getFile() != null && !questionDto.getFile().isEmpty())
                questionDto.setFile(null);
            return ResponseEntity.ok(questionDto);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editQuestion(@PathVariable("id") Long id,@RequestBody QuestionDto questionDto){
        if (questionDto != null){
            questionDto.setId(id);
            questionService.updateOld(questionDto);
            return ResponseEntity.ok(questionDto);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long id){
        if(resultRepository.countRRz(id) > 0){
            return ResponseEntity.ok(false);
        }
        questionRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/getQuestionByExamId/{examId}")
    public ResponseEntity<?> getQuestionByExam(@PathVariable("examId")Long examId){
        return ResponseEntity.ok(questionService.getQuestionByExamId(examId));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/getById/{questionId}")
    public ResponseEntity<?> getQuestionById(@PathVariable("questionId")Long questionId){
        return ResponseEntity.ok(questionService.getByID(questionId));
    }
}
