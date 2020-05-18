package com.brq.jokenpo.resources;

import com.brq.jokenpo.domain.Partida;
import com.brq.jokenpo.services.PartidasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/partida")
public class PartidasResource {

    @Autowired
    private PartidasService partidasService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Partida> play(){
        Partida partida = partidasService.play();

        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(partida);
    }
}
