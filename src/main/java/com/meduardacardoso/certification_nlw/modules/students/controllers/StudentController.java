// definir e restringir ações do usuário

package com.meduardacardoso.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meduardacardoso.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.meduardacardoso.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.meduardacardoso.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.meduardacardoso.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    // precioso usar meu USECASE
    @Autowired // pedindo pro Spring instanciar (inicializar) a classe VerifyIfHasCertificationUseCase pra mim
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @PostMapping("/verifyIfHasCertification")               // criei uma arquivo com uma classe VerifyHasCertificationDTO que só tem email e tech
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO) { // objeto q estou recebendo dentro de um json 
        //email
        //tech
        var result = this.verifyIfHasCertificationUseCase.execute(verifyHasCertificationDTO);
        if(result) {
            return "Usuário já fez a prova";
        }
        return "usuário pode fazer a prova";   
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO studentCertificationAnswerDTO) throws Exception {
        try {
            var result = studentCertificationAnswersUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
}
