package org.uned.practicatw.model;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

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
    @Column(unique = true, nullable = false)
    private String email;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 100, nullable = false)
    private String apellidos;
    @Column(name = "password_hash", nullable = false, length = 60)
    private String password;
    private String direccion;
    private String poblacion;
    private String provincia;
    @Column(name = "codigo_postal")
    private String codigopostal;

    public String getTipoUsuario() {
        return this.getClass().getSimpleName();
    }


}
