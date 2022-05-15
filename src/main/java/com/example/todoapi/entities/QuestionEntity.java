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
@Table(name = "tbl_question")
public class QuestionEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "question_content")
    private String question_content;
    @Column(name = "question_type")
    private int question_type;
    @OneToMany(targetEntity = AnswerEntity.class, mappedBy = "question", cascade = CascadeType.ALL)
    private Set<AnswerEntity> answerEntitySet;
    @ManyToOne(targetEntity = ExamEntity.class)
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;
    @Column(name = "mark")
    private Double mark;
    @Column(name = "video_url")
    private String video_url;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "audio_url")
    private String audio_url;
    @OneToOne(mappedBy = "question")
    private ResultEntity result;
}
