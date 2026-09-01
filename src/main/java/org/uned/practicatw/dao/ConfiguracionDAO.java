package org.uned.practicatw.dao;

import org.uned.practicatw.model.ConfiguracionSistema;

/**
 * DAO de {@link ConfiguracionSistema}. No añade operaciones propias: como es
 * una fila única, {@code buscarPorId(1L)} y {@code actualizar(...)} del
 * genérico son suficientes.
 */
public interface ConfiguracionDAO extends GenericDAO<ConfiguracionSistema> {
}