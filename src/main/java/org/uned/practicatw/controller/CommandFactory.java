package org.uned.practicatw.controller;

import org.uned.practicatw.command.MisCursosCommand;
import org.uned.practicatw.controller.command.*;
import org.uned.practicatw.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, Command> commands = new HashMap<>();

    public static void init () {
        AuthService authService = ServiceFactory.getAuthService();
        CursoService cursoService = ServiceFactory.getCursoService();
        UsuarioService usuarioService = ServiceFactory.getUsuarioService();
        InscripcionService inscripcionService = ServiceFactory.getInscripcionService();
        ContenidoService contenidoService = ServiceFactory.getContenidoService();
        ProfesorService profesorService = ServiceFactory.getProfesorService();
        TematicaService tematicaService = ServiceFactory.getTematicaService();



        commands.put("misCursos", new MisCursosCommand(cursoService, inscripcionService));
        commands.put("curso", new VerCursoCommand(cursoService, inscripcionService));
        commands.put("inscripcion", new InscripcionCommand(inscripcionService, cursoService));
        commands.put("catalogo", new CatalogoCommand(cursoService, tematicaService));
        commands.put("crearCurso", new CrearCursoCommand(cursoService, profesorService));
        commands.put("listarUsuarios", new ListarUsuariosCommand(usuarioService));
        commands.put("cursosProf", new ListarCursosProfesorCommand(cursoService));
        commands.put("estudiantesCurso", new ListarEstudiantesPorCursoCommand(inscripcionService));
        commands.put("detalleCurso", new DetalleCursoProfesorCommand(cursoService, inscripcionService));
        commands.put("subirContenido", new SubirContenidoCommand(contenidoService, cursoService));
        commands.put("cursoContenido", new AccesoAsignarContenidosCommand(contenidoService));
        commands.put("doLogin", new LoginCommand(authService));
        commands.put("logout", new CerrarSesionCommand());
        commands.put("login", new ViewCommand("/WEB-INF/views/login.jsp"));
        commands.put("registro", new ViewCommand("/WEB-INF/views/registro.jsp"));
        commands.put("inicio", new IndexCommand(cursoService));
        commands.put("contenido", new  ViewCommand("/WEB-INF/views/subirContenido.jsp"));
        commands.put("notFound", new  ViewCommand("/WEB-INF/views/error/404.jsp"));

    }

    public static Command getCommand(String path) {

        if (path == null) {
            return commands.get("inicio");
        }

        Command command = commands.get(path);

        if (command == null) {
            return commands.get("notFound");
        }
        return command;
    }
}
