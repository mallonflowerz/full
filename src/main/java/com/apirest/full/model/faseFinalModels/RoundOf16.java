package com.apirest.full.model.faseFinalModels;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "round16")
public class RoundOf16 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String equipo1;
    private int resulEquipo1;
    private int resulEquipo2;
    private String equipo2;
}
