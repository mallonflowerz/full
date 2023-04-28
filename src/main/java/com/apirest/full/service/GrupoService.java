package com.apirest.full.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apirest.full.model.GrupoModel;
import com.apirest.full.respository.GrupoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoService {
    
    private final GrupoRepository grupoRepository;


    public GrupoModel guardar(GrupoModel grupoModel){
        return grupoRepository.save(grupoModel);
    }

    public List<GrupoModel> obtenerAll(){
        return grupoRepository.findAll();
    }

    public Optional<GrupoModel> obtenerPorId(Long id){
        return grupoRepository.findById(id);
    }

    public GrupoModel buscarPorNombre(String nombre){
        return grupoRepository.findByNombre(nombre);
    }
}
