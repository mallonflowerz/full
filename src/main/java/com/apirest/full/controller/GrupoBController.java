package com.apirest.full.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.GrupoBModel;
import com.apirest.full.service.GrupoBService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupob")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoBController {
    
    private final GrupoBService grupoBService;

    @GetMapping()
    public List<GrupoBModel> obtenerEquipos(){
        return grupoBService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoBModel guardarEquipo(@RequestBody GrupoBModel GrupoBModel){
        return grupoBService.guardarEquipo(GrupoBModel);
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
        List<GrupoBModel> lista = grupoBService.obtenerEquiposPorNombre(nombre);

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
