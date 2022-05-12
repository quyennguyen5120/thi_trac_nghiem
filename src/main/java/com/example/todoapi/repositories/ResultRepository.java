package com.example.todoapi.repositories;

import com.example.todoapi.entities.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultEntity,Long> {
}
