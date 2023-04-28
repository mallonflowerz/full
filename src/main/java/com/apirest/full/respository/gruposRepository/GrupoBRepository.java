package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.gruposModels.GrupoBModel;

public interface GrupoBRepository extends JpaRepository<GrupoBModel, Long>{
    public abstract List<GrupoBModel> findAll(Sort sort);
    List<GrupoBModel> findAllByNombre(String nombre);
    List<GrupoBModel> findTop2ByOrderByPuntajeDesc();
}
