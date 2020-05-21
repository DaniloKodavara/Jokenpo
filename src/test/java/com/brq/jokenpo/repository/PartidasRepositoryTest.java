package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.domain.Partida;
import com.brq.jokenpo.enums.EnumMovimento;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PartidasRepositoryTest {

    @Autowired
    PartidasRepository repository;

    @Test
    public void testSave(){

        String resultado = "Resultado Teste";
        List<String> historico = new ArrayList<>();
        historico.add("Teste 1");
        historico.add("Teste 2");

        Partida partida = new Partida(resultado, historico);
        Partida response = repository.save(partida);

        Assert.assertNotNull(response);
    }

    @Test
    public void testeDelete(){
        Long id = 1L;
        String resultado = "Resultado Teste";
        List<String> historico = new ArrayList<>();
        historico.add("Teste 1");
        historico.add("Teste 2");

        Partida partida = new Partida(resultado, historico);
        repository.save(partida);
        repository.deleteAll();

        Assert.assertFalse(repository.findById(1L).isPresent());
    }

}
