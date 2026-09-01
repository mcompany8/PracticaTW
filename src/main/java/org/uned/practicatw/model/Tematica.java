package org.uned.practicatw.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tematicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tematica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private String imagen;

}