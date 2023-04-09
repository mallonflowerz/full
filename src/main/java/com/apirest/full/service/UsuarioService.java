package com.apirest.full.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.full.model.UsuarioModel;
import com.apirest.full.respository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> obtenerUsers(){
        List<UsuarioModel> list = usuarioRepository.findAll();
        return list;
    } 

    public UsuarioModel guardar(UsuarioModel usuarioModel){
        return usuarioRepository.save(usuarioModel);
    }

    public List<UsuarioModel> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Optional<UsuarioModel> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public boolean eliminar(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
