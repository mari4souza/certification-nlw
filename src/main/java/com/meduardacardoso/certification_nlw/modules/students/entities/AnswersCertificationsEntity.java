//uma classe so de relacionamentos

package com.meduardacardoso.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera os getters and setters, funcao do lombok
@AllArgsConstructor // cria um construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio
@Entity(name = "answers_certification_students") 
@Builder

public class AnswersCertificationsEntity {

    @Id // definindo primarykey
    @GeneratedValue(strategy = GenerationType.UUID) 
    private UUID id;
    
    //todas as colunas abaixo são FK (foreignkey)

    @Column(name = "certification_id")
    private UUID certificationID; // id de relacionamento, para saber a qual certificação pertence

    //coluna de ligação entre uma entidade e uma coluna, só uma ligação
    @ManyToOne //CARDINALIDADE: muitas respostas para uma certificação
    @JoinColumn(name = "certification_id", insertable = false, updatable = false) // definindo q certification_id é uma FK q está vindo da entidade ou tabela declarada abaixo
    @JsonBackReference
    private CertificationStudentEntity certificationStudentEntity;


    @Column(name = "student_id") 
    private UUID studentID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false) // FK
    private StudentEntity studentEntity;


    @Column(name = "question_id")
    private UUID questionID;





    @Column(name = "answer_id")
    private UUID answerID;




    @Column(name = "is_correct")
    private boolean isCorrect;




    @CreationTimestamp //toda vez que um dado for inserido, vai add a data e hora
    private LocalDateTime createdAt;
}
