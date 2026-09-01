package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.uned.practicatw.model.Contenido;

import java.util.List;

public class ContenidoDAOImpl extends GenericDAOImpl<Contenido> implements ContenidoDAO {

    public ContenidoDAOImpl(EntityManagerFactory emf) {
        super(Contenido.class, emf);
    }

    @Override
    public List<Contenido> buscarPorCurso(Long cursoId) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Contenido> query = em.createNamedQuery(
                    "Contenido.buscarPorCurso"
                    , Contenido.class);
            query.setParameter("cursoId", cursoId);
            return query.getResultList();
        }
    }

    @Override
    public void actualizarOrden(Long cursoId, Long materialId, Integer ordenViejo, Integer ordenNuevo) {

        if (ordenViejo.equals(ordenNuevo)) {
            return;
        }

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            em.createQuery("UPDATE Contenido c SET c.orden = -1 WHERE c.id = :materialId")
                    .setParameter("materialId", materialId)
                    .executeUpdate();
            em.flush();

            if (ordenNuevo > ordenViejo) {
                List<Contenido> afectados = em.createQuery(
                                "SELECT c FROM Contenido c WHERE c.curso.id = :cursoId " +
                                        "AND c.orden > :ordenViejo AND c.orden <= :ordenNuevo " +
                                        "ORDER BY c.orden ASC", Contenido.class)
                        .setParameter("cursoId", cursoId)
                        .setParameter("ordenViejo", ordenViejo)
                        .setParameter("ordenNuevo", ordenNuevo)
                        .getResultList();

                for (Contenido c : afectados) {
                    c.setOrden(c.getOrden() - 1);
                    em.flush(); // fuerza el UPDATE ya, en este orden exacto
                }
            } else {
                List<Contenido> afectados = em.createQuery(
                                "SELECT c FROM Contenido c WHERE c.curso.id = :cursoId " +
                                        "AND c.orden >= :ordenNuevo AND c.orden < :ordenViejo " +
                                        "ORDER BY c.orden DESC", Contenido.class)
                        .setParameter("cursoId", cursoId)
                        .setParameter("ordenNuevo", ordenNuevo)
                        .setParameter("ordenViejo", ordenViejo)
                        .getResultList();

                for (Contenido c : afectados) {
                    c.setOrden(c.getOrden() + 1);
                    em.flush();
                }
            }

            em.createQuery("UPDATE Contenido c SET c.orden = :ordenNuevo WHERE c.id = :materialId")
                    .setParameter("ordenNuevo", ordenNuevo)
                    .setParameter("materialId", materialId)
                    .executeUpdate();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void hacerHueco(Long cursoId, Integer orden) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            List<Contenido> afectados = em.createQuery(
                            "SELECT c FROM Contenido c WHERE c.curso.id = :cursoId " +
                                    "AND c.orden >= :orden ORDER BY c.orden DESC", Contenido.class)
                    .setParameter("cursoId", cursoId)
                    .setParameter("orden", orden)
                    .getResultList();

            for (Contenido c : afectados) {
                c.setOrden(c.getOrden() + 1);
                em.flush();
            }

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void cerrarHueco(Long cursoId, Integer ordenEliminado) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            List<Contenido> afectados = em.createQuery(
                            "SELECT c FROM Contenido c WHERE c.curso.id = :cursoId " +
                                    "AND c.orden > :orden ORDER BY c.orden ASC", Contenido.class)
                    .setParameter("cursoId", cursoId)
                    .setParameter("orden", ordenEliminado)
                    .getResultList();

            for (Contenido c : afectados) {
                c.setOrden(c.getOrden() - 1);
                em.flush();
            }

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
