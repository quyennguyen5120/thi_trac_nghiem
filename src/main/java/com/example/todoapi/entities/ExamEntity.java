package com.example.todoapi.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_exam")
public class ExamEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "exam_name")
    private String exam_name;
    @OneToMany(targetEntity = QuestionEntity.class,mappedBy = "exam")
    private Set<QuestionEntity> questionEntities;
    @Column(name = "time_limit")
    private Long time_limit;
}
