package com.apirest.full.service.faseFinalServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apirest.full.model.faseFinalModels.RoundOf16;
import com.apirest.full.respository.faseFinalRepository.RoundOf16Repo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoundOf16Services {
    
    private final RoundOf16Repo roundOf16Repo;

    public RoundOf16 guardar(RoundOf16 roundOf16){
        return roundOf16Repo.save(roundOf16);
    }

    public List<RoundOf16> obtenerTodo(){
        return roundOf16Repo.findAll();
    }

    public Long cantidad(){
        return roundOf16Repo.count();
    }

    public boolean eliminarItem(Long id){
        try {
            roundOf16Repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarTodo(){
        try {
            roundOf16Repo.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
