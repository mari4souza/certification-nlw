package com.meduardacardoso.certification_nlw.modules.students.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meduardacardoso.certification_nlw.modules.students.entities.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID>{

    public Optional<StudentEntity> findByEmail(String email); //criando um metodo de busca utilizando o atributo "email" do proprio Student Entity.  ou seja, isso se traduz para bd como um where email = xxxx
    // é optional pq pode retornar um email, mas e se nao tiver ? 
    
    
} 