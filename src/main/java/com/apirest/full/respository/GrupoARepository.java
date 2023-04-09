package com.apirest.full.respository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.GrupoAModel;

public interface GrupoARepository extends JpaRepository<GrupoAModel, Long>{
    public abstract List<GrupoAModel> findAll(Sort sort);
}
