package com.apirest.full.respository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.apirest.full.model.EquipoModel;

//@Repository
public interface EquipoRepository extends JpaRepository<EquipoModel, Long> {
    public abstract List<EquipoModel> findByPuntaje(int puntaje);
    public abstract List<EquipoModel> findAll(Sort sort);
}
