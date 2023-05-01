package com.apirest.full.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.full.model.GrupoModel;

public interface GrupoRepository extends JpaRepository<GrupoModel, Long>{
    GrupoModel findByNombre(String nombre);
    // List<GrupoModel> findTop2ByOrderByPuntajeDesc();
}


