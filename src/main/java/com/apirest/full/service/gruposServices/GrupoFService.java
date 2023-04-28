package com.apirest.full.service.gruposServices;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.gruposModels.GrupoFModel;
import com.apirest.full.respository.gruposRepository.GrupoFRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoFService {
    
    private final GrupoFRepository grupoFRepository;

    public List<GrupoFModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoFRepository.findAll(sortByPriceDesc);
    }

    public List<GrupoFModel> obtenerEquiposPorNombre(String nombre){
        return grupoFRepository.findAllByNombre(nombre);
    }

    public GrupoFModel guardarEquipo(GrupoFModel GrupoFModel){
        return grupoFRepository.save(GrupoFModel);
    }

    public List<GrupoFModel> agregarVarios(Iterable<GrupoFModel> GrupoFModels){
        return grupoFRepository.saveAll(GrupoFModels);
    }

    public List<GrupoFModel> topTwo(){
        return grupoFRepository.findTop2ByOrderByPuntajeDesc();
    }

    public Long cantidad(){
        return grupoFRepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoFRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoFRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoFModel> list){
        try {
            grupoFRepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
