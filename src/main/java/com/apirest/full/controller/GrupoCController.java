package com.apirest.full.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.GrupoCModel;
import com.apirest.full.service.GrupoCService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupoc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoCController {
    
    private final GrupoCService grupoBService;

    @GetMapping()
    public List<GrupoCModel> obtenerEquipos(){
        return grupoBService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoCModel guardarEquipo(@RequestBody GrupoCModel GrupoCModel){
        return grupoBService.guardarEquipo(GrupoCModel);
    }

    @GetMapping("/cantidad")
    public Long cantidad(){
        return grupoBService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id){
        boolean ok = grupoBService.eliminarPorId(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("name/{nombre}")
    public String eliminarPorNombre(@PathVariable("nombre") String nombre){
        List<GrupoCModel> lista = grupoBService.obtenerEquiposPorNombre(nombre);

        if (!lista.isEmpty()){
            grupoBService.eliminarPorNombre(lista);
            return "Eliminado datos por completo";
        } else {
            return "La lista esta vacia";
        }
    }

    @DeleteMapping("/deleteall")
    public String eliminarTodo(){
        boolean ok = grupoBService.eliminarTodo();
        if (ok){
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
