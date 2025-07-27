package org.example.restclient.service;

import org.example.restclient.model.ReqResCreateUserRequest;
import org.example.restclient.model.ReqResCreateUserResponse;
import org.example.restclient.model.ReqResResponse;
import org.example.restclient.model.ReqResUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service  // Kennzeichnet die Klasse als Service-Komponente (Geschäftslogik)
public class ReqResUserService {

    // HTTP-Client für API-Aufrufe
    private final RestClient restClient;

    // Konstruktor: baut den RestClient mit Basis-URL und API-Key auf
    public ReqResUserService(RestClient.Builder builder) {
        // Basisadresse der API setzen
        this.restClient = builder
                .baseUrl("https://reqres.in/api")
                // Standard-Header mit API-Key hinzufügen
                .defaultHeader("x-api-key", "reqres-free-v1")
                // RestClient bauen
                .build();
    }

    // Methode um alle Benutzer von der API abzurufen
    public List<ReqResUser> getAllUsers() {
        // GET-Anfrage an /users?page=2 starten
        ReqResResponse response = restClient.get()
                .uri("/users?page=2")
                // Anfrage ausführen und Antwort holen
                .retrieve()
                // Antwort als ReqResResponse-Objekt parsen
                .body(ReqResResponse.class);

        // Antwort in Konsole ausgeben
        System.out.println("Antwort erhalten: " + response);

        // Liste der Benutzer aus der Antwort zurückgeben
        return response.data();
    }

    // Methode um neuen Benutzer über "POST" anzulegen
    public ReqResCreateUserResponse createUser(ReqResCreateUserRequest request) {
        // POST-Anfrage an /users mit Request-Body starten
        return restClient.post()
                .uri("/users")
                .body(request)
                .retrieve()
                // Antwort als ReqResCreateUserResponse-Objekt parsen und zurückgeben
                .body(ReqResCreateUserResponse.class);
    }
}
