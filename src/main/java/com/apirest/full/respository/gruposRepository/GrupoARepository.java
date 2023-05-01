package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.gruposModels.GrupoAModel;

public interface GrupoARepository extends JpaRepository<GrupoAModel, Long>{
    public abstract List<GrupoAModel> findAll(Sort sort);
    List<GrupoAModel> findAllByNombre(String nombre);
    List<GrupoAModel> findTop2ByOrderByPuntajeDesc(); 
}
