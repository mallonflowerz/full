package com.apirest.full.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.full.model.GrupoAModel;
import com.apirest.full.service.GrupoAService;

@RestController
@RequestMapping("/grupoa")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class GrupoAController {
    @Autowired
    private GrupoAService grupoAService;

    @GetMapping()
    public List<GrupoAModel> obtenerEquipos(){
        return grupoAService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoAModel guardarEquipo(@RequestBody GrupoAModel grupoAModel){
        return grupoAService.guardarEquipo(grupoAModel);
    }

    @GetMapping("/cantidad")
    public Long cantidad(){
        return grupoAService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id){
        boolean ok = grupoAService.eliminar(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }
}
