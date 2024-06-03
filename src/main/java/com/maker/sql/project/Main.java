package com.maker.sql.project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);

        while (true) {
            menu.displayMainMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    menu.createEntity();
                    break;
                case 2:
                    menu.listEntities();
                    break;
                case 3:
                    menu.generateSelectAllSql();
                    break;
                case 4:
                    menu.generateSelectSql();
                    break;
                case 5:
                    menu.generateInsertSql();
                    break;
                case 6:
                    menu.generateDeleteSql();
                    break;
                case 7:
                    menu.generateUpdateSql();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
