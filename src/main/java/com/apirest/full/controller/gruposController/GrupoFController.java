package com.apirest.full.controller.gruposController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.gruposModels.GrupoFModel;
import com.apirest.full.service.gruposServices.GrupoFService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupof")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoFController {
    
    private final GrupoFService grupoFService;

    @GetMapping()
    public List<GrupoFModel> obtenerEquipos(){
        return grupoFService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoFModel guardarEquipo(@RequestBody GrupoFModel GrupoFModel){
        return grupoFService.guardarEquipo(GrupoFModel);
    }

    @GetMapping("/num")
    public List<GrupoFModel> generarPuntajesRandom() {
        List<GrupoFModel> lista = grupoFService.obtenerEquipos();
        List<GrupoFModel> deList = new ArrayList<>();
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

        for (GrupoFModel g : lista) {
            GrupoFModel grupo = new GrupoFModel();
            grupo.setIdF(g.getIdF());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            deList.add(grupo);
            grupoFService.agregarVarios(deList);
            repeat += 1;
        }

        return grupoFService.obtenerEquipos();
    }

    @DeleteMapping("/resetpun")
    public String resetearPuntajes() {
        try {
            List<GrupoFModel> lista = grupoFService.obtenerEquipos();
            List<GrupoFModel> deList = new ArrayList<>();

            for (GrupoFModel g : lista) {
                GrupoFModel grupo = new GrupoFModel();
                grupo.setIdF(g.getIdF());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                deList.add(grupo);
                grupoFService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

    }

    @GetMapping("/cantidad")
    public Long cantidad(){
        return grupoFService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id){
        boolean ok = grupoFService.eliminarPorId(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("name/{nombre}")
    public String eliminarPorNombre(@PathVariable("nombre") String nombre){
        List<GrupoFModel> lista = grupoFService.obtenerEquiposPorNombre(nombre);

        if (!lista.isEmpty()){
            grupoFService.eliminarPorNombre(lista);
            return "Eliminado datos por completo";
        } else {
            return "La lista esta vacia";
        }
    }

    @DeleteMapping("/deleteall")
    public String eliminarTodo(){
        boolean ok = grupoFService.eliminarTodo();
        if (ok){
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
