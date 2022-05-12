package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.Utils.Const;
import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.dtos.CalculatorDto;
import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.entities.AnswerEntity;
import com.example.todoapi.entities.ExamEntity;
import com.example.todoapi.entities.QuestionEntity;
import com.example.todoapi.entities.ResultEntity;
import com.example.todoapi.repositories.AnswerRepository;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.repositories.QuestionRepository;
import com.example.todoapi.services.CalculatorMarkService;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalculatorMarkServiceImpl implements CalculatorMarkService {

    @Autowired
    ExamService examService;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    public void calculartorMark(CalculatorDto calculatorDto){
        ResultEntity resultEntity = null;
        if(calculatorDto.getExamId() != null){
            ExamEntity examEntity = examRepository.getById(calculatorDto.getExamId());
            if(examEntity != null){
                for(QuestionDto question : calculatorDto.getLstQuestion()){
                    resultEntity = new ResultEntity();
                    QuestionEntity question1 = questionRepository.getById(question.getId());
                    if(question.getQuestion_type() == Const.TypeAnsewr.SINGLE_ANWSER.getValue()){
                       Boolean checkRighit = this.checkRightSigle(question.getIdSingleQuestion());
                       if(checkRighit == true){
                           resultEntity.setMark(question1.getMark());
                           resultEntity.setQuestion(question1);
                       }
                       else{
                           resultEntity.setMark(0D);
                           resultEntity.setQuestion(question1);
                       }
                        //// lưu lai result
                    }
                    if(question.getQuestion_type() == Const.TypeAnsewr.MUTILIP_ANWSER.getValue()){
                        Boolean checkRighit = this.checkRightMulti(question.getListIdAnswer());
                    }
                }
            }
        }
    }
    public Boolean checkRightSigle(Long answerId){
        if(answerId != null){
            AnswerEntity answerEntity = answerRepository.getById(answerId);
            if(answerEntity.getIsRight() == true)
                return true;
            return false;
        }
        return false;
    }
    public Boolean checkRightMulti(List<Long> answerId){
        if(answerId != null && answerId.size() > 0) {

        }
        return false;
    }
}
