package com.apirest.full.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "equipo")
public class EquipoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;
    private int puntaje;
    private int partidos;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private GrupoModel grupo;
    
}
