package com.brq.jokenpo.resources;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Partida;
import com.brq.jokenpo.services.PartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/partidas")
public class PartidasResource {

    @Autowired
    private PartidasService partidasService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Partida>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(partidasService.listar());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody Partida partida) {
        partida = partidasService.salvar(partida);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(partida.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Partida partida = partidasService.buscar(id);

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(partida);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(@RequestBody Partida partida, @PathVariable Long id) {
        partida.setId(id);
        partidasService.atualizar(partida);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        partidasService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
