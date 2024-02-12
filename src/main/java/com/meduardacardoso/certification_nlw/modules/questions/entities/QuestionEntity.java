package com.meduardacardoso.certification_nlw.modules.questions.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "questions")
public class QuestionEntity {
    @Id // definindo primarykey
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;

    @Column(length = 50)
    private String technology;

    private String description;

    @OneToMany // uma questao para muitas alternativas
    @JoinColumn(name = "question_id")
    private List<AlternativesEntity> alternatives;

    @CreationTimestamp //toda vez que um dado for inserido, vai add a data e hora
    private LocalDateTime createdAt;
}
