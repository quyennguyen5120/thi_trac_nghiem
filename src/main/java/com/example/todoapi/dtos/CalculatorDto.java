package com.example.todoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorDto {
    private Long userId;
    private Long examId;
    private List<QuestionDto> lstQuestion;
}
