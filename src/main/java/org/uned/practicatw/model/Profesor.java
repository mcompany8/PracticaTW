package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "responsable")
    private List<Curso> cursos = new ArrayList<>();



}
