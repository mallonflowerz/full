package com.apirest.full.controller.gruposController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.gruposModels.GrupoDModel;
import com.apirest.full.service.gruposServices.GrupoDService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/grupod")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GrupoDController {
    
    private final GrupoDService grupoDService;

    @GetMapping()
    public List<GrupoDModel> obtenerEquipos(){
        return grupoDService.obtenerEquipos();
    }

    @PostMapping()
    public GrupoDModel guardarEquipo(@RequestBody GrupoDModel GrupoDService){
        return grupoDService.guardarEquipo(GrupoDService);
    }

    @GetMapping("/num")
    public List<GrupoDModel> generarPuntajesRandom() {
        List<GrupoDModel> lista = grupoDService.obtenerEquipos();
        List<GrupoDModel> deList = new ArrayList<>();
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

        for (GrupoDModel g : lista) {
            GrupoDModel grupo = new GrupoDModel();
            grupo.setIdD(g.getIdD());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            deList.add(grupo);
            grupoDService.agregarVarios(deList);
            repeat += 1;
        }

        return grupoDService.obtenerEquipos();
    }

    @DeleteMapping("/resetpun")
    public String resetearPuntajes() {
        try {
            List<GrupoDModel> lista = grupoDService.obtenerEquipos();
            List<GrupoDModel> deList = new ArrayList<>();

            for (GrupoDModel g : lista) {
                GrupoDModel grupo = new GrupoDModel();
                grupo.setIdD(g.getIdD());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                deList.add(grupo);
                grupoDService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

    }

    @GetMapping("/cantidad")
    public Long cantidad(){
        return grupoDService.cantidad();
    }

    @DeleteMapping("/{id}")
    public String deleteForId(@PathVariable("id") Long id){
        boolean ok = grupoDService.eliminarPorId(id);
        if (ok){
            return "El id: "+id+" fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("name/{nombre}")
    public String eliminarPorNombre(@PathVariable("nombre") String nombre){
        List<GrupoDModel> lista = grupoDService.obtenerEquiposPorNombre(nombre);

        if (!lista.isEmpty()){
            grupoDService.eliminarPorNombre(lista);
            return "Eliminado datos por completo";
        } else {
            return "La lista esta vacia";
        }
    }

    @DeleteMapping("/deleteall")
    public String eliminarTodo(){
        boolean ok = grupoDService.eliminarTodo();
        if (ok){
            return "Eliminado todo correctamente";
        } else {
            return "Algo salio mal";
        }
    }
}
