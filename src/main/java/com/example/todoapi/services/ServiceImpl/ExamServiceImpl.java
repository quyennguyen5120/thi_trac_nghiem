package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.entities.ExamEntity;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public List<ExamDTO> getAll() {
        return examRepository.findAll().stream().map(examEntity -> new ExamDTO().toDTO(examEntity)).collect(Collectors.toList());
    }

    @Override
    public ExamDTO getByID(Long id) {
        return new ExamDTO().toDTO(examRepository.getById(id));
    }

    @Override
    public void insertNew(ExamDTO examDTO) {
        Set<QuestionEntity> questionEntitySet = new HashSet<>();
        examDTO.getQuestionId().forEach(questionEntityId -> {
            QuestionEntity question = questionRepository.getById(questionEntityId);
            questionEntitySet.add(question);
        });
        ExamEntity examEntity = new ExamEntity();
        examEntity.setExam_name(examDTO.getExam_name());
        examEntity.setQuestionEntities(questionEntitySet);
        examEntity.setTime_limit(examDTO.getTime_limit());
        examRepository.save(examEntity);
    }

    @Override
    public void updateOld(ExamDTO examDTO) {
        Set<QuestionEntity> questionEntitySet = new HashSet<>();
        examDTO.getQuestionId().forEach(questionEntityId -> {
            QuestionEntity question = questionRepository.getById(questionEntityId);
            questionEntitySet.add(question);
        });
        ExamEntity examEntity = new ExamEntity();
        examEntity.setId(examDTO.getId());
        examEntity.setExam_name(examDTO.getExam_name());
        examEntity.setQuestionEntities(questionEntitySet);
        examEntity.setTime_limit(examDTO.getTime_limit());
        examRepository.save(examEntity);
    }

    @Override
    public void deleteOld(Long id) {
        examRepository.deleteById(id);
    }
}
