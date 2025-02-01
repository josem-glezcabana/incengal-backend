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
import es.uvigo.mei.incendios.entidades.Brigadista;
import es.uvigo.mei.incendios.servicios.BrigadistaService;


@RestController
@RequestMapping(path = "api/brigadistas", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class BrigadistaController {
    @Autowired
    BrigadistaService brigadistaService;

    @GetMapping()
    public ResponseEntity<List<Brigadista>> buscarTodos() {
        List <Brigadista> result = new ArrayList<>();
        result = brigadistaService.buscarTodas();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<List<Brigadista>> buscarPorNombre (@RequestParam String nombre) {
        List<Brigadista> result = new ArrayList<>();
        result = brigadistaService.buscarPorNombre(nombre);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(params = "dni")
    public ResponseEntity<Brigadista> buscarPorDNI (@RequestParam String dni) {
        Optional<Brigadista> brigadista = brigadistaService.buscarPorDNI(dni);

        if (brigadista.isEmpty()) {
            throw new ResourceNotFoundException("Brigadista no encontrado");
        } else {
            return new ResponseEntity<>(brigadista.get(), HttpStatus.OK);
        }
    }

    @GetMapping(params = "apellido")
    public ResponseEntity<List<Brigadista>> buscarPorApellido (@RequestParam String apellido) {
        List<Brigadista> result = new ArrayList<>();
        result = brigadistaService.buscarPorApellido(apellido);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(params = "telefono")
    public ResponseEntity<List<Brigadista>> buscarPorTelefono (@RequestParam String telefono) {
        List<Brigadista> result = new ArrayList<>();
        result = brigadistaService.buscarPorTelefono(telefono);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<Brigadista>> buscarPorId (@PathVariable Long id) {
        Optional<Brigadista> brigadista = brigadistaService.buscarPorId(id);

        if (brigadista == null) {
            throw new ResourceNotFoundException("Brigadista no encontrado");
        } else {
            return new ResponseEntity<>(brigadista, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> eliminar (@PathVariable Long id) {
        Optional<Brigadista> brigadista = brigadistaService.buscarPorId(id);

        if (brigadista.isEmpty()) {
            throw new ResourceNotFoundException("Brigadista no encontrado, no se puede eliminar");
        } else {
            brigadistaService.eliminar(brigadista.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brigadista> modificar(@PathVariable Long id, @RequestBody Brigadista brigadista) {
        Optional<Brigadista> brigadistaOptional = brigadistaService.buscarPorId(id);

        if (brigadistaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Brigadista no encontrado, no se puede modificar");
        } else {
            Brigadista newBrigadista = brigadistaService.modificar(brigadista);
            return new ResponseEntity<Brigadista>(newBrigadista, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brigadista> crear(@RequestBody Brigadista brigadista) {
        Brigadista newBrigadista = brigadistaService.crear(brigadista);
        URI uri = crearURIBrigadista(newBrigadista);
        return ResponseEntity.created(uri).body(newBrigadista);
    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIBrigadista(Brigadista brigadista) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(brigadista.getIdBrigadista()).toUri();
    }
}
