package com.apirest.full.service.gruposServices;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.gruposModels.GrupoCModel;
import com.apirest.full.respository.gruposRepository.GrupoCRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GrupoCService {
    
    private final GrupoCRepository grupoCRepository;

    public List<GrupoCModel> obtenerEquipos(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return grupoCRepository.findAll(sortByPriceDesc);
    }

    public List<GrupoCModel> obtenerEquiposPorNombre(String nombre){
        return grupoCRepository.findAllByNombre(nombre);
    }

    public GrupoCModel guardarEquipo(GrupoCModel GrupoCModel){
        return grupoCRepository.save(GrupoCModel);
    }

    public List<GrupoCModel> agregarVarios(Iterable<GrupoCModel> grupoCModels){
        return grupoCRepository.saveAll(grupoCModels);
    }

    public List<GrupoCModel> topTwo(){
        return grupoCRepository.findTop2ByOrderByPuntajeDesc();
    }

    public Long cantidad(){
        return grupoCRepository.count();
    }

    public Boolean eliminarPorId(Long id){
        try {
            grupoCRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            grupoCRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarPorNombre(List<GrupoCModel> list){
        try {
            grupoCRepository.deleteAll(list);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
