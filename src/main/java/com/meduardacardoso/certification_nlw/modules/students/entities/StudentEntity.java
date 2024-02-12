//o que vou estar recebendo do usuario

package com.meduardacardoso.certification_nlw.modules.students.entities;

import java.util.List;
import java.util.UUID; //identificador universal unico

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera os getters and setters, funcao do lombok
@AllArgsConstructor // cria um construtor com todos os atributos
@NoArgsConstructor // cria um construtor vazio
@Entity(name = "students") // define essa classe como uma ENTIDADE, ou seja, uma tabela do banco de dados, e students é o nome dessa tabela no banco
@Builder

public class StudentEntity {
    
    //todos os atributos serão tranformados em colunas da tabela 

    @Id // tem q ser o do jakarta persistence
    @GeneratedValue(strategy = GenerationType.UUID) //gerando um numero aleatorio para o id , primarykey
    private UUID id;

    @Column(unique = true, nullable = false) // definindo que não pode haver dois e-mails iguais, e não é para caeitar um estudante sem email
    private String email;


    @OneToMany(mappedBy = "studentEntity") // CARDINALIDADE: um aluno pode ter muitas certificações, um para todos
    @JsonBackReference
    private List<CertificationStudentEntity> certificationStudentEntity;

}
