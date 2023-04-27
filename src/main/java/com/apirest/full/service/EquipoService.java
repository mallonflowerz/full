package com.apirest.full.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apirest.full.model.EquipoModel;
import com.apirest.full.respository.EquipoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public List<EquipoModel> obtenerEquipo(){
        Sort sortByPriceDesc = Sort.by(Sort.Order.desc("puntaje"));
        return equipoRepository.findAll(sortByPriceDesc);
    }

    public EquipoModel guardarEquipo(EquipoModel equipoModel){
        return equipoRepository.save(equipoModel);
    }

    public List<EquipoModel> buscarPorPuntaje(int puntaje){
        return equipoRepository.findByPuntaje(puntaje);
    }

    public Optional<EquipoModel> buscarPorId(Long id){
        return equipoRepository.findById(id);
    }

    public void borrarTodo(){
        equipoRepository.deleteAll();
    }

    public Long cantidad(){
        return equipoRepository.count();
    }

    public List<EquipoModel> agregarVarios(List<EquipoModel> equipoModel){
        return equipoRepository.saveAll(equipoModel);
    }

    public boolean eliminar(Long id){
        try {
            equipoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
