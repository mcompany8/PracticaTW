package org.uned.practicatw.service;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.uned.practicatw.dao.*;

public class ServiceFactory {

    private static EntityManagerFactory emf;

    private static AdministradorDAO administradorDAO;
    private static ContenidoDAO contenidoDAO;
    private static CursoDAO cursoDAO;
    private static EntregaTareaDAO entregaTareaDAO;
    private static EstudianteDAO estudianteDAO;
    private static EstudianteTematicaDAO estudianteTematicaDAO;
    private static InscripcionDAO inscripcionDAO;
    private static ProfesorDAO profesorDAO;
    private static TareaDAO tareaDAO;
    private static TematicaDAO tematicaDAO;
    private static UsuarioDAO usuarioDAO;
    private static ValoracionDAO valoracionDAO;
    private static ConfiguracionDAO configuracionDAO;


    private static AdministradorService administradorService;
    private static ContenidoService contenidoService;
    private static CursoService cursoService;
    private static EntregaTareaService entregaTareaService;
    private static EstudianteService estudianteService;
    private static EstudianteTematicaService estudianteTematicaService;
    private static InscripcionService inscripcionService;
    private static ProfesorService profesorService;
    private static TareaService tareaService;
    private static TematicaService tematicaService;
    private static UsuarioService usuarioService;
    private static ValoracionService valoracionService;

    private static AuthService authService;
    private static ConfiguracionService configuracionService;


    private ServiceFactory() {
    }

    public static void init(EntityManagerFactory entityManagerFactory) {
        emf = entityManagerFactory;

        administradorDAO = new AdministradorDAOImpl(emf);
        contenidoDAO = new ContenidoDAOImpl(emf);
        cursoDAO = new CursoDAOImpl(emf);
        entregaTareaDAO = new EntregaTareaDAOImpl(emf);
        estudianteDAO = new EstudianteDAOImpl(emf);
        estudianteTematicaDAO = new EstudianteTematicaDAOImpl(emf);
        inscripcionDAO = new InscripcionDAOImpl(emf);
        profesorDAO = new ProfesorDAOImpl(emf);
        tareaDAO = new TareaDAOImpl(emf);
        tematicaDAO = new TematicaDAOImpl(emf);
        usuarioDAO = new UsuarioDAOImpl(emf);
        valoracionDAO = new ValoracionDAOImpl(emf);
        configuracionDAO = new ConfiguracionDAOImpl(emf);


        authService = new AuthServiceImpl(usuarioDAO);
        configuracionService = new ConfiguracionServiceImpl(configuracionDAO);
        contenidoService = new ContenidoServiceImpl(contenidoDAO);
        cursoService = new CursoServiceImpl(cursoDAO);
        entregaTareaService =  new EntregaTareaServiceImpl(entregaTareaDAO);
        estudianteService =  new EstudianteServiceImpl(estudianteDAO);
        estudianteTematicaService = new EstudianteTematicaServiceImpl(estudianteTematicaDAO);
        inscripcionService = new InscripcionServiceImpl(inscripcionDAO);
        profesorService = new ProfesorServiceImpl(profesorDAO);
        tareaService = new TareaServiceImpl(tareaDAO);
        tematicaService = new TematicaServiceImpl(tematicaDAO);
        usuarioService = new UsuarioServiceImpl(usuarioDAO);
        valoracionService = new ValoracionServiceImpl(valoracionDAO);
    }

    public static AuthService getAuthService() {return authService;}
    public static ContenidoService getContenidoService() {return contenidoService;}
    public static CursoService getCursoService() {return cursoService;}
    public static EntregaTareaService getEntregaTareaService() {return entregaTareaService;}
    public static EstudianteService getEstudianteService() {return estudianteService;}
    public static EstudianteTematicaService getEstudianteTematicaService() {return estudianteTematicaService;}
    public static InscripcionService getInscripcionService() {return inscripcionService;}
    public static ProfesorService getProfesorService() {return profesorService;}
    public static TareaService getTareaService() {return tareaService;}
    public static TematicaService getTematicaService() {return tematicaService;}
    public static UsuarioService getUsuarioService() {return usuarioService;}
    public static ValoracionService getValoracionService() {return valoracionService;}
    public static ConfiguracionService getConfiguracionService() {return configuracionService;}

}
