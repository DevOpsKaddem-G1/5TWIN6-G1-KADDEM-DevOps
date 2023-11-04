package com.esprit.kaddem;

import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.repositories.UniversiteRepository;
import com.esprit.kaddem.services.IUniversiteService;
import com.esprit.kaddem.services.UniversiteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UniversiteApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private UniversiteServiceImpl iUniversiteService;

    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Sql(statements = "INSERT INTO universite(id, nom_univ) VALUES (22, 'john')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM universite WHERE id='22'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testOrdersList() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Universite>> response = restTemplate.exchange(
                createURLWithPort(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Universite>>(){});
        List<Universite> universiteListList = response.getBody();
        assert universiteListList != null;
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(universiteListList.size(), iUniversiteService.retrieveAllUniversites().size());
        assertEquals(universiteListList.size(), universiteRepository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO universite(id, nom_univ) VALUES (20, 'sam')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM universite WHERE id='20'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testOrderById() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Universite> response = restTemplate.exchange(
                (createURLWithPort() + "/20"), HttpMethod.GET, entity, Universite.class);
        Universite universiteRes = response.getBody();
        String expected = "{\"id\":20,\"nom_univ\":\"sam\"}";
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(expected, objectMapper.writeValueAsString(universiteRes));
        assert universiteRes != null;
        assertEquals(universiteRes, iUniversiteService.retrieveUniversite(20));
        assertEquals(universiteRes.getNomUniv(), iUniversiteService.retrieveUniversite(20).getNomUniv());
        assertEquals(universiteRes, universiteRepository.findById(20).orElse(null));
    }

    @Test
    @Sql(statements = "DELETE FROM universite WHERE id='3'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCreateOrder() throws JsonProcessingException {
        Universite universite = new Universite(3, "peter");
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(universite), headers);
        ResponseEntity<Universite> response = restTemplate.exchange(
                createURLWithPort(), HttpMethod.POST, entity, Universite.class);
        assertEquals(response.getStatusCodeValue(), 201);
        Universite universiteRes = Objects.requireNonNull(response.getBody());
        assertEquals(universiteRes.getNomUniv(), "peter");
        assertEquals(universiteRes.getNomUniv(), universiteRepository.save(universite).getNomUniv());
    }

//    @Test
//    @Sql(statements = "INSERT INTO orders(id, buyer, price, qty) VALUES (6, 'alex', 75.0, 3)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Sql(statements = "DELETE FROM orders WHERE id='6'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//    public void testDeleteOrder() {
//        ResponseEntity<String> response = restTemplate.exchange(
//                (createURLWithPort() + "/6"), HttpMethod.DELETE, null, String.class);
//        String orderRes = response.getBody();
//        assertEquals(response.getStatusCodeValue(), 200);
//        assertNotNull(orderRes);
//        assertEquals(orderRes, "Order deleted - Order ID:6");
//    }

    private String createURLWithPort() {
        return "http://localhost:" + port + "/universite/";
    }

}
