package com.example.todoapi.services.ServiceImpl;


import com.example.todoapi.dtos.AnswerDTO;


import com.example.todoapi.entities.AnswerEntity;
import com.example.todoapi.repositories.AnswerRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public List<AnswerDTO> getAll() {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        List<AnswerEntity> answerEntities = answerRepository.findAll();
        for (AnswerEntity a:answerEntities
             ) {
            if (a.getQuestion()!=null){
                answerDTOS.add(AnswerDTO.builder().isright(a.getIsRight()).question_id(a.getQuestion().getId()).answer_content(a.getAnswer_content()).id(a.getId()).build());
            }else {
                answerDTOS.add(AnswerDTO.builder().isright(a.getIsRight()).answer_content(a.getAnswer_content()).id(a.getId()).build());
            }

        }
        return answerDTOS;
    }

    @Override
    public AnswerDTO getByID(Long id) {
        AnswerEntity answer = answerRepository.findById(id).get();
        if(answer.getQuestion()== null){
            return AnswerDTO.builder().isright(answer.getIsRight()).answer_content(answer.getAnswer_content()).id(answer.getId()).build();

        }else {
            return AnswerDTO.builder().isright(answer.getIsRight()).answer_content(answer.getAnswer_content()).id(answer.getId()).question_id(answer.getQuestion().getId()).build();
        }
    }

    @Override
    public void insertNew(AnswerDTO answerDTO) {
        if(answerDTO.getQuestion_id()== null){
            answerRepository.save(AnswerEntity.builder().isRight(answerDTO.getIsright()).answer_content(answerDTO.getAnswer_content()).build());
        }else {
            answerRepository.save(AnswerEntity.builder().isRight(answerDTO.getIsright()).answer_content(answerDTO.getAnswer_content()).question(questionRepository.findById(answerDTO.getQuestion_id()).get()).build());
        }
    }

    @Override
    public void updateOld(AnswerDTO answerDTO) {
        if(answerDTO.getQuestion_id()== null){
            answerRepository.save(AnswerEntity.builder().isRight(answerDTO.getIsright()).answer_content(answerDTO.getAnswer_content()).id(answerDTO.getId()).build());
        }else {
            answerRepository.save(AnswerEntity.builder().isRight(answerDTO.getIsright()).answer_content(answerDTO.getAnswer_content()).id(answerDTO.getId()).question(questionRepository.findById(answerDTO.getQuestion_id()).get()).build());
        }
    }
    @Override
    public void deleteOld(Long id) {
        if(answerRepository.findById(id).isPresent())
        answerRepository.delete(answerRepository.findById(id).get());
        else
            return;
    }
}