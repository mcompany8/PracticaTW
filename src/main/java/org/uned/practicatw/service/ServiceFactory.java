package org.uned.practicatw.service;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.uned.practicatw.dao.*;

public class ServiceFactory {

    private static EntityManagerFactory emf;

    private static CursoDAO cursoDAO;
    private static UsuarioDAO usuarioDAO;
    private static EstudianteDAO estudianteDAO;
    private static ProfesorDAO profesorDAO;
    private static AdministradorDAO administradorDAO;
    private static TematicaDAO tematicaDAO;
    private static TareaDAO tareaDAO;
    private static InscripcionDAO inscripcionDAO;
    private static EntregaTareaDAO entregaTareaDAO;
    private static ContenidoDAO contenidoDAO;


    private static CursoService cursoService;
    private static UsuarioService usuarioService;
    private static InscripcionService inscripcionService;
    private static ContenidoService contenidoService;

    private static AuthService authService;


    private ServiceFactory() {
    }

    public static void init(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;

        cursoDAO = new CursoDAOImpl(emf);
        usuarioDAO = new UsuarioDAOImpl(emf);
        estudianteDAO = new EstudianteDAOImpl(emf);
        profesorDAO = new ProfesorDAOImpl(emf);
        administradorDAO = new AdministradorDAOImpl(emf);
        tematicaDAO = new TematicaDAOImpl(emf);
        tareaDAO = new TareaDAOImpl(emf);
        inscripcionDAO = new InscripcionDAOImpl(emf);
        entregaTareaDAO = new EntregaTareaDAOImpl(emf);
        contenidoDAO = new ContenidoDAOImpl(emf);


        authService = new AuthServiceImpl(usuarioDAO);
        cursoService = new CursoServiceImpl(cursoDAO);
        usuarioService = new UsuarioServiceImpl(usuarioDAO);
        inscripcionService = new InscripcionServiceImpl(inscripcionDAO);
        contenidoService = new ContenidoServiceImpl(contenidoDAO);
    }

    public static AuthService getAuthService() {return authService;}
    public static CursoService getCursoService() {return cursoService;}
    public static UsuarioService getUsuarioService() {return usuarioService;}
    public static InscripcionService getInscripcionService() {return inscripcionService;}
    public static ContenidoService getContenidoService() {return contenidoService;}

}
