package com.example.todoapi.controllers;


import com.example.todoapi.dtos.CalculatorDto;
import com.example.todoapi.services.CalculatorMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class RestCalculatorMarkController {


    @Autowired
    CalculatorMarkService calculatorMarkService;

    @PostMapping("/")
    public void calculator(@RequestBody CalculatorDto calculatorDto){
        calculatorMarkService.calculartorMark(calculatorDto);
    }
}
