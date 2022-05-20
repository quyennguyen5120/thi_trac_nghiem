package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.dtos.ResultDTO;
import com.example.todoapi.entities.ExamEntity;
import com.example.todoapi.entities.ResultEntity;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.repositories.ResultRepository;
import com.example.todoapi.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImp implements ResultService {
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ExamRepository examRepository;
    @Override
    public List<ResultDTO> getAll() {
        List<ResultEntity> resultEntities = resultRepository.findAll();
        List<ResultDTO> resultDTOS = new ArrayList<>();
        for (ResultEntity r: resultEntities
             ) {
            if(r.getQuestion()!=null){
                resultDTOS.add(ResultDTO.builder().question_id(r.getQuestion().getId()).id(r.getId()).mark(r.getMark()).build());
            }
            else {
                resultDTOS.add(ResultDTO.builder().id(r.getId()).mark(r.getMark()).build());
            }
        }
        return resultDTOS;
    }

    @Override
    public ResultDTO getByID(Long id) {
        ResultEntity r = resultRepository.findById(id).get();
        if(r.getQuestion()!=null){
            return ResultDTO.builder().question_id(r.getQuestion().getId()).id(r.getId()).mark(r.getMark()).build();
        }
        else {
            return ResultDTO.builder().id(r.getId()).mark(r.getMark()).build();
        }
    }

    @Override
    public void insertNew(ResultDTO resultDTO) {
        if(resultDTO.getQuestion_id()!=null){
            resultRepository.save(ResultEntity.builder().question(questionRepository.findById(resultDTO.getQuestion_id()).get()).mark(resultDTO.getMark()).build());
        }
        else {
            resultRepository.save(ResultEntity.builder().mark(resultDTO.getMark()).build());
        }
    }

    @Override
    public void updateOld(ResultDTO resultDTO) {
        if(resultDTO.getQuestion_id()!=null){
            resultRepository.save(ResultEntity.builder().id(resultDTO.getId()).question(questionRepository.findById(resultDTO.getQuestion_id()).get()).mark(resultDTO.getMark()).build());
        }
        else {
            resultRepository.save(ResultEntity.builder().id(resultDTO.getId()).mark(resultDTO.getMark()).build());
        }
    }

    @Override
    public void deleteOld(Long id) {
        if(resultRepository.findById(id).isPresent())
            resultRepository.delete(resultRepository.findById(id).get());
        else
            return;
    }

    @Override
    public List<ResultDTO> getQuestionDtos(Long userId, Long id) {
        return resultRepository.getAllByUserAndExam(userId, id);
    }

    @Override
    public List<ExamDTO> getAllExamByUser(Long userId) {
        List<ExamDTO> getAllExamByUser = examRepository.getAllExamByUser(userId);
        List<ResultDTO> results = null;
        for(ExamDTO e : getAllExamByUser){
            results = this.getQuestionDtos(userId , e.getId());
            e.setResultDTOS(results);
        }
        return getAllExamByUser;
    }
}
