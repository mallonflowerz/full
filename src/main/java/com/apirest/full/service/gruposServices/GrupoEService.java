package com.apirest.full.service.gruposServices;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.gruposModels.GrupoEModel;
import com.apirest.full.respository.gruposRepository.GrupoERepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoEService {
    
    private final GrupoERepository grupoERepository;

    public List<GrupoEModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoERepository.findAll(sortByPriceDesc);
    }

    public List<GrupoEModel> obtenerEquiposPorNombre(String nombre){
        return grupoERepository.findAllByNombre(nombre);
    }

    public GrupoEModel guardarEquipo(GrupoEModel GrupoEModel){
        return grupoERepository.save(GrupoEModel);
    }

    public List<GrupoEModel> agregarVarios(Iterable<GrupoEModel> GrupoEModels){
        return grupoERepository.saveAll(GrupoEModels);
    }

    public List<GrupoEModel> topTwo(){
        return grupoERepository.findTop2ByOrderByPuntajeDesc();
    }

    public Long cantidad(){
        return grupoERepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoERepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoERepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoEModel> list){
        try {
            grupoERepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
