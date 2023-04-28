package com.apirest.full.model.mapper;

import org.springframework.stereotype.Service;

import com.apirest.full.model.EquipoModel;
import com.apirest.full.model.dto.EquipoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipoMapper implements GenericMapper<EquipoModel, EquipoDTO>{

    @Override
    public EquipoModel dtoToPojo(EquipoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dtoToPojo'");
    }

    @Override
    public EquipoDTO pojoToDto(EquipoModel pojo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pojoToDto'");
    }
    
    
}
