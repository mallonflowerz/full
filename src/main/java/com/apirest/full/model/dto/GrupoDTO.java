package com.apirest.full.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoDTO {
    private Long id;

    private String nombre;

    private List<EquipoDTO> equipos;
}
