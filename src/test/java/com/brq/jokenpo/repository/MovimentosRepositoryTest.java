package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.enums.EnumMovimento;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovimentosRepositoryTest {

    private static final EnumMovimento ENUM_MOVIMENTO = EnumMovimento.PEDRA;
    private static final String MOVIMENTO = "PEDRA";
    private Long savedJogadorId = null;
    private Long savedMovimentoId = null;

    @Autowired
    MovimentosRepository repository;
    @Autowired
    JogadoresRepository jogadoresRepository;

    @Before
    public void setUp(){
        Jogador j = new Jogador();
        j.setNome("Danilo");
        jogadoresRepository.save(j);

        Movimento m = new Movimento(null, j, MOVIMENTO, ENUM_MOVIMENTO);
        repository.save(m);

        savedJogadorId = j.getId();
        savedMovimentoId = m.getId();
    }

    @After
    public void tearDown(){
        repository.deleteAll();
        jogadoresRepository.deleteAll();
    }


    @Test
    public  void testeSave(){

        Jogador j = new Jogador();
        j.setNome("Danilo");
        jogadoresRepository.save(j);

        Movimento movimento = new Movimento(1L, j, MOVIMENTO, ENUM_MOVIMENTO);
        Movimento response = repository.save(movimento);

        assertNotNull(response);
        assertEquals(response.getEnumMovimento(), ENUM_MOVIMENTO);
        assertEquals(response.getMovimento(), MOVIMENTO);
        assertEquals(response.getJogador().getId(), j.getId());
    }

    @Test
    public void testFindById(){
        Optional<Movimento> response = repository.findById(savedMovimentoId);

        assertTrue(response.isPresent());
        Assert.assertEquals(MOVIMENTO, response.get().getMovimento());
    }

    @Test
    public void testDeleteMovimento(){
        Optional<Jogador> jogador = jogadoresRepository.findById(savedJogadorId);
        Movimento movimento = new Movimento(null, jogador.get(), MOVIMENTO, ENUM_MOVIMENTO);

        repository.save(movimento);

        repository.deleteById(movimento.getId());

        Optional<Movimento> response = repository.findById(movimento.getId());

        assertFalse(response.isPresent());
    }

}
