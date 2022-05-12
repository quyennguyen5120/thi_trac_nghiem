package com.example.todoapi.dtos;

import com.example.todoapi.entities.ExamEntity;
import lombok.*;

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
    private Set<Long> questionId;
    private Long time_limit;

    public ExamDTO toDTO(ExamEntity examEntity){
        return ExamDTO.builder()
                .id(examEntity.getId())
                .exam_name(examEntity.getExam_name())
                .questionId(examEntity.getQuestionEntities().stream().map(q -> q.getId()).collect(Collectors.toSet()))
                .time_limit(examEntity.getTime_limit())
                .build();
    }
}
