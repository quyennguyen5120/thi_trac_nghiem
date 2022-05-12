package com.example.todoapi.dtos;

import com.example.todoapi.entities.AnswerEntity;
import lombok.*;

import java.util.List;

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

    public AnswerDTO(AnswerEntity answerEntity){
       if(answerEntity != null){
           this.id = answerEntity.getId();
           this.answer_content = answerEntity.getAnswer_content();
           this.question_id = answerEntity.getQuestion().getId();
       }
    }
}
