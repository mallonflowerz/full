package com.apirest.full.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grupo_c")
public class GrupoCModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idC;

    private String nombre;
    private int puntaje;
}
