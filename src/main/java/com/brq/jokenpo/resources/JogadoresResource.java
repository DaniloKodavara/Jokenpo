package com.brq.jokenpo.resources;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.services.JogadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/jogador")
public class JogadoresResource {

    @Autowired
    private JogadoresService jogadoresService;

    @GetMapping
    public ResponseEntity<List<Jogador>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(jogadoresService.listar());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody Jogador jogador) {
        jogador = jogadoresService.salvar(jogador);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Jogador jogador = jogadoresService.buscar(id);

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(jogador);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody Jogador jogador, @PathVariable Long id) {
        jogador.setId(id);
        jogadoresService.atualizar(jogador);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        jogadoresService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
