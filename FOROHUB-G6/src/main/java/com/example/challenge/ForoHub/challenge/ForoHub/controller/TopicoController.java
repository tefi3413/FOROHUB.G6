package com.example.challenge.ForoHub.challenge.ForoHub.controller;

import com.example.challenge.ForoHub.challenge.ForoHub.dto.*;
import com.example.challenge.ForoHub.challenge.ForoHub.infra.errores.ValidacionDeIntegridad;
import com.example.challenge.ForoHub.challenge.ForoHub.model.Topico;
import com.example.challenge.ForoHub.challenge.ForoHub.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Sort;

import java.net.URI;
@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        if (datosRegistroTopico.titulo() == null || datosRegistroTopico.mensaje() == null || datosRegistroTopico.curso() == null) {
            throw new ValidacionDeIntegridad("Titulo, Mensaje y Curso no deben estar vacíos");
        }

        Topico topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 5, sort = {"fechaCreacion", "curso"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DatosListaTopico> page = topicoRepository.findAll(pageable).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(datosActualizarTopico.id())
                .orElseThrow(() -> new ValidacionDeIntegridad("Debe proporcionar un id válido"));

        if (datosActualizarTopico.titulo() == null || datosActualizarTopico.mensaje() == null || datosActualizarTopico.curso() == null) {
            throw new ValidacionDeIntegridad("Titulo, Mensaje y Curso no deben estar vacíos");
        }

        topico.actualizarInformacion(datosActualizarTopico);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("Debe proporcionar un id válido"));

        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("Debe proporcionar un id válido"));

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }
}
