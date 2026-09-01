package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ContenidoService;

import java.util.List;

public class AccesoAsignarContenidosCommand implements Command {

    private ContenidoService contenidoService;

    public AccesoAsignarContenidosCommand(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    @Override
    public CommandResult execute (HttpServletRequest req, HttpServletResponse resp) throws Exception{

        Usuario propietario = (Usuario) req.getSession().getAttribute("usuario");


        System.out.println();
        List<Contenido> contenidos = contenidoService.obtenerTodos();
        req.setAttribute("contenidos", contenidos);
        return CommandResult.forward("/WEB-INF/views/asignarContenido.jsp");
    }
}
