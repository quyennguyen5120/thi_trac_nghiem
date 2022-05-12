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
@Table(name = "tbl_test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "test_name")
    private String test_name;
    @OneToMany(targetEntity = QuestionEntity.class,mappedBy = "test")
    private Set<QuestionEntity> questionEntities;
    @Column(name = "time_limit")
    private Long time_limit;

}
