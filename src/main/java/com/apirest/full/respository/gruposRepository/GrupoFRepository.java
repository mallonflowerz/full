package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.apirest.full.model.gruposModels.GrupoFModel;

public interface GrupoFRepository extends JpaRepository<GrupoFModel, Long> {
    public abstract List<GrupoFModel> findAll(Sort sort);

    List<GrupoFModel> findAllByNombre(String nombre);

    List<GrupoFModel> findTop2ByOrderByPuntajeDesc();
}
