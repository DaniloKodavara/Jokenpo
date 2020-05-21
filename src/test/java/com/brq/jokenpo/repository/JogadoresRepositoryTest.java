package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Jogador;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JogadoresRepositoryTest {

    @Autowired
    JogadoresRepository repository;

    @Test
    public  void testeSave(){

        Jogador jogador = new Jogador();
        jogador.setNome("Danilo");

        Jogador response = repository.save(jogador);

        Assert.assertNotNull(response);

    }

    @Test
    public void testFindById(){

        Jogador jogador = new Jogador();
        jogador.setNome("Danilo");
        Long id = 1L;

        repository.save(jogador);

        Assert.assertEquals("Danilo", repository.findById(id).get().getNome());
    }

    @Test
    public void testDelete(){
        Jogador jogador = new Jogador();
        jogador.setNome("Danilo");

        repository.save(jogador);
        repository.deleteById(1L);

        Assert.assertFalse(repository.findById(1L).isPresent());
    }

}
