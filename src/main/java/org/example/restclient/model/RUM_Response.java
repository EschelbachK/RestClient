package org.example.restclient.model;

import java.util.List;

// Diese Klasse repräsentiert die Antwort der API, die sowohl ein Info-Objekt als auch eine Liste von Charakteren enthält
public record RUM_Response(Info info, List<RUM_Char> results) {

    // Diese Klasse repräsentiert die Metadaten der Antwort, wie die Anzahl der Charaktere, Seiten, und die Navigationselemente
    public record Info(int count, int pages, String next, String prev) {}
}
