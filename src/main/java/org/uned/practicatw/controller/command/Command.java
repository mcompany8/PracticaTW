package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;

public interface Command {


    /**
     * Ejecuta el Command con la lógica deseada y devuelve un CommandResult.
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    CommandResult execute (HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
