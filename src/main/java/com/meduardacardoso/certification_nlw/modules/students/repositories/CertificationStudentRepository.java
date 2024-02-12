package com.meduardacardoso.certification_nlw.modules.students.repositories;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meduardacardoso.certification_nlw.modules.students.entities.CertificationStudentEntity;

//esse repositorio jpa dispoe de inumetos metodos que podem ser usados
@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {
    
    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email= :email AND c.technology = :technology")
    List<CertificationStudentEntity> findByStudentEmailAndTechnology(String email, String technology); //criando um metodo  

    @Query("SELECT c FROM certifications c ORDER BY c.grade DESC LIMIT 10")
    List<CertificationStudentEntity> findTop10ByOrderByGradeDesc();
}
