package com.example.todoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingDto {
    private String username;
    private String fullname;
    private String examName;
    private Integer age;
    private String email;
    private Double totalScore;
    private ExamDTO examDTO;
}
