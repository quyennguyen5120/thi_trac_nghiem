package com.example.todoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calculator_newDto {
    private Long userId;
    private Long examId;
    private List<Question_newDto> lstQuestion;
}
