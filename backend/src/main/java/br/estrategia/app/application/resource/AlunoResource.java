package br.estrategia.app.application.resource;

import br.estrategia.app.domain.model.Aluno;
import br.estrategia.app.domain.repository.AlunoRepository;
import br.estrategia.app.infra.rest.URI_PATHS_CONSTANTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = URI_PATHS_CONSTANTS.ALUNOS_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class AlunoResource {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<Aluno> listar() {
        return new ResponseEntity(alunoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable("id") long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()){
            return new ResponseEntity(alunoOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
        return new ResponseEntity(alunoRepository.save(aluno), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable("id") long id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoRecuperadoDaBase = alunoRepository.findById(id);
        if (alunoRecuperadoDaBase.isPresent()) {
            aluno.setId(id);
            return new ResponseEntity(alunoRepository.save(aluno), HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<Aluno> remover(@PathVariable("id") long id){
        alunoRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
