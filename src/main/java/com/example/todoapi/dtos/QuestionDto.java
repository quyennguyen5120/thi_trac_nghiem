package com.example.todoapi.dtos;

import com.example.todoapi.entities.AnswerEntity;
import com.example.todoapi.entities.ExamEntity;
import com.example.todoapi.entities.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String question_content;
    private int question_type;
    private List<AnswerEntity> answerEntitySet;
    private ExamEntity exam;
    private Double mark;
    private String video_url;
    private String image_url;
    private String audio_url;

    public QuestionDto(QuestionEntity question){
        if(question!= null){
        }
    }
}
