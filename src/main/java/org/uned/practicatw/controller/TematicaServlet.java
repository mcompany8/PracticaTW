package org.uned.practicatw.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.uned.practicatw.model.Tematica;
import org.uned.practicatw.utils.JPAUtil;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Tematica", description = "", value = "/tematica")
public class TematicaServlet extends HttpServlet {

    private static final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        List<Tematica> lt = em.createNamedQuery("Tematica.selectAll", Tematica.class).getResultList();
        req.setAttribute("listaTematicas", lt);
        req.getRequestDispatcher("tematica_dos.jsp").forward(req, resp);
    }
}