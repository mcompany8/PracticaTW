package org.uned.practicatw.controller;

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

        commands.put("listarUsuarios", new ListarUsuariosCommand(usuarioService));
        commands.put("cursosProf", new ListarCursosProfesorCommand(cursoService));
        commands.put("estudiantesCurso", new ListarEstudiantesPorCursoCommand(inscripcionService));
        commands.put("detalleCurso", new DetalleCursoCommand(cursoService, inscripcionService));
        commands.put("doLogin", new LoginCommand(authService));
        commands.put("logout", new CerrarSesionCommand());
        commands.put("login", new ViewCommand("/WEB-INF/views/login.jsp"));
        commands.put("inicio", new ViewCommand("/WEB-INF/views/index.jsp"));
        commands.put("notFound", new  ViewCommand("/WEB-INF/views/error/500.jsp"));
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
