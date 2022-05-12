package com.example.todoapi.dtos;

import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.entities.ResultEntity;
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
    private QuestionDto questionDto;

    public ResultDTO(ResultEntity resultEntity){
        this.id = resultEntity.getId();
        this.mark =resultEntity.getMark();
        this.questionDto = new QuestionDto(resultEntity.getQuestion());
    }

}
