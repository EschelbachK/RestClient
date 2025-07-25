
package org.example.restclient.service;

import org.example.restclient.model.RickAndMortyChar;
import org.example.restclient.model.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RickAndMortyService {

    // Client bauen
    private final RestClient restClient;

    public RickAndMortyService(RestClient.Builder restClient) {
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
    public List<RickAndMortyChar> getCharsByStatus(String status) {
        return restClient.get()
                .uri("/character/?status="+status)
                .retrieve()
                .body(RickAndMortyResponse.class)
                .results();
    }

    public int getSpeciesStatistic(String species) {
        return restClient.get()
                .uri("/character/?species="+species)
                .retrieve()
                .body(RickAndMortyResponse.class)
                .info()
                .count();
    }
}
