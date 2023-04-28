package com.apirest.full.service.gruposServices;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.gruposModels.GrupoDModel;
import com.apirest.full.respository.gruposRepository.GrupoDRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoDService {
    
    private final GrupoDRepository grupoDRepository;

    public List<GrupoDModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoDRepository.findAll(sortByPriceDesc);
    }

    public List<GrupoDModel> obtenerEquiposPorNombre(String nombre){
        return grupoDRepository.findAllByNombre(nombre);
    }

    public GrupoDModel guardarEquipo(GrupoDModel GrupoDModel){
        return grupoDRepository.save(GrupoDModel);
    }

    public List<GrupoDModel> agregarVarios(Iterable<GrupoDModel> GrupoDModels){
        return grupoDRepository.saveAll(GrupoDModels);
    }

    public List<GrupoDModel> topTwo(){
        return grupoDRepository.findTop2ByOrderByPuntajeDesc();
    }

    public Long cantidad(){
        return grupoDRepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoDRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoDRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoDModel> list){
        try {
            grupoDRepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
