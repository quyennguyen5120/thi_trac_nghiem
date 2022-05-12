package com.example.todoapi.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnswerDTO {
    private Long id;
    private String answer_content;
    private Long question_id;

}
