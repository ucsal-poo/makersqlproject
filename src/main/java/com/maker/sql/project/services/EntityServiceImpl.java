package com.maker.sql.project.services;

import java.util.List;

import com.maker.sql.project.EntityGenerator;
import com.maker.sql.project.exceptions.EntityCreationException;
import com.maker.sql.project.helpers.ValidationUtils;
import com.maker.sql.project.interfaces.EntityService;

public class EntityServiceImpl implements EntityService {
    @Override
    public void createEntity(String entityName, List<String> atributos) throws EntityCreationException {
        if (!ValidationUtils.isEntityNameValid(entityName)) {
            throw new EntityCreationException("Nome da classe da entidade inválido.");
        }
        EntityGenerator.generateEntity(entityName, atributos.toArray(new String[0]));
    }

    @Override
    public List<String> listEntities() {
        return EntityGenerator.listEntities("com.maker.sql.project.entities");
    }
}
