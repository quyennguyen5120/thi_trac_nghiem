package com.example.todoapi.dtos;

import com.example.todoapi.entities.QuestionEntity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResultDTO {
    private Long id;
    private Long question_id;
    private Double mark;
}
