
package org.example.restclient.service;

import org.example.restclient.model.RickAndMortyChar;
import org.example.restclient.model.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;


@Service
public class RickAndMortyClientService {

    // Client bauen
    private final RestClient restClient;

    public RickAndMortyClientService(RestClient.Builder restClient) {
        this.restClient = restClient
                // Zugang zur Api verschaffen
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<RickAndMortyChar> getAllChars() {
        return restClient.get()
                .uri("/character")
                // abrufen
                .retrieve()
                // in eine Klasse umbauen lassen mit Objektvorlage
                .body(RickAndMortyResponse.class)
                .results();


    }

    public RickAndMortyChar getCharById(int id) {
        return restClient.get()
                .uri("/character/" + id)
                .retrieve()
                .body(RickAndMortyChar.class);

    }
    // Gibt eine Liste von RickAndMortyChar zurück, mit dem angegebenen Status
    public List<RickAndMortyChar> getCharsByStatus(String status) {
        return restClient.get()
                // https://rickandmortyapi.com/api/character/?status=alive
                .uri("/character/?status="+status)
                .retrieve()
                .body(RickAndMortyResponse.class)
                // Gibt die Liste der Charaktere zurück
                .results();
    }

    public int getSpeciesStatistic(String species) {
        return restClient.get()

                // https://rickandmortyapi.com/api/character/?species=Human
                .uri("/character/?species="+species)
                .retrieve()
                // wandelt JSON in Record um
                .body(RickAndMortyResponse.class)
                // Gibt das "info"-Objekt aus der API-Antwort zurück.
                .info()
                // Ruft die Anzahl der Charaktere aus dem info-Objekt ab.
                .count();
    }
}
