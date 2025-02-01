package es.uvigo.mei.incendios.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.mei.incendios.controladores.excepciones.ResourceNotFoundException;
import es.uvigo.mei.incendios.entidades.EstadoIncendio;
import es.uvigo.mei.incendios.entidades.Incendio;
import es.uvigo.mei.incendios.servicios.IncendioService;

@RestController
@RequestMapping(path = "api/incendios", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class IncendioController {
    @Autowired
    IncendioService incendioService;

    @GetMapping()
    public ResponseEntity<List<Incendio>> buscarTodos() {
        List<Incendio> resultado = new ArrayList<>();
        resultado = incendioService.buscarTodos();
        return new ResponseEntity<List<Incendio>>(resultado, HttpStatus.OK);
    }

    @GetMapping(params = "fecha")
    public ResponseEntity<List<Incendio>> buscarPorFecha(@RequestParam Date fecha) {
        List<Incendio> resultado = new ArrayList<>();
        resultado = incendioService.buscarPorFecha(fecha);
        return new ResponseEntity<List<Incendio>>(resultado, HttpStatus.OK);
    }

    @GetMapping(params = "estado")
    public ResponseEntity<List<Incendio>> buscarPorEstado(@RequestParam EstadoIncendio estado) {
        List<Incendio> resultado = new ArrayList<>();
        resultado = incendioService.buscarPorEstado(estado);
        return new ResponseEntity<List<Incendio>>(resultado, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Incendio> buscarPorId(@PathVariable Long id) {
        Optional<Incendio> incendio = incendioService.buscarPorId(id);

        if (incendio.isEmpty()) {
            throw new ResourceNotFoundException("Incendio no encontrado");
        } else {
            return new ResponseEntity<>(incendio.get(),  HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id) {
        Optional<Incendio> incendio = incendioService.buscarPorId(id);

        if (incendio.isEmpty()) {
            throw new ResourceNotFoundException("Incendio no encontrado, no se puede eliminar");
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Incendio> modificar(@PathVariable Long id, @RequestBody Incendio incendio) {
        Optional<Incendio> incendioOptional = incendioService.buscarPorId(id);

        if (incendioOptional.isEmpty()) {
            throw new ResourceNotFoundException("Incendio no encontrado, no se puede modificar");
        } else {
            Incendio newIncendio = incendioService.modificar(incendio);
            return new ResponseEntity<Incendio>(newIncendio,  HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Incendio> crear(@RequestBody Incendio incendio) {
        Incendio newIncendio = incendioService.crear(incendio);
        URI uri = crearURIIncendio(newIncendio);
        return ResponseEntity.created(uri).body(newIncendio);
    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIIncendio(Incendio incendio) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(incendio.getIdIncendio()).toUri();
    }
}
