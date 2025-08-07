package org.example.restclient.model;

import java.util.List;

// Diese Klasse repräsentiert die Antwort der API, die sowohl ein Info-Objekt als auch eine Liste von Charakteren enthält
public record RUM_Response(RUM_Info info, List<RUM_Char> results) {

}
