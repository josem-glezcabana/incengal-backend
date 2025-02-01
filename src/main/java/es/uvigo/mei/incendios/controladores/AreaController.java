package es.uvigo.mei.incendios.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.mei.incendios.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.mei.incendios.entidades.Area;
import es.uvigo.mei.incendios.entidades.Riesgo;
import es.uvigo.mei.incendios.servicios.AreaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;





@RestController
@RequestMapping(path = "/api/areas", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class AreaController {
    @Autowired
    AreaService areaService;

    @GetMapping()
    public ResponseEntity<List<Area>> buscarTodas() {
        List<Area> result = new ArrayList<>();
        result = areaService.buscarTodas();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(params = "nombre")
    public ResponseEntity<List<Area>> buscarPorNombre(@RequestParam String nombre) {
        List<Area> result = new ArrayList<>();
        result = areaService.buscarPorNombre(nombre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(params = "riesgo")
    public ResponseEntity<List<Area>> buscarPorRiesgo(@RequestParam Riesgo riesgo) {
        List<Area> result = new ArrayList<>();
        result = areaService.buscarPorRiesgo(riesgo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<Area>> buscarPorId(@PathVariable Long id) {
        Optional<Area> area = areaService.buscarPorId(id);

        if (area == null) {
            throw new ResourceNotFoundException("Área no encontrada");
        } else {
            return new ResponseEntity<>(area, HttpStatus.OK);
        }
    }
    
    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id) {
        Optional<Area> area = areaService.buscarPorId(id);

        if (area.isEmpty()) {
            throw new ResourceNotFoundException("Área no encontrada. no se puede eliminar");
        } else {
            areaService.eliminar(area.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Area> modificar(@PathVariable Long id, @RequestBody Area area) {
        Optional<Area> areaOptional = areaService.buscarPorId(id);
        
        if (areaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Área no encontrada. No se puede modificar");
        } else {
            Area newArea = areaService.modificar(area);
            return new ResponseEntity<>(newArea, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Area> crear(@RequestBody Area area) {
        Area newArea = areaService.crear(area);
        URI uri = crearURIArea(newArea);

        return ResponseEntity.created(uri).body(newArea);
    }
    
    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIArea(Area area) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(area.getIdArea()).toUri();
    }
}
