package com.apirest.full.model;

import jakarta.persistence.Entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "grupo")
public class GrupoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "grupo", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<EquipoModel> equipos;
}
