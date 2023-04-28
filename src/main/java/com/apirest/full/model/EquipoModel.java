package com.apirest.full.model;

import java.util.Optional;

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

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private GrupoModel grupo;
    
}
