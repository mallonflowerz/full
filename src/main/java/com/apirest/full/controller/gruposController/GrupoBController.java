package com.apirest.full.controller.gruposController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import com.apirest.full.model.gruposModels.GrupoBModel;
import com.apirest.full.service.gruposServices.GrupoBService;

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

    @GetMapping("/num")
    public List<GrupoBModel> generarPuntajesRandom() {
        List<GrupoBModel> lista = grupoBService.obtenerEquipos();
        List<GrupoBModel> deList = new ArrayList<>();
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

        for (GrupoBModel g : lista) {
            GrupoBModel grupo = new GrupoBModel();
            grupo.setIdB(g.getIdB());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            deList.add(grupo);
            grupoBService.agregarVarios(deList);
            repeat += 1;
        }

        return grupoBService.obtenerEquipos();
    }

    @DeleteMapping("/resetpun")
    public String resetearPuntajes() {
        try {
            List<GrupoBModel> lista = grupoBService.obtenerEquipos();
            List<GrupoBModel> deList = new ArrayList<>();

            for (GrupoBModel g : lista) {
                GrupoBModel grupo = new GrupoBModel();
                grupo.setIdB(g.getIdB());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                deList.add(grupo);
                grupoBService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

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
