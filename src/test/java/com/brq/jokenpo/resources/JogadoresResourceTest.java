package com.brq.jokenpo.resources;

import com.brq.jokenpo.AbstractTest;
import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.MovimentosService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = JogadoresResource.class)
public class JogadoresResourceTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JogadoresService jogadoresService;

    @MockBean
    private MovimentosService movimentoService;

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarOk_QuandoListarJogadores() throws Exception {
        String uri = "/jogador";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarOk_QuandoBuscarJogadores() throws Exception {
        String uri = "/jogador/{id}";
        Long id = 1L;
        Jogador jogador = new Jogador();
        jogador.setNome("Danilo");

        Mockito.when(jogadoresService.buscar(1L)).thenReturn(jogador);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarCrated_QuandoCriarJogador() throws Exception {
        String uri = "/jogador";
        Jogador jogador = new Jogador();
        jogador.setNome("Danilo");

        Mockito.when(jogadoresService.salvar(Mockito.any(Jogador.class))).thenReturn(jogador);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(jogador))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarBadRequest_QuandoCriarJogadorComParametroInvalido() throws Exception {
        Jogador jogador = new Jogador();
        jogador.setNome(null);

        String uri = "/jogador";

        Mockito.when(jogadoresService.salvar(Mockito.any(Jogador.class))).thenReturn(jogador);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(jogador))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    }


    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarNoContent_QuandoAtualizarJogador() throws Exception {
        Jogador jogador = new Jogador();
        jogador.setNome("Danilo");
        String uri = "/jogador/{id}";
        Long id = 1L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(jogador))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

    }

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarNoContent_QuandoDeletarJogador() throws Exception {
        Long id = 1L;
        String uri = "/jogador/{id}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }


}
