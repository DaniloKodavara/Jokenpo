package com.brq.jokenpo.service;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.repository.JogadoresRepository;
import com.brq.jokenpo.services.JogadoresService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JogadoresServiceTest {

    private static final String NOME = "Danilo";
    private Long savedJogadorId = null;

    @MockBean
    JogadoresRepository repository;

    @Autowired
    JogadoresService service;

    @Before
    public void setUp() {
        BDDMockito.given(repository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(new Jogador()));

        BDDMockito.given(repository.findAll()).willReturn(new ArrayList<Jogador>());

        BDDMockito.given(repository.save(Mockito.any(Jogador.class))).willReturn(new Jogador());

    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testListar() {
        List<Jogador> list = service.listar();

        Assert.assertNotNull(list);
    }

    @Test
    public void testSalvar() {
        Jogador jogador = service.salvar(new Jogador());

        Assert.assertNotNull(jogador);
    }

    @Test
    public void testBuscar() {
        Jogador jogador = service.buscar(1L);

        Assert.assertNotNull(jogador);
    }

}
