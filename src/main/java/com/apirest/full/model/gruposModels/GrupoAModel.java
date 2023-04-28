package com.apirest.full.model.gruposModels;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupo_a")
public class GrupoAModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idA;

    private String nombre;
    private int puntaje;
    private int partidos;
}

