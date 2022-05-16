package br.estrategia.app.application.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

    @GetMapping(value = "/olaMundo")
    public String olaMundo(){
    return "Olá meu nome é Spring boot e estou UP";

    }
}
