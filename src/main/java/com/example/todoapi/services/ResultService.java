package com.example.todoapi.services;

import com.example.todoapi.dtos.QuestionDto;
import com.example.todoapi.dtos.ResultDTO;

import java.util.List;

public interface ResultService extends BaseService<ResultDTO>{
    List<ResultDTO> getQuestionDtos(Long userId, Long id);
}
