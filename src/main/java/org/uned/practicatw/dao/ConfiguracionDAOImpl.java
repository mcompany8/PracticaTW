package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.ConfiguracionSistema;

public class ConfiguracionDAOImpl extends GenericDAOImpl<ConfiguracionSistema> implements ConfiguracionDAO {
    public ConfiguracionDAOImpl(EntityManagerFactory emf) {
        super(ConfiguracionSistema.class, emf);
    }
}