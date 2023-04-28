package com.apirest.full.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.EquipoModel;
import com.apirest.full.model.GrupoModel;
import com.apirest.full.service.EquipoService;
import com.apirest.full.service.GrupoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoController {

    private final GrupoService grupoService;

    private final EquipoService equipoService;

    @GetMapping()
    public List<GrupoModel> obtenerTodo() {
        return grupoService.obtenerAll();
    }

    @PostMapping
    public GrupoModel guardar(@RequestBody GrupoModel grupoModel) {
        return grupoService.guardar(grupoModel);
    }

    // @PostMapping("/agg/{id1}/{id2}")
    // public GrupoModel guardarEquipo(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
    //     // GrupoModel grupoModel = new GrupoModel();
    //     // EquipoModel equipoModel = new EquipoModel();
    //     // Optional<GrupoModel> grupoModelOp = grupoService.obtenerPorId(id2);
    //     // Optional<EquipoModel> equipoModelOp = equipoService.buscarPorId(id1);
    //     // List<EquipoModel> lista = new ArrayList<>();
    //     // equipoModel.setId(equipoModelOp.get().getId());
    //     // equipoModel.setNombre(equipoModelOp.get().getNombre());
    //     // equipoModel.setPuntaje(equipoModelOp.get().getPuntaje());
    //     // grupoModel.setId(grupoModelOp.get().getId());
    //     // grupoModel.setNombre(grupoModelOp.get().getNombre());
    //     // lista.add(equipoModel);
    //     // grupoModel.setEquipos(lista);
    //     // equipoModel.setGrupo(grupoModel);

    //     return grupoModel;

    // }

    @PostMapping("/{nombre}")
    public GrupoModel buscarPorNombre(@PathVariable("nombre") String nombre) {
        return grupoService.buscarPorNombre(nombre);
    }
}
