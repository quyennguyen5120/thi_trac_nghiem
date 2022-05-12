package com.example.todoapi.services;

import com.example.todoapi.dtos.QuestionDto;

import java.util.List;

public interface QuestionService extends BaseService<QuestionDto>{
    List<QuestionDto> getQuestionByExamId(Long examId);
}
