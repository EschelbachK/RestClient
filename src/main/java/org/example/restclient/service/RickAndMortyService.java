
package org.example.restclient.service;

import org.example.restclient.model.RickAndMortyChar;
import org.example.restclient.model.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {

    private final RestClient restClient;

    public RickAndMortyService(RestClient.Builder restClient) {
        this.restClient = restClient
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<RickAndMortyChar> getAllChars() {
        return restClient.post()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyResponse.class)
                .results();

    }

    public RickAndMortyChar getCharById(int id) {
        return restClient.get()
                .uri("/character/"+id)
                .retrieve()
                .body(RickAndMortyChar.class);

    }
}
