package com.maker.sql.project.interfaces;

import java.util.List;

import com.maker.sql.project.exceptions.EntityCreationException;

public interface EntityService {
    void createEntity(String nomeClasse, List<String> atributos) throws EntityCreationException;

    List<String> listEntities();
}
