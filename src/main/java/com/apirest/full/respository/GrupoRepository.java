package com.apirest.full.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.GrupoModel;

public interface GrupoRepository extends JpaRepository<GrupoModel, Long>{
    GrupoModel findByNombre(String nombre);
}


