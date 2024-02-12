//executando slq na mao

package com.meduardacardoso.certification_nlw.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateSeed {

    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource) { // recebendo
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[] args) { //criando um datasource com as infos do meu banco de dados
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5434/pg_nlw"); // mesma do application propperties
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args); // criando um metodo no create seed
    }

    public void run(String... args) { // aqui vou ler o meu arquivo create.sql
        executeSqlFile("src/main/resources/create.sql");
    }

    private void executeSqlFile(String filePath) { // aqui voui receber o path e ler o arquivo, e executar o script no jdbc
        try {
            String slqScript = new String (Files.readAllBytes(Paths.get(filePath))); //faz a leitura

            jdbcTemplate.execute(slqScript); //executa no jdbc

            System.out.println("Seed realizado");
        }catch(IOException e) {
            System.err.println("Erro ao executar arquivo" + e.getMessage()); // err aparece no terminal
        }
    }
}