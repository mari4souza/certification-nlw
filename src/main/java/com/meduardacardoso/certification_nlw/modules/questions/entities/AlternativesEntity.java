package com.meduardacardoso.certification_nlw.modules.questions.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "alternatives")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativesEntity {
    @Id // definindo primarykey
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;

    private String description;

    private boolean isCorrect;

    @CreationTimestamp //toda vez que um dado for inserido, vai add a data e hora
    private LocalDateTime createdAt;
}
