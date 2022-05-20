package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.entities.AnswerEntity;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.repositories.AnswerRepository;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.AnswerService;
import com.example.todoapi.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    AnswerService answerService;
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
        QuestionEntity questionEntity = null;
        if(questionDto.getId() != null){
            questionEntity = questionRepository.getById(questionDto.getId());
        }
        if(questionEntity == null){
            questionEntity = new QuestionEntity();
        }
        questionEntity.setQuestion_type(questionDto.getQuestion_type());
        questionEntity.setQuestion_content(questionDto.getQuestion_content());
        questionEntity.setExam(questionDto.getExamDto() != null ? examRepository.getById(questionDto.getExamDto().getId()) : null);
        questionEntity.setMark(questionDto.getMark());
        questionEntity.setVideo_url(questionDto.getVideo_url());
        questionEntity.setAudio_url(questionDto.getAudio_url());
        questionEntity.setImage_url(questionDto.getImage_url());
//         questionEntity = QuestionEntity.builder()
//                .question_content(questionDto.getQuestion_content())
//                .question_type(questionDto.getQuestion_type())
//                .answerEntitySet(answerEntitySet)
//                .exam(questionDto.getExamDto() != null ? examRepository.getById(questionDto.getExamDto().getId()) : null)
//                .mark(questionDto.getMark())
//                .video_url(questionDto.getVideo_url())
//                .image_url(questionDto.getImage_url())
//                .audio_url(questionDto.getAudio_url())
//                .build();
        questionEntity.setAnswerEntitySet(null);
        questionEntity = questionRepository.save(questionEntity);
        List<AnswerEntity> answerEntities = new ArrayList<>();

        questionDto.setId(questionEntity.getId());
        if (questionDto.getAnswerDTOS()!=null){
            List<AnswerEntity> answerEntities1 = answerRepository.getByQuestionId(questionDto.getId());
            answerRepository.deleteAll(answerEntities1);
            for(AnswerDTO answerDTO : questionDto.getAnswerDTOS()){
                AnswerEntity answerEntity = null;
                if(answerDTO.getId() != null){
                    answerEntity = answerRepository.getById(answerDTO.getId());
                }
                if(answerEntity == null){
                    answerEntity = new AnswerEntity();
                }
                answerEntity.setAnswer_content(answerDTO.getAnswer_content());
                answerEntity.setIsRight(answerDTO.getIsright());
                answerEntity.setQuestion(questionEntity);
                answerEntity = answerRepository.save(answerEntity);
                answerEntities.add(answerEntity);
            }

        }
        if(questionDto.getId() != null){
            questionEntity.setAnswerEntitySet(answerEntities.stream().collect(Collectors.toSet()));
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
