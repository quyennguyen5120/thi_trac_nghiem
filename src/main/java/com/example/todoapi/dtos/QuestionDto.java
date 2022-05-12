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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String question_content;
    private int question_type;
    private List<AnswerDTO> answerDTOS;
    private List<Long> listIdAnswer;
    private Long idSingleQuestion;
    private ExamDTO examDto;
    private Double mark;
    private String video_url;
    private String image_url;
    private String audio_url;

    public QuestionDto(QuestionEntity question){
        if(question!= null){
            this.id = question.getId();
            if(question.getQuestion_content() != null)
                this.question_content = question.getQuestion_content();
            if(question.getQuestion_type() != -1)
                this.question_type = question.getQuestion_type();
            if(question.getMark() != null)
                this.mark = question.getMark();
            if(question.getVideo_url() != null)
                this.video_url = question.getVideo_url();
            if(question.getImage_url() != null)
                this.image_url = question.getImage_url();
            if(question.getAudio_url() != null){
                this.audio_url = question.getAudio_url();
            }
            if(question.getAnswerEntitySet() != null && question.getAnswerEntitySet().size() > 0){
                AnswerDTO answerDto = null;
                List<AnswerDTO> answerDTOS = new ArrayList<>();
                for(AnswerEntity answerEntity : question.getAnswerEntitySet()){
                    answerDto = new AnswerDTO();
                    answerDto.setId(answerEntity.getId());
                    answerDto.setAnswer_content(answerEntity.getAnswer_content());
                    answerDto.setQuestion_id(question.getId());
                    answerDto.setIsright(answerEntity.getIsRight());
                    answerDTOS.add(answerDto);
                }
                this.answerDTOS = answerDTOS;
            }
            if(question.getExam() != null){
                ExamDTO examDTO = new ExamDTO();
                examDTO.setId(question.getExam().getId());
                examDTO.setExam_name(question.getExam().getExam_name());
                examDTO.setTime_limit(question.getExam().getTime_limit());
                this.examDto = examDTO;
            }

        }
    }
}
