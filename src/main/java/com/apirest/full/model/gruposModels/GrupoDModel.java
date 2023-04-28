package com.apirest.full.model.gruposModels;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupo_d")
public class GrupoDModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idD;

    private String nombre;
    private int puntaje;
    private int partidos;
}
