package com.meduardacardoso.certification_nlw.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meduardacardoso.certification_nlw.modules.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> { //listar todas as questoes somente da tech q o user vai fazer a prova

    List<QuestionEntity> findByTechnology(String technology); //vou receber a tech 
} 