package com.apirest.full.controller.faseFinalControllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/count")
    public Long cantidadData(){
        return roundOf16Services.cantidad();
    }

    @GetMapping("/reset")
    public List<RoundOf16> resetearResult(){
        List<RoundOf16> roundAll = roundOf16Services.obtenerTodo();
        //List<RoundOf16> lista = new ArrayList<>();

        for (RoundOf16 rOf16: roundAll){
            RoundOf16 roundOf16 = new RoundOf16();
            roundOf16.setId(rOf16.getId());
            roundOf16.setEquipo1(rOf16.getEquipo1());
            roundOf16.setEquipo2(rOf16.getEquipo2());
            roundOf16.setResulEquipo1(0);
            roundOf16.setResulEquipo2(0);
            roundOf16Services.guardar(roundOf16);
            //lista.add(roundOf16);
        }

        return roundOf16Services.obtenerTodo();
    }

    @GetMapping("/saveRound")
    public List<RoundOf16> guardarRonda() {
        List<RoundOf16> roundAll = roundOf16Services.obtenerTodo();
        List<GrupoAModel> listaGrupoA = grupoAService.topTwo();
        List<GrupoBModel> listaGrupoB = grupoBService.topTwo();
        List<GrupoCModel> listaGrupoC = grupoCService.topTwo();
        List<GrupoDModel> listaGrupoD = grupoDService.topTwo();
        List<String> listaName = new ArrayList<>();
        //List<RoundOf16> listaRound = new ArrayList<>();
        int cont = 0;

        if (!listaGrupoA.isEmpty() && listaGrupoA.size() == 2) {
            listaName.add(listaGrupoA.get(0).getNombre());
            listaName.add(listaGrupoA.get(1).getNombre());
        }
        if (!listaGrupoB.isEmpty() && listaGrupoB.size() == 2) {
            listaName.add(listaGrupoB.get(0).getNombre());
            listaName.add(listaGrupoB.get(1).getNombre());
        }
        if (!listaGrupoC.isEmpty() && listaGrupoC.size() == 2) {
            listaName.add(listaGrupoC.get(0).getNombre());
            listaName.add(listaGrupoC.get(1).getNombre());
        }
        if (!listaGrupoD.isEmpty() && listaGrupoD.size() == 2) {
            listaName.add(listaGrupoD.get(0).getNombre());
            listaName.add(listaGrupoD.get(1).getNombre());
        }

        Collections.shuffle(listaName);

        for (int i = 0; i < listaName.size() - 1; i += 2) {
            RoundOf16 roundOf16 = new RoundOf16();
            if (!roundAll.isEmpty()){
                roundOf16.setId(roundAll.get(cont).getId());
            }
            roundOf16.setEquipo1(listaName.get(i));
            roundOf16.setEquipo2(listaName.get(i + 1));
            roundOf16.setResulEquipo1(0);
            roundOf16.setResulEquipo2(0);
            roundOf16Services.guardar(roundOf16);
            //listaRound.add(roundOf16);
            cont +=1;
        }

        return roundOf16Services.obtenerTodo();
    }

    @PostMapping("/result/{min}/{max}")
    public List<RoundOf16> generarResultados(@PathVariable("min") int min, @PathVariable("max") int max){
        List<RoundOf16> roundAll = roundOf16Services.obtenerTodo();
        //List<RoundOf16> lista = new ArrayList<>();

        for (RoundOf16 rOf16: roundAll){
            Random random = new Random();
            int[] result = new int[2];
            RoundOf16 roundOf16 = new RoundOf16();
            result[0] = random.nextInt(max - min + 1) + min;
            result[1] = random.nextInt(max - min + 1) + min;
            roundOf16.setId(rOf16.getId());
            roundOf16.setEquipo1(rOf16.getEquipo1());
            roundOf16.setEquipo2(rOf16.getEquipo2());
            roundOf16.setResulEquipo1(result[0]);
            roundOf16.setResulEquipo2(result[1]);
            roundOf16Services.guardar(roundOf16);
            //lista.add(roundOf16);
        }

        return roundOf16Services.obtenerTodo();
    }

    @DeleteMapping("/deleteForId/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = roundOf16Services.eliminarItem(id);
        if (ok) {
            return "Se ha eliminado el id: " + id + " correctamente";
        } else {
            return "Algo salio mal";
        }
    }

    @DeleteMapping("/deleteAll")
    public String eliminarTodo(){
        boolean ok = roundOf16Services.eliminarTodo();
        if (ok) {
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
