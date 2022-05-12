package com.example.todoapi.repositories;

import com.example.todoapi.dtos.AnswerDTO;
import com.example.todoapi.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
=======

>>>>>>> d379dce7950ff046a5c3079d76bfb88935baafaa
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======
>>>>>>> d379dce7950ff046a5c3079d76bfb88935baafaa

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("select new com.example.todoapi.dtos.AnswerDTO(a) from AnswerEntity a where a.question.id = ?1 and a.isRight = true")
    public List<AnswerDTO> getMultiAnswerRightByQuestion(Long id);

    @Query("select new com.example.todoapi.dtos.AnswerDTO(a) from AnswerEntity a where a.question.id = ?1 and a.isRight = true")
    public AnswerDTO getSingleAnswerRightByQuestion(Long id);

    @Query("select count(a) from AnswerEntity a where a.id in ?1 and a.isRight = true")
    public Integer checkRighitMultiAnswer(List<Long> lstLongs);
}
