package com.example.todoapi.entities;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_result")
public class ResultEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(targetEntity = QuestionEntity.class)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
    @Column(name = "mark")
    private Double mark;

}
