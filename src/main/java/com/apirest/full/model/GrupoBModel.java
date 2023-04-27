package com.apirest.full.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupo_b")
public class GrupoBModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idB;

    private String nombre;
    private int puntaje;
}
