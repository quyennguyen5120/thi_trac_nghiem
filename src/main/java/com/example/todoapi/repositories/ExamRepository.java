package com.example.todoapi.repositories;

import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.entities.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    @Query("select new com.example.todoapi.dtos.ExamDTO(e.id, e.exam_name) from ResultEntity r " +
            "inner join UserEntity u on r.userEntity.id = u.id " +
            "inner join QuestionEntity q on r.question.id = q.id " +
            "inner join ExamEntity e on q.exam.id = e.id " +
            "where r.userEntity.id = ?1 " +
            "group by e.id")
    List<ExamDTO> getAllExamByUser(Long userId);
}
