package com.apirest.full.respository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apirest.full.model.EquipoModel;

//@Repository
public interface EquipoRepository extends JpaRepository<EquipoModel, Long> {
    public abstract List<EquipoModel> findByPuntaje(int puntaje);
    public abstract List<EquipoModel> findAll(Sort sort);

    @Query("SELECT e FROM EquipoModel e WHERE e.grupo.id = :id ORDER BY e.puntaje DESC")
    List<EquipoModel> findByGrupo(@Param("id") Long id);
    @Query("SELECT e FROM EquipoModel e WHERE e.grupo.id = :grupoId ORDER BY e.puntaje DESC LIMIT 2")
    List<EquipoModel> encontrarDosMejoresEquiposPorGrupo(@Param("grupoId") Long grupoId);

}
