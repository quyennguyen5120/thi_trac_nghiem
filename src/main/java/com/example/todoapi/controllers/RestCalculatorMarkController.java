package com.example.todoapi.controllers;


import com.example.todoapi.dtos.CalculatorDto;
import com.example.todoapi.dtos.Calculator_newDto;
import com.example.todoapi.dtos.ResultDTO;
import com.example.todoapi.services.CalculatorMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
public class RestCalculatorMarkController {

    @Autowired
    CalculatorMarkService calculatorMarkService;

    @Secured({"ROLE_USER"})
    @PostMapping("/")
    public ResponseEntity<?> calculator(@RequestBody Calculator_newDto calculatorDto){
        return ResponseEntity.ok(calculatorMarkService.calculartorMark(calculatorDto));
    }
}
