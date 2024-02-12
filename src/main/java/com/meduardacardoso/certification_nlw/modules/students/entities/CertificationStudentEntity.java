package com.meduardacardoso.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera os getters and setters, funcao do lombok
@AllArgsConstructor // cria um construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio
@Entity(name = "certifications")
@Builder
public class CertificationStudentEntity {
    
    @Id // deifnindo primarykey
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id; // id da entidade

    @Column(length = 100)
    private String technology;

    @Column(length = 10)
    private int grade; // nota

    @Column(name = "student_id")
    private UUID studentID; // uuid que esta vindo do StudentEntiy, foreignkey


    @ManyToOne // CARDINALIDADE: todos para um, muitos estudantes para uma certificação
    @JoinColumn(name = "student_id", insertable = false, updatable = false) //qual coluna da tabela sera responsavel por esse mapeamento

    private StudentEntity studentEntity; //relacionando com a studentEntity

    @OneToMany(cascade = CascadeType.ALL) // CARDINALIDADE: muitas respostas para uma certificação
    @JoinColumn(name = "answer_certification_id", insertable = false, updatable = false) 
    @JsonBackReference
    List<AnswersCertificationsEntity> answersCertificationsEntities;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
