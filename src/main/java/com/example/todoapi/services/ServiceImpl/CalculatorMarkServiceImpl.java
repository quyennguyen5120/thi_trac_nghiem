package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.Utils.Const;
import com.example.todoapi.dtos.*;
import com.example.todoapi.entities.*;
import com.example.todoapi.repositories.*;
import com.example.todoapi.services.CalculatorMarkService;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    ResultRepository resultRepository;
    @Autowired
    UserRepository  userRepository;

    @Override
    public List<ResultDTO> calculartorMark(Calculator_newDto calculatorDto){
        ResultEntity resultEntity = null;
        List<ResultDTO> resultDTOS = new ArrayList<>();
        ResultDTO resultDTO = null;
        if(calculatorDto.getExamId() != null && calculatorDto.getUserId() != null){
            ExamEntity examEntity = examRepository.getById(calculatorDto.getExamId());
            List<ResultEntity> resultEntities = resultRepository.getAllByUserAndExamZ(calculatorDto.getUserId(), calculatorDto.getExamId());
            resultRepository.deleteAll(resultEntities);

            UserEntity userEntity = userRepository.getById(calculatorDto.getUserId());
            if(examEntity != null){
                for(Question_newDto question : calculatorDto.getLstQuestion()){
                    resultEntity = new ResultEntity();
                    QuestionEntity question1 = questionRepository.getById(question.getQuestion_id());
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
                       resultEntity.setUserEntity(userEntity);
                        resultEntity = resultRepository.save(resultEntity);
                        resultDTO = new ResultDTO(resultEntity);
                        resultDTOS.add(resultDTO);
                    }
                   else if(question.getQuestion_type() == Const.TypeAnsewr.MUTILIP_ANWSER.getValue()){
                        List<AnswerDTO> answerDTOS = answerRepository.getMultiAnswerRightByQuestion(question.getQuestion_id(), question.getListIdAnswer());
                        Boolean isRight = true;
                        for(AnswerDTO answerDTO : answerDTOS){
                            if(answerDTO.getIsright() == null || (answerDTO.getIsright() != null && answerDTO.getIsright() == false )){
                                isRight = false;
                                break;
                            }
                        }
                        resultEntity.setQuestion(question1);
                        if(isRight == true){
                            resultEntity.setMark(question1.getMark());
                        }
                        else
                            resultEntity.setMark(0D);
                        resultEntity.setUserEntity(userEntity);
                        resultEntity = resultRepository.save(resultEntity);
                        resultDTO = new ResultDTO(resultEntity);
                        resultDTOS.add(resultDTO);
                    }
                }
            }
        }
        return resultDTOS;
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

}
