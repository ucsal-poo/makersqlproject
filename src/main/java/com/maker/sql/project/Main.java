package com.maker.sql.project;

import java.util.Scanner;

import com.maker.sql.project.interfaces.EntityService;
import com.maker.sql.project.services.EntityServiceImpl;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            EntityService entityService = new EntityServiceImpl();
            Menu menu = new Menu(scanner, entityService);
            menu.run();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
