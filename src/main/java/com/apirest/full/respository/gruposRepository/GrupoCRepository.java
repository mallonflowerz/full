package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.gruposModels.GrupoCModel;

public interface GrupoCRepository extends JpaRepository<GrupoCModel, Long>{
    public abstract List<GrupoCModel> findAll(Sort sort);
    List<GrupoCModel> findAllByNombre(String nombre);
    List<GrupoCModel> findTop2ByOrderByPuntajeDesc();
}
