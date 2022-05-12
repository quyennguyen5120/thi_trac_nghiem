package com.example.todoapi.dtos;

import com.example.todoapi.entities.ExamEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDTO {
    private Long id;
    private String exam_name;
    private List<QuestionDto> questionDtos;
    private Long time_limit;

    public ExamDTO toDTO(ExamEntity examEntity){
        List<QuestionDto> questionDtos = new ArrayList<>();
        examEntity.getQuestionEntities().forEach(questionEntity -> {
            QuestionDto questionDto = new QuestionDto(questionEntity);
            questionDtos.add(questionDto);
        });

        return ExamDTO.builder()
                .id(examEntity.getId())
                .exam_name(examEntity.getExam_name())
                .questionDtos(questionDtos)
                .time_limit(examEntity.getTime_limit())
                .build();
    }
}
