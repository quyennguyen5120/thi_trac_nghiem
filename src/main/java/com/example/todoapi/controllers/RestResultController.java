package com.example.todoapi.controllers;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.dtos.ResultDTO;
import com.example.todoapi.services.AnswerService;
import com.example.todoapi.services.ResultService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping("/api/result")
@RestController
public class RestResultController {
    @Autowired
    ResultService resultService;

    @GetMapping(produces = "application/json",value = "/all")
    public ResponseEntity<?> getAllAnswer(){
        return ResponseEntity.ok(resultService.getAll());
    }
    @GetMapping(produces = "application/json",value = "/{id}")
    public ResponseEntity<?> getAnswerByID(@PathVariable("id") Long id){
        return ResponseEntity.ok(resultService.getByID(id));
    }
//    @PostMapping(produces = "application/json",value = "/add")
//    public ResponseEntity<?> addAnswer(@RequestBody ResultDTO resultDTO){
//        resultService.insertNew(resultDTO);
//        return ResponseEntity.ok(resultDTO.toString() +"added");
//    }
    @PutMapping(produces = "application/json",value = "/edit/{id}")
    public ResponseEntity<?> editAnswer(@PathVariable("id") Long id, @RequestBody ResultDTO resultDTO){
        ResultDTO resultDTO1 = resultService.getByID(id);
        resultDTO.setId(id);
        if(resultDTO.getQuestion_id()!=null)
            resultDTO1.setQuestion_id(resultDTO.getQuestion_id());
        if(resultDTO.getMark()!=null){
            resultDTO1.setMark(resultDTO.getMark());
        }
        resultService.updateOld(resultDTO1);
        return ResponseEntity.ok(resultDTO1);
    }
    @DeleteMapping(produces = "application/json",value = "/delete/{id}")
    public ResponseEntity<?> delAnswer(@PathVariable("id") Long id){
        String response = resultService.getByID(id).toString();
        resultService.deleteOld(id);
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/getResultByExamAndUser/{userId}/{examId}")
    public ResponseEntity<?> getResultByExam(@PathVariable("userId") Long userId,@PathVariable("examId") Long id){
            return ResponseEntity.ok(resultService.getQuestionDtos(userId, id));
    }
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/getAllResultByUser/{userId}")
    public ResponseEntity<?> getResultByExamAndUser(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(resultService.getAllExamByUser(userId));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/ranking")
    public ResponseEntity<?> ranking(){
        return ResponseEntity.ok(resultService.rankingUser());
    }
}
