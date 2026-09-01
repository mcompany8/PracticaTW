package org.uned.practicatw.controller;

import org.uned.practicatw.controller.command.*;
import org.uned.practicatw.model.ConfiguracionSistema;
import org.uned.practicatw.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final Map<String, Command> commands = new HashMap<>();

    public static void init () {
        AuthService authService = ServiceFactory.getAuthService();
        CursoService cursoService = ServiceFactory.getCursoService();
        EstudianteTematicaService estudianteTematicaService = ServiceFactory.getEstudianteTematicaService();
        UsuarioService usuarioService = ServiceFactory.getUsuarioService();
        InscripcionService inscripcionService = ServiceFactory.getInscripcionService();
        ContenidoService contenidoService = ServiceFactory.getContenidoService();
        ProfesorService profesorService = ServiceFactory.getProfesorService();
        TematicaService tematicaService = ServiceFactory.getTematicaService();
        ValoracionService valoracionService = ServiceFactory.getValoracionService();
        ConfiguracionService configuracionService = ServiceFactory.getConfiguracionService();

        commands.put("editarConfiguracion", new EditarConfiguracionCommand(configuracionService));
        commands.put("guardarConfiguracion", new GuardarConfiguracionCommand(configuracionService));
        commands.put("listarTematicas", new ListarTematicasCommand(tematicaService));
        commands.put("crearTematica", new MostrarCrearTematicaCommand());
        commands.put("guardarTematica", new GuardarTematicaCommand(tematicaService));
        commands.put("editarTematica", new EditarTematicaCommand(tematicaService));
        commands.put("actualizarTematica", new ActualizarTematicaCommand(tematicaService));
        commands.put("eliminarTematica", new EliminarTematicaCommand(tematicaService, cursoService));
        commands.put("admin", new AdminCommand(usuarioService, cursoService, inscripcionService, valoracionService));
        commands.put("listarUsuarios", new ListarUsuariosCommand(usuarioService));
        commands.put("crearUsuarioAdmin", new MostrarCrearUsuarioAdminCommand());
        commands.put("guardarUsuarioAdmin", new GuardarUsuarioAdminCommand(usuarioService));
        commands.put("editarUsuarioAdmin", new EditarUsuarioAdminCommand(usuarioService));
        commands.put("actualizarUsuarioAdmin", new ActualizarUsuarioAdminCommand(usuarioService));
        commands.put("eliminarUsuarioAdmin", new EliminarUsuarioAdminCommand(usuarioService, cursoService));
        commands.put("crearCurso", new MostrarCrearCursoCommand(tematicaService));
        commands.put("guardarCurso", new GuardarCursoCommand(cursoService, tematicaService));
        commands.put("eliminarCurso", new EliminarCursoCommand(cursoService));
        commands.put("anadirMaterial", new AnadirMaterialCommand(contenidoService, cursoService));
        commands.put("eliminarMaterial", new EliminarMaterialCommand(contenidoService, cursoService));
        commands.put("editarMaterial", new EditarMaterialCommand(contenidoService, cursoService));
        commands.put("guardarMaterial", new GuardarMaterialCommand(contenidoService, cursoService));
        commands.put("actualizarCurso", new ActualizarCursoCommand(cursoService, tematicaService));
        commands.put("actualizarOrdenMaterial", new ActualizarOrdenMaterialCommand(contenidoService, cursoService));
        commands.put("perfil", new MostrarPerfilCommand(tematicaService, estudianteTematicaService));
        commands.put("actualizarPerfil", new ActualizarPerfilCommand(usuarioService, tematicaService, estudianteTematicaService));
        commands.put("miInscripcion", new VerInscripcionCommand(cursoService, inscripcionService, contenidoService));
        commands.put("curso", new VerCursoCommand(cursoService, inscripcionService));
        commands.put("inscripcion", new InscripcionCommand(inscripcionService, cursoService));
        commands.put("desinscripcion", new DesinscripcionCommand(inscripcionService));
        commands.put("catalogo", new CatalogoCommand(cursoService, tematicaService));
        commands.put("cursosProf", new ListarCursosProfesorCommand(cursoService));
        commands.put("estudiantesCurso", new ListarEstudiantesPorCursoCommand(inscripcionService));
        commands.put("detalleCurso", new DetalleCursoProfesorCommand(
                cursoService,
                inscripcionService,
                contenidoService,
                tematicaService,
                valoracionService));
        commands.put("subirContenido", new SubirContenidoCommand(contenidoService, cursoService));
        commands.put("cursoContenido", new AccesoAsignarContenidosCommand(contenidoService));
        commands.put("valorarCurso", new ValorarCursoCommand(valoracionService, inscripcionService));
        commands.put("doLogin", new LoginCommand(authService));
        commands.put("logout", new CerrarSesionCommand());
        commands.put("login", new ViewCommand("/WEB-INF/views/login.jsp"));
        commands.put("registro", new MostrarRegistroCommand(tematicaService));
        commands.put("inicio", new IndexCommand(cursoService, configuracionService));
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
