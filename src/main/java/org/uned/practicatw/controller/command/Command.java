package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;

/**
 * Unidad básica del patrón Command usado por {@link org.uned.practicatw.controller.FrontController}.
 * <p>
 * Cada implementación encapsula un caso de uso concreto de la aplicación
 * (por ejemplo, "matricular a un alumno en un curso" o "eliminar un material").
 * El {@code FrontController} resuelve, a partir del {@code pathInfo} de la
 * petición, qué {@code Command} ejecutar (ver {@link org.uned.practicatw.controller.CommandFactory}),
 * y delega en él toda la lógica de negocio, dejando al propio {@code Command}
 * decidir si el resultado se sirve mediante un {@code forward} a una vista JSP
 * o mediante un {@code redirect} a otra ruta (patrón Post/Redirect/Get).
 * <p>
 * Las implementaciones son responsables de:
 * <ul>
 *     <li>Leer los parámetros de la petición ({@code request.getParameter(...)}).</li>
 *     <li>Comprobar que el usuario en sesión tiene permiso para la acción
 *     (control de acceso por rol e IDOR — nunca confiar en un id recibido
 *     del cliente sin verificar que pertenece al usuario logueado).</li>
 *     <li>Invocar a la capa de {@code Service} correspondiente.</li>
 *     <li>Construir el {@link CommandResult} con la vista o ruta de destino.</li>
 * </ul>
 */
public interface Command {

    /**
     * Ejecuta la lógica de negocio asociada a este {@code Command} y determina
     * cómo debe continuar la respuesta HTTP.
     *
     * @param req  la petición HTTP entrante, con los parámetros del formulario/query
     *             string y la sesión del usuario actualmente logueado (si lo hay)
     * @param resp la respuesta HTTP; normalmente no se escribe directamente sobre
     *             ella aquí, ya que es el {@code FrontController} quien realiza el
     *             {@code forward}/{@code redirect} a partir del {@link CommandResult} devuelto
     * @return el resultado de la operación: la vista a la que hacer {@code forward}
     *         o la ruta a la que hacer {@code redirect}
     * @throws Exception si ocurre cualquier error no controlado durante la ejecución
     *                    (p. ej. de acceso a datos); el {@code FrontController} lo
     *                    envuelve en una {@link RuntimeException} y lo deja subir
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}