package com.example.todoapi.services.ServiceImpl;

import com.example.todoapi.dtos.ExamDTO;
import com.example.todoapi.repositories.ExamRepository;
import com.example.todoapi.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository examRepository;

    @Override
    public List<ExamDTO> getAll() {

        return null;
    }

    @Override
    public ExamDTO getByID(Long id) {
        return null;
    }

    @Override
    public void insertNew(ExamDTO examDTO) {

    }

    @Override
    public void updateOld(ExamDTO examDTO) {

    }

    @Override
    public void deleteOld(Long id) {

    }
}
