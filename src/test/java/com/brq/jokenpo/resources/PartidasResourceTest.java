package com.brq.jokenpo.resources;

import com.brq.jokenpo.AbstractTest;
import com.brq.jokenpo.services.JogadoresService;
import com.brq.jokenpo.services.MovimentosService;
import com.brq.jokenpo.services.PartidasService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@WebMvcTest(value = PartidasResource.class)
public class PartidasResourceTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JogadoresService jogadoresService;

    @MockBean
    private MovimentosService movimentoService;

    @MockBean
    private PartidasService partidasService;

    @WithMockUser(value = "spring")
    @Test
    public void deveRetornarOk_QuandoIniciarPartida() throws Exception {
        String uri = "/partida";

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
    public void deveRetornarNoContent_QuandoLimparPartidas() throws Exception {
        String uri = "/partida";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(uri)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
