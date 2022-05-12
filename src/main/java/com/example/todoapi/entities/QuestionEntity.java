package com.example.todoapi.entities;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "question")
    private String question;
    @Column(name = "question_type")
    private int question_type;

}
