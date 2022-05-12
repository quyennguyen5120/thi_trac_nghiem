package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.entities.AnswerEntity;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.repositories.AnswerRepository;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ExamRepository examRepository;

    @Override
    public List<QuestionDto> getAll() {
        return questionRepository.findAll().stream().map(questionEntity -> new QuestionDto(questionEntity)).collect(Collectors.toList());
    }

    @Override
    public QuestionDto getByID(Long id) {
        return new QuestionDto(questionRepository.getById(id));
    }

    @Override
    public void insertNew(QuestionDto questionDto) {
        Set<AnswerEntity> answerEntitySet = new HashSet<>();

        if  (questionDto.getAnswerDTOS() != null){
            questionDto.getAnswerDTOS().forEach(answerDTO -> {
                AnswerEntity answerEntity = answerRepository.getById(answerDTO.getId());
                answerEntitySet.add(answerEntity);
            });
        }

        QuestionEntity questionEntity = QuestionEntity.builder()
                .question_content(questionDto.getQuestion_content())
                .question_type(questionDto.getQuestion_type())
                .answerEntitySet(answerEntitySet)
                .exam(questionDto.getExamDto() != null ? examRepository.getById(questionDto.getExamDto().getId()) : null)
                .mark(questionDto.getMark())
                .video_url(questionDto.getVideo_url())
                .image_url(questionDto.getImage_url())
                .audio_url(questionDto.getAudio_url())
                .build();
        questionRepository.save(questionEntity);
        }else {
            QuestionEntity questionEntity = QuestionEntity.builder()
                    .question_content(questionDto.getQuestion_content())
                    .question_type(questionDto.getQuestion_type())
                    .answerEntitySet(answerEntitySet)
                    .mark(questionDto.getMark())
                    .video_url(questionDto.getVideo_url())
                    .image_url(questionDto.getImage_url())
                    .audio_url(questionDto.getAudio_url())
                    .build();
            questionRepository.save(questionEntity);
        }
    }

    @Override
    public void updateOld(QuestionDto questionDto) {
        Set<AnswerEntity> answerEntitySet = new HashSet<>();
        if  (questionDto.getAnswerDTOS() != null){
            questionDto.getAnswerDTOS().forEach(answerDTO -> {
                AnswerEntity answerEntity = answerRepository.getById(answerDTO.getId());
                answerEntitySet.add(answerEntity);
            });
        }
        QuestionEntity questionEntity = QuestionEntity.builder()
                .id(questionDto.getId())
                .question_content(questionDto.getQuestion_content())
                .question_type(questionDto.getQuestion_type())
                .answerEntitySet(answerEntitySet)
                .exam(questionDto.getExamDto() != null ? examRepository.getById(questionDto.getExamDto().getId()) : null)
                .mark(questionDto.getMark())
                .video_url(questionDto.getVideo_url())
                .image_url(questionDto.getImage_url())
                .audio_url(questionDto.getAudio_url())
                .build();
        questionRepository.save(questionEntity);
    }

    @Override
    public void deleteOld(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<QuestionDto> getQuestionByExamId(Long examId) {
        return questionRepository.findAllByExamId(examId).stream()
                .map(questionEntity -> new QuestionDto(questionEntity)).collect(Collectors.toList());
    }
}
