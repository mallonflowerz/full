package com.apirest.full.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.GrupoBModel;
import com.apirest.full.respository.GrupoBRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoBService {
    private final GrupoBRepository grupoBRepository;

    public List<GrupoBModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoBRepository.findAll(sortByPriceDesc);
    }

    public List<GrupoBModel> obtenerEquiposPorNombre(String nombre){
        return grupoBRepository.findAllByNombre(nombre);
    }

    public GrupoBModel guardarEquipo(GrupoBModel GrupoBModel){
        return grupoBRepository.save(GrupoBModel);
    }

    public Long cantidad(){
        return grupoBRepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoBRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoBRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoBModel> list){
        try {
            grupoBRepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
