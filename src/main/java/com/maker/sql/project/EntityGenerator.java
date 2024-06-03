package com.maker.sql.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityGenerator {

    public static void generateEntity(String nomeClasse, String[] atributos) {
        StringBuilder classContent = new StringBuilder();

        classContent.append("package com.maker.sql.project.entities;\n\n");
        classContent.append("import com.sql.anotations.Entity;\n\n");
        classContent.append("import com.sql.anotations.Id;\n\n");

        classContent.append("@Entity\n");
        classContent.append("public class ").append(nomeClasse).append(" {\n\n");

        classContent.append("\t@Id\n");
        classContent.append("\tprivate String id;\n\n");

        for (String atributo : atributos) {
            classContent.append("\tprivate String ").append(atributo).append(";\n\n");
        }

        classContent.append("\tpublic String getId() {\n");
        classContent.append("\t\treturn id;\n");
        classContent.append("\t}\n\n");

        classContent.append("\tpublic void setId(String id) {\n");
        classContent.append("\t\tthis.id = id;\n");
        classContent.append("\t}\n\n");

        for (String atributo : atributos) {
            String atributoCapitalizado = atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
            classContent.append("\tpublic String get").append(atributoCapitalizado).append("() {\n");
            classContent.append("\t\treturn ").append(atributo).append(";\n");
            classContent.append("\t}\n\n");

            classContent.append("\tpublic void set").append(atributoCapitalizado).append("(String ").append(atributo)
                    .append(") {\n");
            classContent.append("\t\tthis.").append(atributo).append(" = ").append(atributo).append(";\n");
            classContent.append("\t}\n\n");
        }

        classContent.append("}");

        String currentDir = System.getProperty("user.dir");

        String classDir = currentDir + File.separator + "src" + File.separator + "main"
                + File.separator + "java" + File.separator + "com" + File.separator + "maker" + File.separator + "sql"
                + File.separator + "project" + File.separator
                + "entities";

        File entitiesDir = new File(classDir);
        entitiesDir.mkdirs();

        try {
        FileWriter writer = new FileWriter(entitiesDir.getAbsolutePath() + File.separator + nomeClasse + ".java");
    writer.write(classContent.toString());
    System.out.println("Classe " + nomeClasse + " gerada com sucesso!");
} catch (IOException e) {
    System.err.println("Erro ao escrever o arquivo da classe " + nomeClasse + ": " + e.getMessage());
} finally {
    try {
        if (writer != null) {
            writer.close();
        }
    } catch (IOException e) {
        System.err.println("Erro ao fechar o FileWriter: " + e.getMessage());
    }
}
    }

    public static List<String> listEntities(String packageName) {
        List<String> entities = new ArrayList<>();
        String currentDir = System.getProperty("user.dir");
        String entitiesDirPath = currentDir + File.separator + "src" + File.separator + "main" + File.separator + "java"
                + File.separator + packageName.replace(".", File.separator);

        File entitiesDir = new File(entitiesDirPath);
        if (entitiesDir.exists() && entitiesDir.isDirectory()) {
            File[] files = entitiesDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".java")) {
                        String entityName = file.getName().replace(".java", "");
                        entities.add(entityName);
                    }
                }
            }
        } else {
            System.out.println("O diretório de entidades não existe ou não é um diretório.");
        }

        return entities;
    }
}
