package com.apirest.full.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.full.model.EquipoModel;
import com.apirest.full.service.EquipoService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/equipo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping()
    public List<EquipoModel> obtenerEquipos(){
        return equipoService.obtenerEquipo();
    }

    @PostMapping()
    public EquipoModel guardar(@RequestBody EquipoModel equipoModel){
        return equipoService.guardarEquipo(equipoModel);
    }

    @GetMapping("/cantidad")
    public Long cantidadEquipos(){
        return equipoService.cantidad();
    }

    @PostMapping("/saveall")
    public List<EquipoModel> salvarVarios(@RequestBody List<EquipoModel> equipoModels){
        return equipoService.agregarVarios(equipoModels);
    }

    @GetMapping("/query")
    public List<EquipoModel> obtenerPorPuntaje(@RequestParam("puntaje") int puntaje){
        return equipoService.buscarPorPuntaje(puntaje);
    }

    @GetMapping(path = "/{id}")
    public Optional<EquipoModel> obtenerPorId(@PathParam("id") Long id){
        return equipoService.buscarPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = equipoService.eliminar(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("/delete")
    public void eliminarTodo(){
        equipoService.borrarTodo();
    }
}
