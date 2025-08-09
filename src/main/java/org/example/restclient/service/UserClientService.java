package org.example.restclient.service;

import org.example.restclient.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserClientService {

    private final RestClient restClient;

    // Konstruktor: baut den RestClient mit Basis-URL und API-Key auf
    public UserClientService(RestClient.Builder builder) {
        // Basisadresse der API setzen
        this.restClient = builder
                .baseUrl("https://reqres.in/api")
                // Standard-Header mit API-Key hinzufügen
                .defaultHeader("x-api-key", "reqres-free-v1")
                // RestClient bauen
                .build();
    }

    public List<User> getAllUsers() {
        // GET-Anfrage an /users?page=2 starten
        Response response = restClient.get()
                .uri("/users?page=2")
                // Anfrage ausführen und Antwort holen
                .retrieve()
                // Antwort als ReqResResponse-Objekt parsen
                .body(Response.class);

        // Antwort in Konsole ausgeben
        System.out.println("Antwort erhalten: " + response);

        // Liste der Benutzer aus der Antwort zurückgeben
        return response.data();
    }

    // Methode um neuen Benutzer über "POST" anzulegen
    public CreateUserResponse createUser(CreateUserRequest request) {
        // POST-Anfrage an /users mit Request-Body starten
        return restClient.post()
                .uri("/users")
                .body(request)
                .retrieve()
                // Antwort als ReqResCreateUserResponse-Objekt parsen und zurückgeben
                .body(CreateUserResponse.class);
    }

    // Benutzer aktualisieren
    public Update_User_Response updateUser(int id, UpdateUserRequest request) {
        return restClient.put()
                .uri("/users/" + id)
                .body(request)
                .retrieve()
                .body(Update_User_Response.class);
    }

    // Benutzer löschen
    public void deleteUser(int id) {
        restClient.delete()
                .uri("/users/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}
