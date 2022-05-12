package com.example.todoapi.repositories;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("select new com.example.todoapi.dtos.AnswerDTO(a) from AnswerEntity a where a.question.id = ?1 and a.id in ?2 and a.isRight = true")
    public List<AnswerDTO> getMultiAnswerRightByQuestion(Long qusetionId, List<Long> lstAnswerId);

    @Query("select new com.example.todoapi.dtos.AnswerDTO(a) from AnswerEntity a where a.question.id = ?1 and a.isRight = true")
    public AnswerDTO getSingleAnswerRightByQuestion(Long id);

}
