package com.example.todoapi.services.ServiceImpl;


import com.example.todoapi.dtos.AnswerDTO;


import com.example.todoapi.services.AnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Override
    public List<AnswerDTO> getAll() {
        return null;
    }

    @Override
    public AnswerDTO getByID(Long id) {
        return null;
    }

    @Override
    public void insertNew(AnswerDTO answerDTO) {

    }

    @Override
    public void updateOld(AnswerDTO answerDTO) {

    }

    @Override
    public void deleteOld(Long id) {

    }
}