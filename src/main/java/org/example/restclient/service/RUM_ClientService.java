package org.example.restclient.service;

import org.example.restclient.dto.RUM_CharacterDTO;
import org.example.restclient.model.RUM_Char;
import org.example.restclient.model.RUM_Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

// Markiert die Klasse als Service-Komponente in Spring
@Service
public class RUM_ClientService {

    // Deklariert den RestClient für API-Anfragen
    private final RestClient restClient;

    // Konstruktor, der den RestClient mit der Basis-URL initialisiert
    public RUM_ClientService(RestClient.Builder restClientBuilder) {
        // Setzt die Basis-URL der API für den RestClient
        this.restClient = restClientBuilder
                .baseUrl("https://rickandmortyapi.com/api")
                // Baut den RestClient
                .build();
    }

    // Methode, um alle Charaktere mit reduzierten Informationen zurückzugeben
    public List<RUM_CharacterDTO> getAllReducedChars() {
        // Sendet eine GET-Anfrage an /character, um alle Charaktere zu erhalten
        return restClient.get()
                // API-Endpunkt für alle Charaktere
                .uri("/character")
                // Abrufen der Antwort von der API
                .retrieve()
                // Antwort in ein RUM_Response-Objekt umwandeln
                .body(RUM_Response.class)
                // Holt die Liste der Charaktere aus der Antwort
                .results()
                // Wandelt die Liste der Charaktere in einen Stream um
                .stream()
                // Wandelt jedes Charakter-Objekt in ein DTO um
                .map(charakter -> new RUM_CharacterDTO(charakter.id(), charakter.name(), charakter.species()))
                // Sammelt alle DTOs in eine Liste und gibt sie zurück
                .collect(Collectors.toList());
    }

    // Methode, um einen Charakter anhand seiner ID zu holen
    public RUM_CharacterDTO getReducedCharById(int id) {
        // Sendet eine GET-Anfrage an /character/{id}, um den Charakter mit der angegebenen ID zu erhalten
        RUM_Char charakter = restClient.get()
                // API-Endpunkt für den Charakter mit der angegebenen ID
                .uri("/character/" + id)
                // Abrufen der Antwort von der API
                .retrieve()
                // Antwort in ein RUM_Char-Objekt umwandeln
                .body(RUM_Char.class);

        // Sicherstellen, dass der Charakter nicht null ist
        assert charakter != null;
        // Wandelt den Charakter in ein DTO um und gibt es zurück
        return new RUM_CharacterDTO(charakter.id(), charakter.name(), charakter.species());
    }

    // Methode, um Charaktere nach Status zu filtern (z.B. "alive")
    public List<RUM_CharacterDTO> getReducedCharsByStatus(String status) {
        // Sendet eine GET-Anfrage an /character/?status={status}, um Charaktere nach Status zu filtern
        return restClient.get()
                // API-Endpunkt mit Status-Filter
                .uri("/character/?status=" + status)
                // Abrufen der Antwort von der API
                .retrieve()
                // Antwort in ein RUM_Response-Objekt umwandeln
                .body(RUM_Response.class)
                // Holt die Liste der Charaktere aus der Antwort
                .results()
                // Wandelt die Liste der Charaktere in einen Stream um
                .stream()
                // Wandelt jedes Charakter-Objekt in ein DTO um
                .map(c -> new RUM_CharacterDTO(c.id(), c.name(), c.species()))
                // Sammelt alle DTOs in eine Liste und gibt sie zurück
                .collect(Collectors.toList());
    }

    // Methode, um die Anzahl der Charaktere einer bestimmten Spezies zurückzugeben
    public int getSpeciesStatistic(String species) {
        // Sendet eine GET-Anfrage an /character/?species={species}, um Charaktere nach Spezies zu filtern
        return restClient.get()
                // API-Endpunkt mit Spezies-Filter
                .uri("/character/?species=" + species)
                // Abrufen der Antwort von der API
                .retrieve()
                // Antwort in ein RUM_Response-Objekt umwandeln
                .body(RUM_Response.class)
                // Holt das 'info' Objekt aus der Antwort
                .info()
                // Gibt die Anzahl der Charaktere dieser Spezies zurück
                .count();
    }
}
