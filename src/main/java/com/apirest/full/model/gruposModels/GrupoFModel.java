package com.apirest.full.model.gruposModels;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupo_f")
public class GrupoFModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idF;

    private String nombre;
    private int puntaje;
    private int partidos;
}
