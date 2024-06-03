package com.maker.sql.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.maker.sql.project.exceptions.EntityCreationException;
import com.maker.sql.project.helpers.ValidationUtils;
import com.sql.core.maker.SqlMaker;
import com.sql.core.maker.SqlResponse;
import com.sql.data.maker.SqlGenFactory;

public class Menu {
    private final Scanner scanner;
    private static final SqlGenFactory factory = new SqlGenFactory();
    private static final SqlMaker sqlMaker = factory.createSqlMakerExtended(null);

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMainMenu() {
        System.out.println("----- Menu Principal -----");
        System.out.println("1. Criar Entidade");
        System.out.println("2. Listar Entidades");
        System.out.println("3. Gerar SELECT ALL");
        System.out.println("4. Gerar SELECT");
        System.out.println("5. Gerar INSERT");
        System.out.println("6. Gerar REMOVE");
        System.out.println("7. Gerar UPDATE");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void createEntity() {
        System.out.println("----- Criar Nova Entidade -----");
        try {
            System.out.print("Digite o nome da classe da entidade: ");
            String nomeClasse = scanner.nextLine();

            if (!ValidationUtils.isEntityNameValid(nomeClasse)) {
                throw new EntityCreationException("Nome da classe da entidade inválido.");
            }
            List<String> atributos = new ArrayList<>();
            String atributo;
            while (true) {
                System.out.print("Digite o nome do próximo atributo ou 'fim' para terminar: ");
                atributo = scanner.nextLine();
                if (atributo.equals("fim")) {
                    break;
                }
                atributos.add(atributo);
            }
            EntityGenerator.generateEntity(nomeClasse, atributos.toArray(new String[0]));
        } catch (EntityCreationException e) {
            System.out.println("Erro ao criar entidade: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

   public void listEntities(String packagePath) {
        System.out.println("----- Lista de Entidades -----");
        List<String> entities = EntityGenerator.listEntities(packagePath);

        if (entities.isEmpty()) {
            System.out.println("Nenhuma entidade criada ainda.");
        } else {
            System.out.println("Entidades disponíveis:");
            printEntities(entities);
        }
    }

    private void printEntities(List<String> entities) {
        for (String entity : entities) {
            System.out.println(entity);
        }
    }

    public void generateSelectAllSql() {
        System.out.println("----- Gerar SQL para Selecionar Todos os Registros de uma Entidade -----");
        System.out.print("Digite o nome da classe da entidade: ");
        String entityName = scanner.nextLine();
        try {
            Class<?> entityClass = Class.forName("com.maker.sql.project.entities." + entityName);
            SqlResponse response = sqlMaker.generateSelectAllSql(entityClass);
            String sqlQuery = replaceEntityName(response.getSqlQuery(), entityName);
            System.out.println("Consulta gerada: " + sqlQuery);
        } catch (ClassNotFoundException e) {
            System.out.println("Classe " + entityName + " não encontrada.");
        }
    }

    public void generateSelectSql() {
        System.out.println("----- Gerar SQL de Seleção -----");
        System.out.print("Nome da Entidade: ");
        String entityName = scanner.nextLine();

        try {
            Class<?> entityClass = Class.forName("com.maker.sql.project.entities." + entityName);
            System.out.print("Operador (AND/OR): ");
            String operator = scanner.nextLine();
            System.out.println("Digite os nomes dos atributos (separados por vírgula):");
            String input = scanner.nextLine();
            String[] attributes = input.split(",");

            SqlResponse response = sqlMaker.generateSelectSql(entityClass,
                    operator, attributes);
            String sqlQuery = replaceEntityName(response.getSqlQuery(), entityName);
            System.out.println("Consulta gerada: " + sqlQuery);
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Entidade não encontrada.");
        }
    }

    public void generateInsertSql() {
        System.out.println("Gerar INSERT");
        System.out.print("Nome da Entidade: ");
        String entityName = scanner.nextLine();

        try {
            Class<?> entityClass = Class.forName("com.maker.sql.project.entities." + entityName);
            SqlResponse response = sqlMaker.generateInsertSql(entityClass);
            String sqlQuery = replaceEntityName(response.getSqlQuery(), entityName);
            System.out.println("Consulta gerada: " + sqlQuery);
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Entidade não encontrada.");
        }
    }

    public void generateDeleteSql() {
        System.out.println("Gerar DELETE");
        System.out.print("Nome da Entidade: ");
        String entityName = scanner.nextLine();

        try {
            Class<?> entityClass = Class.forName("com.maker.sql.project.entities." + entityName);
            SqlResponse response = sqlMaker.generateDeleteSql(entityClass);
            String sqlQuery = replaceEntityName(response.getSqlQuery(), entityName);
            System.out.println("Consulta gerada: " + sqlQuery);
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Entidade não encontrada.");
        }
    }

    public void generateUpdateSql() {
        System.out.println("----- Gerar SQL de Atualização -----");
        System.out.print("Nome da Entidade: ");
        String entityName = scanner.nextLine();

        try {
            Class<?> entityClass = Class.forName("com.maker.sql.project.entities." + entityName);
            SqlResponse response = sqlMaker.generateUpdateSql(entityClass);
            String sqlQuery = replaceEntityName(response.getSqlQuery(), entityName);
            System.out.println("Consulta de atualização gerada: " + sqlQuery);
        } catch (ClassNotFoundException e) {
            System.out.println("Entidade não encontrada.");
        }
    }

    private String replaceEntityName(String sqlQuery, String entityName) {
        return sqlQuery.replace("GeneralEntity", entityName);
    }

}
