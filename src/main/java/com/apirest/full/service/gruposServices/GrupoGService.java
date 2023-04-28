package com.apirest.full.service.gruposServices;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.gruposModels.GrupoGModel;
import com.apirest.full.respository.gruposRepository.GrupoGRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoGService {
    
    private final GrupoGRepository grupoGRepository;

    public List<GrupoGModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoGRepository.findAll(sortByPriceDesc);
    }

    public List<GrupoGModel> obtenerEquiposPorNombre(String nombre){
        return grupoGRepository.findAllByNombre(nombre);
    }

    public GrupoGModel guardarEquipo(GrupoGModel GrupoGModel){
        return grupoGRepository.save(GrupoGModel);
    }

    public List<GrupoGModel> agregarVarios(Iterable<GrupoGModel> GrupoGModels){
        return grupoGRepository.saveAll(GrupoGModels);
    }

    public List<GrupoGModel> topTwo(){
        return grupoGRepository.findTop2ByOrderByPuntajeDesc();
    }

    public Long cantidad(){
        return grupoGRepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoGRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoGRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoGModel> list){
        try {
            grupoGRepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
