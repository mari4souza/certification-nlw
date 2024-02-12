// aqui eu vou precisar receber uma entidade q tenha email do user, a tech, e uma lista com as perguntas e as respostas dele
// como ainda nao existe um dto ou entidade com essa estrutura, entao eu vou criar a studentcertificationanswerDTO

package com.meduardacardoso.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meduardacardoso.certification_nlw.modules.questions.entities.QuestionEntity;
import com.meduardacardoso.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.meduardacardoso.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.meduardacardoso.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.meduardacardoso.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.meduardacardoso.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.meduardacardoso.certification_nlw.modules.students.entities.StudentEntity;
import com.meduardacardoso.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.meduardacardoso.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired//vai verificar se ja nao existe a certificação
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception {

        //verificando se ja nao tem certificação
        var hasCertification = this.verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(dto.getEmail(), dto.getTechnology()));

        if (hasCertification) {
            throw new Exception ("Você já tirou sua certificação!");
        }

        //Buscar as alternativas das perguntas
                // - corret ou incorrect 
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0); //usando ess classe para manipular uma variavel dentro de uma expressao lambda

        dto.getQuestionsAnswers()
                .stream().forEach(questionAnswer -> { //percorrendo cada questao
                    var question = questionsEntity.stream()
                            .filter(q -> q.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

                    var findCorrectAlternative = question.getAlternatives().stream()
                            .filter(alternative -> alternative.isCorrect()).findFirst().get();

                    if (findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                        questionAnswer.setCorrect(true);
                        correctAnswers.incrementAndGet(); // incrementando a variavel de contagem de corretas
                    } else {
                        questionAnswer.setCorrect(false);
                    }

                    var answersCertificationsEntity = AnswersCertificationsEntity.builder()
                    .answerID(questionAnswer.getAlternativeID()) //minha resposta
                    .questionID(questionAnswer.getQuestionID())
                    .isCorrect(questionAnswer.isCorrect()).build();

                    answersCertifications.add(answersCertificationsEntity);
                });
        

        //verificar se o estudante existe pelo email
        var student = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if (student.isEmpty()) { //se o estudante n for cadastrado, cadastra o email e atribui um id pro estudante
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build(); //todas as infos do usuario estao dentro do dto
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else {
            studentID = student.get().getId();
        }


        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
            .technology(dto.getTechnology())
            .studentID(studentID)
            .grade(correctAnswers.get())
            .build();

            var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

            answersCertifications.stream().forEach(answerCertification -> {
                answerCertification.setCertificationID(certificationStudentEntity.getId());
                answerCertification.setCertificationStudentEntity(certificationStudentEntity);
            });

            certificationStudentEntity.setAnswersCertificationsEntities(answersCertifications);

            certificationStudentRepository.save(certificationStudentEntity);
            
            return certificationStudentCreated;
        //salvar as informações da certificacao


    }
}
