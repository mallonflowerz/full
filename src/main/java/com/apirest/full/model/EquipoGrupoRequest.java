package com.apirest.full.model;

import lombok.Data;

@Data
public class EquipoGrupoRequest {
    private String nombre;
    private int puntaje;
    private int partidos;
    private Long grupoId;
}
