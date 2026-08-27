package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.utils.JPAUtil;

import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends GenericDAOImpl<Usuario> implements UsuarioDAO {

    public UsuarioDAOImpl(EntityManagerFactory emf) {
        super(Usuario.class, emf);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        EntityManager em =  getEntityManager();
        try {
            List<Usuario> resultado = em.createNamedQuery("Usuario.buscarPorEmail", Usuario.class)
                    .setParameter("email", email)
                    .getResultList();

            return resultado.stream().findFirst();
        } finally {
            em.close();
        }
    }
}
