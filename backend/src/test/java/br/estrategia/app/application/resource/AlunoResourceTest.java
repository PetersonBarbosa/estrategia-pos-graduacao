package br.estrategia.app.application.resource;

import br.estrategia.app.domain.model.Aluno;
import br.estrategia.app.domain.repository.AlunoRepository;
import br.estrategia.app.infra.rest.URI_PATHS_CONSTANTS;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoResourceTest {

    @Autowired
    private WebTestClient web;

    @Autowired
    private AlunoRepository alunoRepository;

    @BeforeEach
    @AfterEach
    public void setUp() {
        alunoRepository.deleteAll();
    }

    @Test
    public void deve_salvar_aluno() {
        Aluno aluno = new Aluno("pyter");
        web.post().uri(URI_PATHS_CONSTANTS.ALUNOS_API)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(aluno))
                .exchange().expectStatus().isCreated()
                .expectBody(Aluno.class)
                .value(a -> assertTrue(a.getId() > 0));
    }

    @Test
    public void deve_listar_alunos() {

        Aluno aluno = new Aluno("pyter");
        web.post().uri(URI_PATHS_CONSTANTS.ALUNOS_API)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(aluno))
                .exchange();
        web.get().uri(URI_PATHS_CONSTANTS.ALUNOS_API)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Aluno.class).hasSize(1);

    }

    @Test
    public void deve_atualizar_aluno() {
        Aluno aluno = new Aluno("pyter");
        web.post().uri(URI_PATHS_CONSTANTS.ALUNOS_API)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectBody(Aluno.class)
                .value(alunoCadastrado -> {
                    assertEquals("pyter", alunoCadastrado.getNome());
                  alunoCadastrado.setNome("pyter atualizado");

                    web.put().uri(URI_PATHS_CONSTANTS.ALUNOS_API + "/" + alunoCadastrado.getId())
                            .accept(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(alunoCadastrado))
                            .exchange()
                            .expectStatus().isOk()
                            .expectBody(Aluno.class)
                            .value(alunoAtualizado -> assertEquals("pyter atualizado", alunoAtualizado.getNome()));
                });
    }

    @Test
    public void deve_remover_aluno() {
        Aluno aluno = new Aluno("pyter");
        web.post().uri(URI_PATHS_CONSTANTS.ALUNOS_API)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectBody(Aluno.class)
                .value(alunoCadastrado -> {
                    assertEquals("pyter", alunoCadastrado.getNome());
                  //  alunoCadastrado.setNome("pyter deletado");
                    web.delete().uri(URI_PATHS_CONSTANTS.ALUNOS_API + "/" + alunoCadastrado.getId())
                            .accept(MediaType.APPLICATION_JSON)
                            .exchange()
                            .expectStatus().isOk();
                });

    }


}
