package org.uned.practicatw.controller;

import lombok.Getter;

@Getter
public class CommandResult {

    public enum NavigationType {
        FORWARD,
        REDIRECT
    }

    private final NavigationType navigationType;
    private final String view;

    public CommandResult(NavigationType navigationType, String view) {
        this.navigationType = navigationType;
        this.view = view;
    }

    public static CommandResult forward (String view) {
        return new CommandResult(NavigationType.FORWARD, view);
    }

    public static CommandResult redirect (String view) {
        return new CommandResult(NavigationType.REDIRECT, view);
    }
}
