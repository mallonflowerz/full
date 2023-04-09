package com.apirest.full.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.full.model.UsuarioModel;
import com.apirest.full.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<UsuarioModel> obtenerUsers(){
        return usuarioService.obtenerUsers();
    }

    @PostMapping()
    public UsuarioModel guardar(@RequestBody UsuarioModel usuario){
        return usuarioService.guardar(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsersPorId(@PathVariable("id") Long id){
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/query")
    public List<UsuarioModel> obtenerPorEmail(@RequestParam("email") String email){
        return usuarioService.buscarPorEmail(email);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = usuarioService.eliminar(id);
        if (ok){
            return "Eliminado correctamente el id: "+id;
        } else {
            return "Algo salio mal :(";
        }
    }
}
