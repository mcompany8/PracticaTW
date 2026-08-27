package org.uned.practicatw.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Nivel;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.CursoServiceImpl;
import org.uned.practicatw.service.ServiceFactory;

import java.io.IOException;
import java.security.Provider;
import java.util.List;

@WebServlet(name = "Curso", value = "/curso")
public class CursoServlet extends HttpServlet {

    private final CursoService cursoService = ServiceFactory.getCursoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            Curso curso = cursoService.obtenerPorId(id).get();

            if (curso == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            req.setAttribute("curso", curso);
            req.getRequestDispatcher("/cursodetalle.jsp").forward(req, resp);
        } else {
            // Listar todos
            List<Curso> cursos = cursoService.listar();
            req.setAttribute("cursos", cursos);
            req.getRequestDispatcher("/listacursos.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String action  = req.getParameter("action");
        switch (action) {
            case "actualizar" -> actualizarCurso(req, resp);

        }



    }

    private void actualizarCurso(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Curso curso = cursoService.obtenerPorId(id).get();
        if (curso == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            throw new ServletException("Curso no encontrado");
        }

        curso.setTitulo(req.getParameter("titulo"));
        curso.setDescripcion(req.getParameter("descripcion"));
        curso.setDuracionHoras(Integer.parseInt(req.getParameter("duracionHoras")));
        curso.setNivel(Nivel.valueOf(req.getParameter("nivel")));
        cursoService.actualizar(curso);


    }
}
