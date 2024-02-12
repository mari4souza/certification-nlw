//escondendo a alternativa certa do usuário

package com.meduardacardoso.certification_nlw.modules.questions.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // inicializa a variavel sem precisar do new..
public class QuestionResultDTO {
    
    private UUID id;
    private String technology;
    private String description;

    private List<AlternativesResultDTO> alternativesResultDTO;
}
