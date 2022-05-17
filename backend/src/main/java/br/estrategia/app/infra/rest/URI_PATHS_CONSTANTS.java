package br.estrategia.app.infra.rest;

public class URI_PATHS_CONSTANTS {
    public static final String URL_BASE = "/api";
    public static final String VERSAO = "/v1";
    public static final String URL_COM_VERSIONAMENTO = URL_BASE + VERSAO;

    public static final String LIVENESS = "/monitor/liveness";
    public static final String READINESS = "/monitor/readiness";

    public static final  String ALUNOS_API = URL_COM_VERSIONAMENTO + "/alunos";

}
