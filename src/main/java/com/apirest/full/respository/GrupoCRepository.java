package com.apirest.full.respository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.full.model.GrupoCModel;

public interface GrupoCRepository extends JpaRepository<GrupoCModel, Long>{
    public abstract List<GrupoCModel> findAll(Sort sort);
    List<GrupoCModel> findAllByNombre(String nombre);
}
