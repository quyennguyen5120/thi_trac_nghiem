package com.example.todoapi.controllers;

import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/category")
@RestController
public class RestExamController {
    @Autowired
    ExamService examService;

    @GetMapping
    public ResponseEntity<?> getAllExam(){
        return ResponseEntity.ok(examService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable("id")Long id){
        return ResponseEntity.ok(examService.getByID(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExam(@RequestBody ExamDTO examDTO){
        if (examDTO != null){
            examService.insertNew(examDTO);
            return ResponseEntity.ok(examDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }

//    @PostMapping("/update")
//    public ResponseEntity<?> 
}
