//perguntas e resposta
package com.meduardacardoso.certification_nlw.modules.students.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDTO {
    private UUID questionID;
    private UUID alternativeID;
    private boolean isCorrect; //vgai receber sim ou nao do banco de dados
}