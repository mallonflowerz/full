package com.apirest.full.controller.gruposController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.gruposModels.GrupoGModel;
import com.apirest.full.service.gruposServices.GrupoGService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupog")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoGController {
    
    private final GrupoGService grupoGService;

    @GetMapping()
    public List<GrupoGModel> obtenerEquipos(){
        return grupoGService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoGModel guardarEquipo(@RequestBody GrupoGModel GrupoGModel){
        return grupoGService.guardarEquipo(GrupoGModel);
    }

    @GetMapping("/num")
    public List<GrupoGModel> generarPuntajesRandom() {
        List<GrupoGModel> lista = grupoGService.obtenerEquipos();
        List<GrupoGModel> deList = new ArrayList<>();
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

        for (GrupoGModel g : lista) {
            GrupoGModel grupo = new GrupoGModel();
            grupo.setIdG(g.getIdG());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            deList.add(grupo);
            grupoGService.agregarVarios(deList);
            repeat += 1;
        }

        return grupoGService.obtenerEquipos();
    }

    @DeleteMapping("/resetpun")
    public String resetearPuntajes() {
        try {
            List<GrupoGModel> lista = grupoGService.obtenerEquipos();
            List<GrupoGModel> deList = new ArrayList<>();

            for (GrupoGModel g : lista) {
                GrupoGModel grupo = new GrupoGModel();
                grupo.setIdG(g.getIdG());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                deList.add(grupo);
                grupoGService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

    }

    @GetMapping("/cantidad")
    public Long cantidad(){
        return grupoGService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id){
        boolean ok = grupoGService.eliminarPorId(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("name/{nombre}")
    public String eliminarPorNombre(@PathVariable("nombre") String nombre){
        List<GrupoGModel> lista = grupoGService.obtenerEquiposPorNombre(nombre);

        if (!lista.isEmpty()){
            grupoGService.eliminarPorNombre(lista);
            return "Eliminado datos por completo";
        } else {
            return "La lista esta vacia";
        }
    }

    @DeleteMapping("/deleteall")
    public String eliminarTodo(){
        boolean ok = grupoGService.eliminarTodo();
        if (ok){
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
