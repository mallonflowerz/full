package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.gruposModels.GrupoDModel;

public interface GrupoDRepository extends JpaRepository<GrupoDModel, Long>{
    public abstract List<GrupoDModel> findAll(Sort sort);
    List<GrupoDModel> findAllByNombre(String nombre);
    List<GrupoDModel> findTop2ByOrderByPuntajeDesc();
}
