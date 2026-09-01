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

    @Override
    public void cambiarTipo(Long id, String tipo) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("UPDATE usuarios SET tipo_usuario = :tipo WHERE id = :id")
                    .setParameter("tipo", tipo)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
