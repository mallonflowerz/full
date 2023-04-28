package com.apirest.full.controller.gruposController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.gruposModels.GrupoEModel;
import com.apirest.full.service.gruposServices.GrupoEService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupoe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoEController {
    
    private final GrupoEService grupoEService;

    @GetMapping()
    public List<GrupoEModel> obtenerEquipos(){
        return grupoEService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoEModel guardarEquipo(@RequestBody GrupoEModel grupoEModel){
        return grupoEService.guardarEquipo(grupoEModel);
    }

    @GetMapping("/num")
    public List<GrupoEModel> generarPuntajesRandom() {
        List<GrupoEModel> lista = grupoEService.obtenerEquipos();
        List<GrupoEModel> deList = new ArrayList<>();
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

        for (GrupoEModel g : lista) {
            GrupoEModel grupo = new GrupoEModel();
            grupo.setIdE(g.getIdE());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            deList.add(grupo);
            grupoEService.agregarVarios(deList);
            repeat += 1;
        }

        return grupoEService.obtenerEquipos();
    }

    @DeleteMapping("/resetpun")
    public String resetearPuntajes() {
        try {
            List<GrupoEModel> lista = grupoEService.obtenerEquipos();
            List<GrupoEModel> deList = new ArrayList<>();

            for (GrupoEModel g : lista) {
                GrupoEModel grupo = new GrupoEModel();
                grupo.setIdE(g.getIdE());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                deList.add(grupo);
                grupoEService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

    }

    @GetMapping("/cantidad")
    public Long cantidad(){
        return grupoEService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id){
        boolean ok = grupoEService.eliminarPorId(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("name/{nombre}")
    public String eliminarPorNombre(@PathVariable("nombre") String nombre){
        List<GrupoEModel> lista = grupoEService.obtenerEquiposPorNombre(nombre);

        if (!lista.isEmpty()){
            grupoEService.eliminarPorNombre(lista);
            return "Eliminado datos por completo";
        } else {
            return "La lista esta vacia";
        }
    }

    @DeleteMapping("/deleteall")
    public String eliminarTodo(){
        boolean ok = grupoEService.eliminarTodo();
        if (ok){
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
