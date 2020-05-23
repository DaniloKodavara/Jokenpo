package com.brq.jokenpo.repository;

import com.brq.jokenpo.domain.Jogador;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JogadoresRepositoryTest {

    private static final String NOME = "Danilo";
    private Long savedJogadorId;

    @Autowired
    JogadoresRepository repository;

    @Before
    public void setUp() {
        Jogador jogador = new Jogador();
        jogador.setNome(NOME);
        repository.save(jogador);

        savedJogadorId = jogador.getId();

    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testeSave() {

        Jogador jogador = new Jogador();
        jogador.setNome(NOME);

        Jogador response = repository.save(jogador);

        assertNotNull(response);
        assertEquals(response.getNome(), NOME);

    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveInvalidJogador() {
        Jogador jogador = new Jogador(null, null, null);
        repository.save(jogador);
    }

    @Test
    public void testFindById() {

        Optional<Jogador> optionalJogador = repository.findById(savedJogadorId);

        Assert.assertEquals(NOME, optionalJogador.get().getNome());
    }

    @Test
    public void testUpdate() {
        Optional<Jogador> jogador = repository.findById(savedJogadorId);

        String nome = "Thomas";

        Jogador changed = jogador.get();
        changed.setNome(nome);
        repository.save(changed);

        Optional<Jogador> newJogador = repository.findById(savedJogadorId);

        assertEquals(nome, newJogador.get().getNome());
    }

    @Test
    public void deleteJogador() {
        Optional<Jogador> optional = repository.findById(savedJogadorId);
        Jogador jogador = optional.get();
        repository.deleteById(jogador.getId());

        Optional<Jogador> response = repository.findById(jogador.getId());
        Assert.assertFalse(response.isPresent());
    }

}
