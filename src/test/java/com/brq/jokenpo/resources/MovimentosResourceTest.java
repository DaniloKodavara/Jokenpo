package com.brq.jokenpo.resources;

import com.brq.jokenpo.AbstractTest;
import com.brq.jokenpo.domain.Jogador;
import com.brq.jokenpo.domain.Movimento;
import com.brq.jokenpo.enums.EnumMovimento;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.MovimentoService;
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
@WebMvcTest(value = MovimentosResource.class)
public class MovimentosResourceTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JogadoresService jogadoresService;

    @MockBean
    private MovimentoService movimentoService;

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarCrated_QuandoAdicionarMovimento() throws Exception {
        String uri = "/jogadores/{id}/movimento";
        Long id = 1L;
        Movimento movimento = new Movimento(EnumMovimento.LAGARTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(movimento))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarOk_QuandoBuscarMovimento() throws Exception {
        String uri = "/jogadores/{id}/movimento";
        Long id = 1L;
        Movimento movimento = new Movimento(EnumMovimento.LAGARTO);

        Mockito.when(movimentoService.buscar(1L)).thenReturn(movimento);

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
    public void deveRetornarNoContent_QuandoAtualizarMovimento() throws Exception {
        Movimento movimento = new Movimento(EnumMovimento.LAGARTO);
        String uri = "/jogadores/{id}/movimento";
        Long id = 1L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapToJson(movimento))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());

    }

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarNoContent_QuandoDeletarMovimento() throws Exception {
        Long id = 1L;
        String uri = "/jogadores/{id}/movimento";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri, id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

}
