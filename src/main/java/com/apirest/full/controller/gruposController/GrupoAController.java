package com.apirest.full.controller.gruposController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.full.model.gruposModels.GrupoAModel;
import com.apirest.full.service.gruposServices.GrupoAService;

@RestController
@RequestMapping("/grupoa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoAController {

    @Autowired
    private GrupoAService grupoAService;

    @GetMapping()
    public List<GrupoAModel> obtenerEquipos() {
        return grupoAService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoAModel guardarEquipo(@RequestBody GrupoAModel grupoAModel) {
        return grupoAService.guardarEquipo(grupoAModel);
    }

    @GetMapping("/num")
    public List<GrupoAModel> generarPuntajesRandom() {
        List<GrupoAModel> lista = grupoAService.obtenerEquipos();
        List<GrupoAModel> deList = new ArrayList<>();
        List<Integer> numeros = new ArrayList<>();
        Random random = new Random();
        int ultimo = -1;
        int repeat = 0;

        for (int i = 0; i < 4; i++) {
            int numero;
            do {
                numero = random.nextInt(4);
            } while (numero == 2 || numero == ultimo);
            numeros.add(numero);
            ultimo = numero;
            if (numero == 1 && i < 3) {
                numeros.add(1);
                ultimo = 1;
                i++;
            } else if (numero == 3 && i < 3) {
                numeros.add(0);
                ultimo = 0;
                i++;
            }
        }

        for (GrupoAModel g : lista) {
            GrupoAModel grupo = new GrupoAModel();
            grupo.setIdA(g.getIdA());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            deList.add(grupo);
            grupoAService.agregarVarios(deList);
            repeat += 1;
        }

        return grupoAService.obtenerEquipos();
    }

    @GetMapping("/cantidad")
    public Long cantidad() {
        return grupoAService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id) {
        boolean ok = grupoAService.eliminarPorId(id);
        if (ok) {
            return "El id: " + id + " fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("name/{nombre}")
    public String eliminarPorNombre(@PathVariable("nombre") String nombre) {
        List<GrupoAModel> lista = grupoAService.obtenerEquiposPorNombre(nombre);

        if (!lista.isEmpty()) {
            grupoAService.eliminarPorNombre(lista);
            return "Eliminado datos por completo";
        } else {
            return "La lista esta vacia";
        }
    }

    @DeleteMapping("/resetpun")
    public String resetearPuntajes() {
        try {
            List<GrupoAModel> lista = grupoAService.obtenerEquipos();
            List<GrupoAModel> deList = new ArrayList<>();

            for (GrupoAModel g : lista) {
                GrupoAModel grupo = new GrupoAModel();
                grupo.setIdA(g.getIdA());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                deList.add(grupo);
                grupoAService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

    }

    @DeleteMapping("/deleteall")
    public String eliminarTodo() {
        boolean ok = grupoAService.eliminarTodo();
        if (ok) {
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
