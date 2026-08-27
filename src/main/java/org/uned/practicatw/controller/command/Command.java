package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;

public interface Command {
    CommandResult execute (HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
