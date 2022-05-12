package com.example.todoapi.repositories;

import com.example.todoapi.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
