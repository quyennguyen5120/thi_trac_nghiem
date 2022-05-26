package com.example.todoapi.dtos;

import com.example.todoapi.entities.ExamEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDTO {
    private Long id;
    private String exam_name;
    private List<QuestionDto> questionDtos;
    private Long time_limit;
    private List<ResultDTO> resultDTOS;
    private Double totalScore;

    public ExamDTO toDTO(ExamEntity examEntity){
        if(examEntity != null){
            List<QuestionDto> questionDtos = new ArrayList<>();
            if(examEntity.getQuestionEntities() != null){
                examEntity.getQuestionEntities().forEach(questionEntity -> {
                    QuestionDto questionDto = new QuestionDto(questionEntity);
                    questionDtos.add(questionDto);
                });
            }

            return ExamDTO.builder()
                    .id(examEntity.getId())
                    .exam_name(examEntity.getExam_name())
                    .questionDtos(questionDtos)
                    .time_limit(examEntity.getTime_limit())
                    .build();
        }
        return null;
    }
    public ExamDTO(Long id, String exam_name){
        this.id = id;
        this.exam_name = exam_name;
    }
    public ExamDTO(ExamEntity examEntity){
        if(examEntity != null){
            this.id = id;
            this.exam_name = exam_name;
        }
    }
}
