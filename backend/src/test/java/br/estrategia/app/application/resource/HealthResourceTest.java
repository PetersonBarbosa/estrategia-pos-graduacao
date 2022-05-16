package br.estrategia.app.application.resource;


import br.estrategia.app.infra.rest.URI_PATHS_CONSTANTS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthResourceTest {

    @Autowired
    private WebTestClient web;

    @Test
    public void deve_verificar_se_servico_esta_ready(){
        web.get().uri(URI_PATHS_CONSTANTS.READINESS).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(resposta -> assertTrue(resposta.contains("ok")));
    }

    @Test
    public void deve_verificar_se_o_servico_esta_live(){
        web.get().uri(URI_PATHS_CONSTANTS.LIVENESS).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(resposta -> assertTrue(resposta.contains("ok")));

    }
}
