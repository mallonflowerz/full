package com.apirest.full.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apirest.full.model.EquipoGrupoRequest;
import com.apirest.full.model.EquipoModel;
import com.apirest.full.model.GrupoModel;
import com.apirest.full.service.EquipoService;
import com.apirest.full.service.GrupoService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/equipo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EquipoController {

    private final EquipoService equipoService;

    private final GrupoService grupoService;

    @GetMapping
    public ResponseEntity<List<EquipoModel>> obtenerEquipos() {
        List<EquipoModel> listaAll = equipoService.obtenerEquipo();
        return ResponseEntity.ok(listaAll);
    }

    @PostMapping
    public ResponseEntity<EquipoModel> crearEquipo(@RequestBody EquipoGrupoRequest request) {
        GrupoModel grupo = grupoService.obtenerPorId(request.getGrupoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo no encontrado"));
        
        EquipoModel equipo = new EquipoModel();
        equipo.setNombre(request.getNombre());
        equipo.setPuntaje(request.getPuntaje());
        equipo.setPuntaje(request.getPartidos());
        equipo.setGrupo(grupo);
        
        EquipoModel eModel = equipoService.guardarEquipo(equipo);
        return ResponseEntity.ok(eModel);
    }

    @GetMapping("/cantidad")
    public Long cantidadEquipos() {
        return equipoService.cantidad();
    }

    @GetMapping("/num/{id}")
    public List<EquipoModel> generarPuntajesRandom(@PathVariable Long id) {
        List<EquipoModel> lista = equipoService.buscarGrupo(id);
        List<EquipoModel> deList = new ArrayList<>();
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

        for (EquipoModel g : lista) {
            EquipoModel grupo = new EquipoModel();
            grupo.setId(g.getId());
            grupo.setNombre(g.getNombre());
            grupo.setPuntaje(g.getPuntaje() + numeros.get(repeat));
            grupo.setPartidos(g.getPartidos() + 1);
            grupo.setGrupo(g.getGrupo());
            deList.add(grupo);
            equipoService.agregarVarios(deList);
            repeat += 1;
        }

        return equipoService.buscarGrupo(id);
    }

    @GetMapping("/grupo/{id}")
    public ResponseEntity<List<EquipoModel>> obtenerGrupo(@PathVariable Long id){
        List<EquipoModel> eList = equipoService.buscarGrupo(id);
        return ResponseEntity.ok(eList);
    }

    @GetMapping("/grupo/count/{id}")
    public int cantidadGrupo(@PathVariable Long id){
        return equipoService.cantidadGrupo(id);
    }

    @PostMapping("/saveall")
    public List<EquipoModel> salvarVarios(@RequestBody List<EquipoModel> equipoModels) {
        return equipoService.agregarVarios(equipoModels);
    }

    @GetMapping("/query")
    public List<EquipoModel> obtenerPorPuntaje(@RequestParam("puntaje") int puntaje) {
        return equipoService.buscarPorPuntaje(puntaje);
    }

    @GetMapping(path = "/{id}")
    public Optional<EquipoModel> obtenerPorId(@PathParam("id") Long id) {
        return equipoService.buscarPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = equipoService.eliminar(id);
        if (ok) {
            return "El id: " + id + " fue eliminado correctamente";
        } else {
            return "Algo salio mal :(";
        }
    }

    @DeleteMapping("/delete")
    public void eliminarTodo() {
        equipoService.borrarTodo();
    }

    @DeleteMapping("/deleteAll/{id}")
    public String eliminarTodoElGrupo(@PathVariable Long id){
        List<EquipoModel> lista = equipoService.buscarGrupo(id);
        boolean ok = equipoService.eliminarPorGrupo(lista);
        if (ok) {
            return "Limpiado correctamente";
        } else {
            return "Algo salio mal";
        }
    }

    @DeleteMapping("/resetpun/{id}")
    public String resetearPuntajes(@PathVariable Long id) {
        try {
            List<EquipoModel> lista = equipoService.buscarGrupo(id);
            List<EquipoModel> deList = new ArrayList<>();

            for (EquipoModel g : lista) {
                EquipoModel grupo = new EquipoModel();
                grupo.setId(g.getId());
                grupo.setNombre(g.getNombre());
                grupo.setPuntaje(0);
                grupo.setPartidos(0);
                grupo.setGrupo(g.getGrupo());
                deList.add(grupo);
                equipoService.agregarVarios(deList);
            }
            return "Puntajes reseteados correctamente";
        } catch (Exception e) {
            return "Algo salio mal: " + e.getMessage();
        }

    }
}
