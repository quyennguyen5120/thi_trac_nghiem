package com.example.todoapi.repositories;

import com.example.todoapi.dtos.ResultDTO;
import com.example.todoapi.entities.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity,Long> {
    @Query("select new com.example.todoapi.dtos.ResultDTO(r) from ResultEntity r where r.userEntity.id=?1 and r.question.exam.id = ?2")
    public List<ResultDTO> getAllByUserAndExam(Long userId, Long examId);

    @Query("select r from ResultEntity r where r.userEntity.id=?1 and r.question.exam.id = ?2")
    public List<ResultEntity> getAllByUserAndExamZ(Long userId, Long examId);

    @Query("select SUM(r.mark) from ResultEntity r where r.userEntity.id = ?1 and r.question.exam.id = ?2 ")
    public Double sumScoreByUserAndExam(Long userId, Long examId);
}
