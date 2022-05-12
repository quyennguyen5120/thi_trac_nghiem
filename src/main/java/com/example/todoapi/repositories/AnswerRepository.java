package com.example.todoapi.repositories;

import com.example.todoapi.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

}
