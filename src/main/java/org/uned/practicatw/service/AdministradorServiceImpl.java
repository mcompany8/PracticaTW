package org.uned.practicatw.service;

import org.uned.practicatw.dao.AdministradorDAO;
import org.uned.practicatw.model.Administrador;

public class AdministradorServiceImpl extends GenericServiceImpl<Administrador, AdministradorDAO> implements AdministradorService {
    public AdministradorServiceImpl(AdministradorDAO dao) {
        super(dao);
    }
}
