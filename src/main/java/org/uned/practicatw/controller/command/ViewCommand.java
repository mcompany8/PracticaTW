package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;

public class ViewCommand implements Command {

    private final String view;

    public ViewCommand(String view) {
        this.view = view;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return CommandResult.forward(view);
    }
}
