# Projeto SQL Maker

## Descrição

Este projeto é uma aplicação Java que atravês do uso do componente MakerSql, facilita a criação e manipulação de entidades e a geração de consultas SQL. Ele permite criar novas entidades, listar entidades existentes e gerar consultas SQL (SELECT, INSERT, UPDATE, DELETE) para essas entidades.

## Funcionalidades

- Criar nova entidade
- Listar entidades existentes
- Gerar consulta SQL SELECT ALL
- Gerar consulta SQL SELECT
- Gerar consulta SQL INSERT
- Gerar consulta SQL DELETE
- Gerar consulta SQL UPDATE

## Dependências

O projeto faz uso da dependência MakerSql que facilita a geração das consultas SQL. Certifique-se de incluir a dependência no seu `pom.xml`:

```xml
    <dependencies>
        <dependency>
            <groupId>com.sql</groupId>
            <artifactId>maker</artifactId>
            <version>1.0</version>
            <scope>system</scope>
        <systemPath>"Coloque aqui o caminho para o Jar proveniente da dependência caso for usa-lo"</systemPath>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>github</id>
            <name>My Maven Repo on GitHub</name>
            <url>https://github.com/DanielTM999/makerSql/</url>
        </repository>
    </repositories>
```

## Estrutura do Projeto

O projeto está organizado em pacotes conforme descrito abaixo:

- A classe principal `Main` que inicia a aplicação e gerencia o menu principal.
- A classe Menu que é responsavel pelos metodos que usaram as funcionalidades do componente MakerSql para criação de entidades e geração de consultas.
- A classe EntityGenerator que é responsavel por criar e listar as entidades.
- `com.maker.sql.project.entities`: Pacote onde as classes das entidades geradas serão armazenadas.
- `com.maker.sql.project.exceptions`: Contém exceções personalizadas utilizadas no projeto.
- `com.maker.sql.project.helpers`: Contém classes auxiliares, como utilitários de validação.

## Classes Principais

### Main

A classe `Main` contém o ponto de entrada da aplicação e gerencia o menu principal, permitindo ao usuário selecionar diferentes operações.

### Menu

A classe `Menu` é responsável por apresentar o menu de opções ao usuário e executar as operações correspondentes, como criar entidades e gerar consultas SQL.

### EntityGenerator

A classe `EntityGenerator` é responsável por gerar dinamicamente classes de entidades baseadas nas entradas fornecidas pelo usuário e listas tais classes.

#### Exemplo de criação de uma entidade pelo metodo "createEntity":

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

## Classes Secundarias

### Helpers

A pasta Helpers guarda metodos e classes que possuem o objetivo de auxiliar partes mais criticas do código:

- ValidationUtils: Classe que armazena metodos de validação:

#### Metodos
1. isEntityNameValid

       public static boolean isEntityNameValid(String entityName) {
           if (entityName.isEmpty()) {
               return false;
           }
           if (!Character.isLetter(entityName.charAt(0)) && entityName.charAt(0) != '_') {
               return false;
           }
           for (int i = 1; i < entityName.length(); i++) {
               char ch = entityName.charAt(i);
               if (!Character.isLetterOrDigit(ch) && ch != '_') {
                   return false;
               }
           }
           if (RESERVED_KEYWORDS.contains(entityName)) {
               return false;
           }
           return true;
       }

### Exceptions

A pasta Exceptions guarda exceções personalidas:

- EntityCreationException: Exceção para a criação de entidades

## Uso

### Executar a aplicação

Para executar a aplicação, execute o método `main` da classe `Main`. Será exibido um menu com as seguintes opções:

1. Criar Entidade
2. Listar Entidades
3. Gerar SELECT ALL
4. Gerar SELECT
5. Gerar INSERT
6. Gerar DELETE
7. Gerar UPDATE
0. Sair

### Criar uma nova entidade

1. Selecione a opção `1` no menu principal.
2. Digite o nome da classe da entidade.
3. Insira os nomes dos atributos da entidade um por um, digitando `fim` quando terminar.

### Listar entidades

Selecione a opção `2` no menu principal para listar todas as entidades existentes.

### Gerar consultas SQL

Para gerar diferentes tipos de consultas SQL, selecione as opções correspondentes no menu principal e siga as instruções fornecidas.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias e correções.

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## Observações

- **Limitações de Nome de Entidade**: Certifique-se de que os nomes das entidades sejam válidos e sigam as convenções de nomenclatura Java. Nomes inválidos resultarão em uma `EntityCreationException`.
- **Atributos de Entidade**: Atualmente, todos os atributos das entidades são gerados como `String`. Futuras melhorias podem incluir suporte a outros tipos de dados.
- **Diretório de Entidades**: As classes de entidades são geradas no diretório `src/main/java/com/maker/sql/project/entities`.
- **Dependência Maven**: A dependência Maven `sql-core-maker` deve estar corretamente configurada no `pom.xml`. Verifique se a versão especificada está correta e se o repositório Maven contém essa dependência.
- **Compatibilidade**: Este projeto foi desenvolvido e testado com a versão específica do Java e da dependência Maven mencionada.

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.
