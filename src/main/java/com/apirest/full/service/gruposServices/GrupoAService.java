package com.apirest.full.service.gruposServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.gruposModels.GrupoAModel;
import com.apirest.full.respository.gruposRepository.GrupoARepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoAService {

    private final GrupoARepository grupoARepository;

    public List<GrupoAModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoARepository.findAll(sortByPriceDesc);
    }

    public List<GrupoAModel> obtenerEquiposPorNombre(String nombre){
        return grupoARepository.findAllByNombre(nombre);
    }

    public List<GrupoAModel> agregarVarios(Iterable<GrupoAModel> grupoAModels){
        return grupoARepository.saveAll(grupoAModels);
    }

    public List<GrupoAModel> topTwo(){
        return grupoARepository.findTop2ByOrderByPuntajeDesc();
    }

    public GrupoAModel guardarEquipo(GrupoAModel grupoAModel){
        return grupoARepository.save(grupoAModel);
    }

    public Long cantidad(){
        return grupoARepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoARepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoARepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoAModel> list){
        try {
            grupoARepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
