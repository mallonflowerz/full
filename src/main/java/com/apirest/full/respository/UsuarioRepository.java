package com.apirest.full.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.apirest.full.model.UsuarioModel;

//@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    public abstract List<UsuarioModel> findByEmail(String email);
    
}
