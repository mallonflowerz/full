package com.apirest.full.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping
    public ResponseEntity<List<GrupoModel>> obtenerTodo() {
        List<GrupoModel> all = grupoService.obtenerAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<GrupoModel> guardar(@RequestBody GrupoModel grupoModel) {
        GrupoModel grupoM = grupoService.guardar(grupoModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(grupoM.getId())
                .toUri();

        return ResponseEntity.created(location).body(grupoM);
    }

    @PostMapping("/{nombre}")
    public ResponseEntity<GrupoModel> buscarPorNombre(@PathVariable("nombre") String nombre) {
        GrupoModel gModel = grupoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(gModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        grupoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> eliminarTodo(){
        grupoService.eliminarTodo();
        return ResponseEntity.noContent().build();
    }

}
