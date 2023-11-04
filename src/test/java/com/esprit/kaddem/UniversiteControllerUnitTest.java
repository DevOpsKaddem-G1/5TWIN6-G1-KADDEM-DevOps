package com.esprit.kaddem;

import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.restcontrollers.UniversiteRestController;
import com.esprit.kaddem.services.IUniversiteService;
import com.esprit.kaddem.services.UniversiteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UniversiteRestController.class)
public class UniversiteControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UniversiteServiceImpl iUniversiteService;

    private Universite universite;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        universite = new Universite(10, "andrew");
    }

    @Test
    public void testGetUniversitesList() throws Exception {
        when(iUniversiteService.retrieveAllUniversites()).thenReturn(Collections.singletonList(universite));
        mockMvc.perform(get("/universite/retrieve-all-universites"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUniversiteById() throws Exception {
        when(iUniversiteService.retrieveUniversite(10)).thenReturn(universite);
        mockMvc.perform(get("/universite/retrieve-universite/10"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nomUniv", is("andrew")))
            .andExpect(jsonPath("$.idUniversite", is(10)))
            .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testCreateUniversite() throws Exception {
        when(iUniversiteService.addUniversite(universite)).thenReturn(universite);
        mockMvc.perform(
            post("/universite/add-universite")
                .content(objectMapper.writeValueAsString(universite))
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nomUniv", is("andrew")))
            .andExpect(jsonPath("$.idUniversite", is(10)))
            .andExpect(jsonPath("$").isNotEmpty());
    }

//    @Test
//    public void testDeleteOrder() throws Exception {
//        when(orderService.deleteOrderById(order.getId())).thenReturn(true);
//        mockMvc.perform(delete("/api/orders/" + order.getId()))
//            .andDo(print())
//            .andExpect(status().isOk());
//    }

}
