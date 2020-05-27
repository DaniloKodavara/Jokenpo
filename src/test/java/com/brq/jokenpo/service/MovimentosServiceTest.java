package com.brq.jokenpo.service;

import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.enums.EnumMovimento;
import com.brq.jokenpo.repository.JogadoresRepository;
import com.brq.jokenpo.repository.MovimentosRepository;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.MovimentosService;
import org.junit.Assert;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovimentosServiceTest {

    private static final String MOVIMENTO = "PEDRA";
    private static final EnumMovimento ENUM_MOVIMENTO = EnumMovimento.PEDRA;

    @MockBean
    MovimentosRepository repository;

    @MockBean
    JogadoresRepository jogadoresRepository;

    @Autowired
    JogadoresService jogadoresService;

    @Autowired
    MovimentosService service;

    @Test
    public void testListar(){
        List<Movimento> list = new ArrayList<>();
        list.add(getMockMovimento());

        BDDMockito.given(repository.findAll()).willReturn(list);

        List<Movimento> response = service.listar();

        assertNotNull(response);
        assertEquals(response.get(0).getMovimento(), MOVIMENTO);
        assertEquals(response.get(0).getEnumMovimento(), ENUM_MOVIMENTO);

    }

    @Test
    public void testSalvar(){
        BDDMockito.given(repository.save(Mockito.any(Movimento.class)))
                .willReturn(getMockMovimento());

        jogadoresRepository.save(getMockJogador());

        Movimento response = service.salvar(1L, new Movimento());

        assertNotNull(response);
    }

    private Movimento getMockMovimento(){
        Jogador j = new Jogador();
        j.setId(1L);

        Movimento m = new Movimento(1L, j, MOVIMENTO, ENUM_MOVIMENTO);
        return m;

    }

    private Jogador getMockJogador(){
        Jogador j = new Jogador();
        j.setId(1L);
        j.setNome("Danilo");
        return j;
    }

}
