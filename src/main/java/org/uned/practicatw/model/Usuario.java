package org.uned.practicatw.model;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Clase base de la jerarquía de usuarios de la aplicación.
 * <p>
 * Usa herencia {@link InheritanceType#SINGLE_TABLE}: todas las subclases
 * ({@link Estudiante}, {@link Profesor}, {@link Administrador}) se almacenan
 * en una única tabla {@code usuarios}, distinguidas por la columna
 * discriminadora {@code tipo_usuario}. Esto tiene una consecuencia práctica
 * importante: el tipo concreto de un usuario ya persistido no se puede
 * cambiar reasignando el objeto Java — solo actualizando esa columna
 * directamente (ver {@code UsuarioDAO.cambiarTipo(Long, String)}), y solo es
 * seguro hacerlo entre {@link Profesor} y {@link Administrador} (que
 * comparten exactamente las mismas relaciones), no hacia o desde
 * {@link Estudiante}.
 * <p>
 * Es {@code abstract}: no se persiste nunca un {@code Usuario} "a secas",
 * siempre a través de una de sus subclases concretas.
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@NamedQueries({
        @NamedQuery(
                name= "Usuario.buscarPorEmail",
                query = "SELECT u FROM Usuario u WHERE u.email = :email"
        )
})
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** Identificador de acceso; único en toda la tabla, independientemente del rol. */
    @Column(unique = true, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 100, nullable = false)
    private String apellidos;
    /** Hash BCrypt de la contraseña (nunca la contraseña en claro). */
    @Column(name = "password_hash", nullable = false, length = 60)
    private String password;
    private String direccion;
    private String poblacion;
    private String provincia;
    @Column(name = "codigo_postal")
    private String codigopostal;

    /**
     * Nombre del rol del usuario, derivado del nombre de la subclase concreta
     * ({@code "Estudiante"}, {@code "Profesor"} o {@code "Administrador"}).
     * Se usa en las vistas JSP para renderizado condicional por rol
     * (p. ej. {@code sessionScope.usuario.tipoUsuario == 'Estudiante'}) y en
     * los {@code Command} para comprobaciones de acceso mediante {@code instanceof}.
     *
     * @return el nombre simple de la clase concreta del usuario
     */
    public String getTipoUsuario() {
        return this.getClass().getSimpleName();
    }


}