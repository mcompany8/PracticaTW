package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;

import java.lang.reflect.Type;
import java.util.List;

public class InscripcionDAOImpl extends GenericDAOImpl<Inscripcion> implements InscripcionDAO {

    public InscripcionDAOImpl(EntityManagerFactory emf) {
        super(Inscripcion.class, emf);
    }

    @Override
    public List<Estudiante> buscarEstudiantesPorCurso(Long cursoId) {

        try (EntityManager em = getEntityManager()) {
            TypedQuery<Estudiante> query = em.createNamedQuery(
                    "Inscripcion.buscarEstudiantesPorCurso"
                    , Estudiante.class);
            query.setParameter("cursoId", cursoId);
            return query.getResultList();
        }
    }

    @Override
    public List<Curso> buscarCursosPorEstudiante(Long estudianteId) {
        try(EntityManager em = getEntityManager()) {
            TypedQuery<Curso> query = em.createNamedQuery(
                    "Inscripcion.buscarCursosPorEstudiante"
                    , Curso.class);
            query.setParameter("estudianteId", estudianteId);
            return query.getResultList();
        }
    }

    @Override
    public Inscripcion buscarPorCursoAndEstudiante(Long cursoId, Long estudianteId) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Inscripcion> query = em.createNamedQuery(
                    "Inscripcion.buscarPorCursoAndEstudiante"
                    , Inscripcion.class);
            query.setParameter("cursoId", cursoId);
            query.setParameter("estudianteId", estudianteId);
            return query.getSingleResultOrNull();
        }
    }

    @Override
    public List<Inscripcion> buscarPorEstudiante(Long estudianteId) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Inscripcion> query = em.createNamedQuery(
                    "Inscripcion.buscarPorEstudiante"
                    , Inscripcion.class);
            query.setParameter("estudianteId", estudianteId);
            return query.getResultList();
        }
    }

    @Override
    public List<Inscripcion> buscarPorCurso(Long cursoId) {
        try (EntityManager em = getEntityManager()) {
            TypedQuery<Inscripcion> query = em.createNamedQuery(
                    "Inscripcion.buscarPorCurso", Inscripcion.class);
            query.setParameter("cursoId", cursoId);
            return query.getResultList();
        }
    }
}
