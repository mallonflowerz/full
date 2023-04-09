package com.apirest.full.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.GrupoAModel;
import com.apirest.full.respository.GrupoARepository;

@Service
public class GrupoAService {
    @Autowired
    private GrupoARepository grupoARepository;

    public List<GrupoAModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoARepository.findAll(sortByPriceDesc);
    }

    public GrupoAModel guardarEquipo(GrupoAModel grupoAModel){
        return grupoARepository.save(grupoAModel);
    }

    public Long cantidad(){
        return grupoARepository.count();
    }

    public Boolean eliminar(Long id){
        try {
            grupoARepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
