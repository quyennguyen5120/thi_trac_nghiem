package com.example.todoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingDto {
    private String username;
    private String fullname;
    private String examName;
    private Integer age;
    private String email;
    private List<ExamDTO> examDTO;
}
