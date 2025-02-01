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
import es.uvigo.mei.incendios.entidades.Contrato;
import es.uvigo.mei.incendios.servicios.ContratoService;

@RestController
@RequestMapping(path = "api/contratos", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class ContratoController {
    @Autowired
    ContratoService contratoService;

    @GetMapping()
    public ResponseEntity<List<Contrato>> buscarTodos() {
        List<Contrato> result = new ArrayList<>();
        result = contratoService.buscarTodas();
        return new ResponseEntity<List<Contrato>>(result,HttpStatus.OK);
    }

    @GetMapping(params = "fechaContratacion")
    public ResponseEntity<List<Contrato>> buscarPorFechaContratacion(@RequestParam Date fechaContratacion) {
        List<Contrato> resultado = new ArrayList<>();
        resultado = contratoService.buscarPorFechaContratacion(fechaContratacion);
        return new ResponseEntity<List<Contrato>>(resultado, HttpStatus.OK);
    }

    @GetMapping(params = "fechaFin")
    public ResponseEntity<List<Contrato>> buscarPorFechaFin(@RequestParam Date fechaFin) {
        List<Contrato> resultado = new ArrayList<>();
        resultado = contratoService.buscarPorFechaFin(fechaFin);
        return new ResponseEntity<List<Contrato>>(resultado, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Optional<Contrato>> buscarPorId(@PathVariable Long id) {
        Optional<Contrato> contrato = contratoService.buscarPorId(id);

        if (contrato == null) {
            throw new ResourceNotFoundException("Contrato no encontrado");
        } else {
            return new ResponseEntity<>(contrato, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable Long id) {
        Optional<Contrato> contrato = contratoService.buscarPorId(id);

        if (contrato.isEmpty()) {
            throw new ResourceNotFoundException("Contrato no encontrado, no se puede eliminar");
        } else {
            contratoService.eliminar(contrato.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contrato> modificar(@PathVariable Long id, @RequestBody Contrato contrato) {
        Optional<Contrato> contratoOptional = contratoService.buscarPorId(id);

        if (contratoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Contrato no encontrado, no se puede modificar");
        } else {
            Contrato newContrato = contratoService.modificar(contrato);
            return new ResponseEntity<Contrato>(newContrato, HttpStatus.OK);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contrato> crear(@RequestBody Contrato contrato) {
        Contrato newContrato = contratoService.crear(contrato);
        URI uri = crearURIContrato(newContrato);
        return ResponseEntity.created(uri).body(newContrato);
    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIContrato(Contrato contrato) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(contrato.getIdContrato()).toUri();
    }
}
