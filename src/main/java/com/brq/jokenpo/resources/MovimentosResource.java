package com.brq.jokenpo.resources;

import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.services.MovimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/jogador")
public class MovimentosResource {

    @Autowired
    private MovimentosService movimentosService;

    @PostMapping(value = "{id}/movimento")
    public ResponseEntity<Void> adicionarMovimento(@PathVariable("id") Long jogadorId, @RequestBody Movimento movimento){
        movimentosService.salvar(jogadorId, movimento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}/movimento")
    public ResponseEntity<?> buscarMovimento(@PathVariable("id") Long jogadorId) {
        Movimento movimento = movimentosService.buscar(jogadorId);
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(movimento);
    }

    @PutMapping(value = "{id}/movimento")
    public ResponseEntity<Void> atualizarMovimento(@PathVariable("id") Long jogadorId, @RequestBody Movimento movimento){
        movimentosService.atualizar(jogadorId, movimento);
        return ResponseEntity.noContent().build();
    }
}
