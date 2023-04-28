package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.gruposModels.GrupoEModel;

public interface GrupoERepository extends JpaRepository<GrupoEModel, Long>{
    public abstract List<GrupoEModel> findAll(Sort sort);
    List<GrupoEModel> findAllByNombre(String nombre);
    List<GrupoEModel> findTop2ByOrderByPuntajeDesc();
}
