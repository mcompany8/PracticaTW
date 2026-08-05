package org.uned.practicatw.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Profesor")
@Getter
@Setter
public class Profesor extends Usuario {

    @OneToMany(mappedBy = "responsable")
    private List<Curso> cursos = new ArrayList<>();

}
