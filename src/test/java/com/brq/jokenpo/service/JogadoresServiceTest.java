package com.brq.jokenpo.service;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.repository.JogadoresRepository;
import com.brq.jokenpo.services.JogadoresService;
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

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JogadoresServiceTest {

    @MockBean
    JogadoresRepository repository;

    @Autowired
    JogadoresService service;

    @Before
    public void setUp() {
        BDDMockito.given(repository.findById(Mockito.anyLong()))
                .willReturn(Optional.of(new Jogador()));
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Jogador jogador = service.buscar(id);

        Assert.assertNotNull(jogador);
    }
}
