package com.example.todoapi.controllers;

import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/api/category")
@RestController
public class RestExamController {
    @Autowired
    ExamService examService;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<?> getAllExam(){
        return ResponseEntity.ok(examService.getAll());
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable("id")Long id){
        return ResponseEntity.ok(examService.getByID(id));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add")
    public ResponseEntity<?> addExam(@RequestBody ExamDTO examDTO) throws IOException {
        if (examDTO != null){
            examService.insertNew(examDTO);
            return ResponseEntity.ok(examDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/update")
    public ResponseEntity<?> updateExam(@RequestBody ExamDTO examDTO){
        if (examDTO != null){
            examService.updateOld(examDTO);
            return ResponseEntity.ok(examDTO);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Long id){
        examService.deleteOld(id);
        return ResponseEntity.ok(id);
    }
}
