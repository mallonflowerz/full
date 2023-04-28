package com.apirest.full.respository.gruposRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.apirest.full.model.gruposModels.GrupoGModel;

public interface GrupoGRepository extends JpaRepository<GrupoGModel, Long> {
    public abstract List<GrupoGModel> findAll(Sort sort);

    List<GrupoGModel> findAllByNombre(String nombre);

    List<GrupoGModel> findTop2ByOrderByPuntajeDesc();
}
