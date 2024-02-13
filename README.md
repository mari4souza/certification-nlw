# 🎓 Certification System

> Complete application of certifications made in Spring Boot.

A partir do e-mail cadastrado, o usuário é capaz de escolher qual tecnologia ele deseja obter a certificação. Após isso, o mesmo recebe uma lista de questões referentes a tecnologia a qual ele escolheu, com suas respectivas alternativas. 

O usuário então, seleciona a alternativa que ele acredita ser a correta para cada questão. Ao final, o usuário recebe a nota referente à certificação, que é baseada na quantidade de acertos. E por último, consegue visualizar sua posição em um ranking com as 10 melhores notas de alunos que também realizaram aquela certificação.

Foram utilizados durante o desenvolvimento da aplicação padrões de arquitetura de projetos (MVC), e boas práticas de programação.
A aplicação também dispõe de tratamento de erros, impedindo que um usuário com e-mail não cadastrado realize a prova e impedindo também a realização de certificações que já foram obtidas anteriormente.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes dependências instaladas:

- Antes de começar, verifique se você possui o `Java 17` instalado em sua máquina. Se não tiver, você pode baixá-lo [aqui](https://download.oracle.com/java/17/archive/jdk-17.0.6_windows-x64_bin.msi).
- Também será necessária a instalação e configuração do `Maven`, baixe [aqui](https://maven.apache.org/download.cgi) e configure-o em sua máquina.
- Por último, utilizaremos o `Doker`, a maneira mais fácil e recomendada é instalando o Docker Desktop, baixe-o [aqui](https://docs.docker.com/desktop/install/windows-install/). 

## Como executar o projeto

Siga as etapas abaixo para executar o projeto em sua máquina local:

Execute os seguintes comandos a partir da pasta raiz do projeto:


### Clone este repositório

```bash
git clone <link-do-repositorio>
```

Este link pode ser encontrado no botão verde acima `Code`.

### Instale as dependências

Não é necssária a instalação pois as dependências que usaremos já foram instaladas previamente com as configurações padrão. São elas: `Spring Web`, `Spring Boot Dev Tools`, `Lombok` e `Spring Data JPA`.

### Defina as variáveis de ambiente

No arquivo “Aplication Properties” definir em qual porta deverá rodar a aplicação. Exemplo: `server.port=8085`.


### Execute o Projeto

```bash
mvn spring-boot:run
```

## Estrutura de Pastas

A estrutura de pastas do projeto é organizada da seguinte maneira:

```text
/
|-- certifications/
|   |-- controllers/
|   |  |-- RankingController.java
|   |-- useCases/
|   |  |-- Top10RankingUseCase.java
|-- questions/
|   |-- controllers/
|   |  |-- QuestionController.java
|   |-- dto/
|   |  |-- AlternativesResultDTO.java
|   |  |-- QuestionResultDTO.java
|   |-- entities/
|   |  |-- AlternativesEntity.java
|   |  |-- QuestionEntity.java
|   |-- repositories/
|   |  |-- QuestionRepository.java
|-- students/
|   |-- controllers/
|   |  |-- StudentController.java
|   |-- dto/
|   |  |-- QuestionAnswerDTO.java
|   |  |-- StudentCertificationAnswerDTO.java
|   |  |-- VerifyHasCertificationDTO.java
|   |-- entities/
|   |  |-- AnswersCertificationsEntity.java
|   |  |-- CertificationStudentEntity.java
|   |  |-- StudentEntity.java
|   |-- repositories/
|   |  |-- CertificationStudentRepository.java
|   |  |-- StudentRepository.java
|   |-- useCases/
|   |  |-- StudentCertificationAnswersUseCase.java
|   |  |-- VerifyIfHasCertificationUseCase.java

```

<!-- Outra forma de descrever é em texto corrido -->

### Disposição e estilos

* `controllers`: Dados estruturados para serem reusados de maneira X. Por exemplo Y

* `useCases`: Componentes que podem ser reusados entre as páginas. Por exemplo Z

* `dto`: Componentes que podem ser reusados entre as páginas. Por exemplo Z

* `entities`: Componentes que podem ser reusados entre as páginas. Por exemplo Z

* `repositories`: Componentes que podem ser reusados entre as páginas. Por exemplo Z
  
* `...`: Outras informações


## Como contribuir

Se você deseja contribuir para este projeto, siga as etapas abaixo:

1. Faça um fork deste repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_do_projeto> / <local>`
5. Criar a solicitação de pull.

Como alternativa, consulte a documentação do GitHub sobre [como criar uma solicitação de pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).
    

## Licença

Este projeto está sob licença. Consulte [LICENSE](LICENSE.md) para obter mais informações.

## Membros do Projeto 

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/mari4souza">
        <img src="https://github.com/mari4souza.png" width="100px">
        <br>
        <sub>
          <b>Maria Eduarda Cardoso</b>
        </sub>
      </a>
    </td>
