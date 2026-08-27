package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contenidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    public enum TipoContenido {
        ARCHIVO, URL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tipo_contenido",
            length = 16,
            nullable = false
    )
    private TipoContenido tipoContenido;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "propietario_id",
            foreignKey = @ForeignKey(name = "fk_propietario")
    )
    private Profesor propietario;


}
