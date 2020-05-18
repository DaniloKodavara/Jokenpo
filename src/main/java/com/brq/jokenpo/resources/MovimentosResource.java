package com.brq.jokenpo.resources;

import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.services.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/jogadores")
public class MovimentosResource {

    @Autowired
    private MovimentoService movimentoService;

    @RequestMapping(value = "{id}/movimento", method = RequestMethod.POST)
    public ResponseEntity<Void> adicionarMovimento(@PathVariable("id") Long jogadorId, @RequestBody Movimento movimento){
        movimentoService.salvar(jogadorId, movimento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}/movimento", method = RequestMethod.GET)
    public ResponseEntity<?> buscarMovimento(@PathVariable("id") Long jogadorId) {
        Movimento movimento = movimentoService.buscar(jogadorId);
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(movimento);
    }

    @RequestMapping(value = "{id}/movimento", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizarMovimento(@PathVariable("id") Long jogadorId, @RequestBody Movimento movimento){
        movimentoService.atualizar(jogadorId, movimento);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "{id}/movimento", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarMovimento(@PathVariable("id") Long jogadorId){
        movimentoService.deletar(jogadorId);
        return ResponseEntity.noContent().build();
    }
}
