package org.uned.practicatw.service;

import org.uned.practicatw.dao.ConfiguracionDAO;
import org.uned.practicatw.model.ConfiguracionSistema;

public class ConfiguracionServiceImpl extends GenericServiceImpl<ConfiguracionSistema, ConfiguracionDAO> implements ConfiguracionService {
    public ConfiguracionServiceImpl(ConfiguracionDAO dao) {
        super(dao);
    }
}