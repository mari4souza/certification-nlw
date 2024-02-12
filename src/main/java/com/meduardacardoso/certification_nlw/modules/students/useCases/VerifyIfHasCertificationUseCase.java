package com.meduardacardoso.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meduardacardoso.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.meduardacardoso.certification_nlw.modules.students.repositories.CertificationStudentRepository;

@Service // definindo como camada de serviço 
public class VerifyIfHasCertificationUseCase {

    @Autowired // para usar um componente que é gerenciado pelo Spring
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute( VerifyHasCertificationDTO dto) {
       var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology()); // utilizando o metodo q criei la no repository

        if (!result.isEmpty()) { 
            return true; //se tiver alguma info no banco significa q ele ja tem certificação
        }
        return false;
    }
    
}
