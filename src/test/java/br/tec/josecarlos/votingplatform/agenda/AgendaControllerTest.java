package br.tec.josecarlos.votingplatform.agenda;

import br.tec.josecarlos.votingplatform.config.errorhandler.ExceptionHandlerD;
import br.tec.josecarlos.votingplatform.models.Agenda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest({AgendaController.class, ExceptionHandlerD.class})
class AgendaControllerTest {

    private static final String URI = "/agendas";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AgendaService agendaService;

    private Agenda genericAgenda;

    @BeforeEach
    public void setUp() {
        genericAgenda = new Agenda();
        genericAgenda.setName("Generic Agenda");
    }

    @Test
    public void testFind_whenExistingId_thanReturnAgenda() throws Exception {
        when(agendaService.find(any())).thenReturn(genericAgenda);

        new AgendaMockMvcD(mockMvc, URI, genericAgenda)
                .testFind_whenExistingId_thanReturnAgenda_mockMvc();
    }

    @Test
    public void testFind_whenDoesNotExistsId_thanReturn404() throws Exception {
        when(agendaService.find(any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        new AgendaMockMvcD(mockMvc, URI, genericAgenda)
                .testFind_whenDoesNotExistsId_thanReturn404_mockMvc();
    }

    @Test
    public void testList_whenThereAreAgendas_thanReturnListOfAll() throws Exception {
        when(agendaService.list()).thenReturn(List.of(genericAgenda, genericAgenda));

        new AgendaMockMvcD(mockMvc, URI, genericAgenda)
                .testList_whenThereAreAgendas_thanReturnListOfAll_mockMvc();
    }

    @Test
    public void testList_whenThereAreNotAgendas_thanReturnEmpty() throws Exception {
        when(agendaService.list()).thenReturn(List.of());

        new AgendaMockMvcD(mockMvc, URI, genericAgenda)
                .testList_whenThereAreNotAgendas_thanReturnEmpty_mockMvc();
    }

    @Test
    public void testCreate_whenValidAgenda_thanCreates() throws Exception {
        when(agendaService.create(any())).thenReturn(genericAgenda);

        new AgendaMockMvcD(mockMvc, URI, genericAgenda)
                .testCreate_whenValidAgenda_thanCreates_mockMvc();
    }

    @Test
    public void testCreate_whenInvalidAgendaName_thanBadRequest() throws Exception {
        genericAgenda.setName("");

        new AgendaMockMvcD(mockMvc, URI, genericAgenda)
                .testCreate_whenInvalidAgendaName_thanBadRequest_mockMvc();
    }
}
