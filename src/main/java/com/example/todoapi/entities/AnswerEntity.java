package com.example.todoapi.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_answer")
public class AnswerEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "answer_content")
    private String answer_content;
    @ManyToOne(targetEntity = QuestionEntity.class)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
}
