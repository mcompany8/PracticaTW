package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Usuario con rol de profesor: es responsable de uno o varios {@link Curso},
 * sobre los que puede gestionar información, materiales y tareas, y consultar
 * el listado de alumnos matriculados y las estadísticas de valoraciones.
 * <p>
 * {@link Administrador} extiende esta clase, así que cualquier consulta sobre
 * {@code Profesor} (p. ej. {@code Curso.responsable}) incluye también a los
 * administradores de forma transparente, gracias al polimorfismo de la
 * herencia {@code SINGLE_TABLE} de {@link Usuario}.
 */
@Entity
@DiscriminatorValue(value = "PROFESOR")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(
                name = "Profesor.obtenerProfesores",
                query = "SELECT p FROM Profesor p",
                resultClass = Profesor.class
        )
})
public class Profesor extends Usuario {

    /** Cursos de los que este profesor es responsable. No tiene cascada: eliminar un
     *  profesor con cursos asignados se bloquea explícitamente en la capa de {@code Command}
     *  en vez de arrastrar sus cursos en cascada. */
    @OneToMany(mappedBy = "responsable")
    private List<Curso> cursos = new ArrayList<>();
}