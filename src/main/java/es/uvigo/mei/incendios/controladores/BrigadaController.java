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
import es.uvigo.mei.incendios.entidades.Brigada;
import es.uvigo.mei.incendios.servicios.BrigadaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping(path = "api/brigadas", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class BrigadaController {
    @Autowired
    BrigadaService brigadaService;

    @GetMapping()
    public ResponseEntity<List<Brigada>> buscarTodas() {
        List<Brigada> result = new ArrayList<>();
        result = brigadaService.buscarTodas();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(params = "nombre")
    public ResponseEntity<List<Brigada>> buscarPorNombre(@RequestParam String nombre) {
        List<Brigada> result = new ArrayList<>();
        result = brigadaService.buscarPorNombre(nombre);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(params = "base")
    public ResponseEntity<List<Brigada>> buscarPorBase(@RequestParam String base) {
        List<Brigada> result = new ArrayList<>();
        result = brigadaService.buscarPorBase(base);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<Brigada>> buscarPorId(@PathVariable Long id) {
        Optional<Brigada> brigada = brigadaService.buscarPorId(id);

        if (brigada == null) {
            throw new ResourceNotFoundException("Brigada no encontrada");
        } else {
            return new ResponseEntity<>(brigada, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> eliminar (@PathVariable Long id) {
        Optional<Brigada> brigada = brigadaService.buscarPorId(id);

        if (brigada.isEmpty()) {
            throw new ResourceNotFoundException("Brigada no encontrada. No se puede eliminar");
        } else {
            brigadaService.eliminar(brigada.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brigada> modificar (@PathVariable Long id, @RequestBody Brigada brigada) {
        Optional<Brigada> brigadaOptional = brigadaService.buscarPorId(id);

        if (brigadaOptional.isEmpty()) {
            throw new ResourceNotFoundException("Brigada no encontrada, no se puede modificar");
        } else {
            Brigada newBrigada = brigadaService.modificar(brigada);
            return new ResponseEntity<>(newBrigada, HttpStatus.OK);
        }        
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brigada> crear (@RequestBody Brigada brigada) {
        Brigada newBrigada = brigadaService.crear(brigada);
        URI uri = crearURIBrigada(newBrigada);
        
        return ResponseEntity.created(uri).body(newBrigada);
    }
    
    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIBrigada(Brigada brigada) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(brigada.getIdBrigada()).toUri();
    }
}
