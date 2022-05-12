package com.example.todoapi.repositories;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("select new com.example.todoapi.dtos.AnswerDTO(a) from AnswerEntity a where a.question.id = ?1 and a.isRight = true")
    public List<AnswerDTO> getMultiAnswerRightByQuestion(Long id);

    @Query("select new com.example.todoapi.dtos.AnswerDTO(a) from AnswerEntity a where a.question.id = ?1 and a.isRight = true")
    public AnswerDTO getSingleAnswerRightByQuestion(Long id);

    @Query("select count(a) from AnswerEntity a where a.id in ?1 and a.isRight = true")
    public Integer checkRighitMultiAnswer(List<Long> lstLongs);
}
