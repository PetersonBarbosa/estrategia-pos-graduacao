package br.estrategia.app.application.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.estrategia.app.infra.rest.URI_PATHS_CONSTANTS.LIVENESS;
import static br.estrategia.app.infra.rest.URI_PATHS_CONSTANTS.READINESS;

@RestController
public class HealthResource {

    @GetMapping(value = LIVENESS,produces = MediaType.APPLICATION_JSON_VALUE)
    public  String isLive(){
        return "{\"status\": \"ok\"}";
    }

    @GetMapping(value = READINESS,produces = MediaType.APPLICATION_JSON_VALUE)
    public  String isReady(){
        return "{\"status\": \"ok\"}";
    }



}
