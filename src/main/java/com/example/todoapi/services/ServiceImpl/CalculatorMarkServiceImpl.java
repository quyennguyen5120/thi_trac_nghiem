package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.Utils.Const;
import com.example.todoapi.dtos.CalculatorDto;
import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.entities.ExamEntity;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.entities.ResultEntity;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.CalculatorMarkService;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CalculatorMarkServiceImpl implements CalculatorMarkService {

    @Autowired
    ExamService examService;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionRepository questionRepository;


    public void calculartorMark(CalculatorDto calculatorDto){
        ResultEntity resultEntity = null;
        if(calculatorDto.getExamId() != null){
            ExamEntity examEntity = examRepository.getById(calculatorDto.getExamId());
            if(examEntity != null){
                for(QuestionDto question : calculatorDto.getLstQuestion()){
                    QuestionEntity question1 = questionRepository.getById(question.getId());
                    if(question.getQuestion_type() == Const.TypeAnsewr.SINGLE_ANWSER.getValue()){

                    }
                }
            }
        }
    }
}
