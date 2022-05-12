package com.example.todoapi.services;

import com.example.todoapi.dtos.CalculatorDto;
import com.example.todoapi.dtos.ResultDTO;

import java.util.List;

public interface CalculatorMarkService {
    public List<ResultDTO> calculartorMark(CalculatorDto calculatorDto);
}
