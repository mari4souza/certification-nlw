//tentar entender melhor essa parte
//aparentente manipulando as questions para o user nao saber a certa
//montando a estrutura de como a questao ira aparecer para o usuario

package com.meduardacardoso.certification_nlw.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meduardacardoso.certification_nlw.modules.questions.dto.AlternativesResultDTO;
import com.meduardacardoso.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.meduardacardoso.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.meduardacardoso.certification_nlw.modules.questions.entities.QuestionEntity;
import com.meduardacardoso.certification_nlw.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    
    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        System.out.println("TECH === " + technology);
        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionToDto(question))
        .collect(Collectors.toList());
        return toMap;
    }


    static QuestionResultDTO mapQuestionToDto(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
            .id(question.getId())
            .technology(question.getTechnology())
            .description(question.getDescription()).build();
        

        List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives()
            .stream().map(alternative -> mapAlternativesResultDTO(alternative))
            .collect(Collectors.toList());

        questionResultDTO.setAlternativesResultDTO(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativesResultDTO(AlternativesEntity alternativesResultDTO) {
        return AlternativesResultDTO.builder()
            .id(alternativesResultDTO.getId())
            .description(alternativesResultDTO.getDescription()).build();
    }
}
