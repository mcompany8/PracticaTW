package org.uned.practicatw.controller;

import lombok.Getter;

/**
 * Resultado devuelto por un {@link org.uned.practicatw.controller.command.Command}
 * tras ejecutarse, indicando al {@link FrontController} cómo debe continuar
 * la respuesta HTTP: reenviando la petición a una vista JSP interna, o
 * redirigiendo al navegador a otra URL (patrón Post/Redirect/Get).
 * <p>
 * El significado de {@link #getView()} depende del {@link NavigationType}:
 * <ul>
 *     <li>{@link NavigationType#FORWARD}: una ruta interna al servlet container,
 *     siempre bajo {@code /WEB-INF/...} (p. ej. {@code "/WEB-INF/views/curso.jsp"}).
 *     No es una URL pública — el navegador nunca la ve ni puede acceder a ella
 *     directamente.</li>
 *     <li>{@link NavigationType#REDIRECT}: una ruta de la aplicación, relativa al
 *     context path (p. ej. {@code "/app/login"}). El {@code FrontController} le
 *     antepone {@code request.getContextPath()} antes de llamar a
 *     {@code response.sendRedirect(...)}, así que aquí no hay que incluirlo a mano.</li>
 * </ul>
 * Mezclar estos dos formatos (por ejemplo, hacer un {@code forward} a una ruta
 * pública tipo {@code "/app/login"}, o un {@code redirect} a una ruta interna
 * tipo {@code "/WEB-INF/..."}) no lanza ningún error en tiempo de compilación,
 * pero falla en tiempo de ejecución — es un fallo fácil de cometer al escribir
 * un {@code Command} nuevo, así que conviene usar siempre los métodos de
 * fábrica ({@link #forward(String)} / {@link #redirect(String)}) del tipo que
 * corresponda en vez de instanciar la clase directamente.
 */
@Getter
public class CommandResult {

    /**
     * Distingue si la navegación resultante es un {@code forward} interno
     * (la URL en el navegador no cambia) o un {@code redirect} HTTP (el
     * navegador recibe un 302 y hace una nueva petición GET).
     */
    public enum NavigationType {
        /** Reenvío interno del servlet container a una vista JSP bajo {@code /WEB-INF/}. */
        FORWARD,
        /** Redirección HTTP a una ruta pública de la aplicación, relativa al context path. */
        REDIRECT
    }

    private final NavigationType navigationType;
    private final String view;

    /**
     * Construye un resultado directamente. En la práctica, se usan casi
     * siempre los métodos de fábrica {@link #forward(String)} y
     * {@link #redirect(String)} en su lugar, que dejan más claro en el
     * código del {@code Command} qué tipo de navegación se está devolviendo.
     *
     * @param navigationType el tipo de navegación a realizar
     * @param view           la vista o ruta de destino (ver la documentación
     *                       de la clase sobre el formato esperado según el tipo)
     */
    public CommandResult(NavigationType navigationType, String view) {
        this.navigationType = navigationType;
        this.view = view;
    }

    /**
     * Crea un resultado de tipo {@link NavigationType#FORWARD}.
     *
     * @param view ruta interna a la vista JSP, siempre empezando por
     *             {@code "/WEB-INF/..."}
     * @return el {@code CommandResult} correspondiente
     */
    public static CommandResult forward(String view) {
        return new CommandResult(NavigationType.FORWARD, view);
    }

    /**
     * Crea un resultado de tipo {@link NavigationType#REDIRECT}.
     *
     * @param view ruta de la aplicación a la que redirigir, relativa al
     *             context path (p. ej. {@code "/app/misCursos"}) — sin
     *             anteponer el context path, eso lo hace el {@link FrontController}
     * @return el {@code CommandResult} correspondiente
     */
    public static CommandResult redirect(String view) {
        return new CommandResult(NavigationType.REDIRECT, view);
    }
}