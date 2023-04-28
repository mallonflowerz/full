package com.apirest.full.controller.faseFinalControllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.full.model.clases.Equipo;
import com.apirest.full.model.faseFinalModels.RoundOf16;
import com.apirest.full.model.gruposModels.GrupoAModel;
import com.apirest.full.model.gruposModels.GrupoBModel;
import com.apirest.full.model.gruposModels.GrupoCModel;
import com.apirest.full.model.gruposModels.GrupoDModel;
import com.apirest.full.service.faseFinalServices.RoundOf16Services;
import com.apirest.full.service.gruposServices.GrupoAService;
import com.apirest.full.service.gruposServices.GrupoBService;
import com.apirest.full.service.gruposServices.GrupoCService;
import com.apirest.full.service.gruposServices.GrupoDService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/round16")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoundOf16Controller {

    private final GrupoBService grupoBService;

    private final RoundOf16Services roundOf16Services;

    private final GrupoAService grupoAService;

    private final GrupoCService grupoCService;

    private final GrupoDService grupoDService;

    @GetMapping()
    public List<RoundOf16> obtenerRonda() {
        return roundOf16Services.obtenerTodo();
    }

    // @GetMapping("/saveRound")
    // public List<RoundOf16> guardarRonda() {
    //     Map<String, List<? extends Equipo>> grupos = new HashMap<>();
    //     grupos.put("Grupo A", grupoAService.topTwo());
    //     grupos.put("Grupo B", grupoBService.topTwo());
    //     grupos.put("Grupo C", grupoCService.topTwo());
    //     grupos.put("Grupo D", grupoDService.topTwo());
    //     // Agrega aquí tantos grupos como quieras

    //     List<RoundOf16> listaRound = new ArrayList<>();

    //     for (String nombreGrupo : grupos.keySet()) {
    //         List<? extends Equipo> listaEquipo = grupos.get(nombreGrupo);

    //         RoundOf16 roundOf16 = new RoundOf16();
    //         roundOf16.setEquipo1(listaEquipo.get(0).getNombre());
    //         roundOf16.setEquipo2(listaEquipo.get(1).getNombre());
    //         roundOf16.setResulEquipo1(0);
    //         roundOf16.setResulEquipo2(0);
    //         // roundOf16Services.guardar(roundOf16);
    //         listaRound.add(roundOf16);
    //     }
    //     return listaRound;
    // }
}
