package org.example.restclient.service;

import org.example.restclient.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RR_UserClient_Service {

    private final RestClient restClient;

    // Konstruktor: baut den RestClient mit Basis-URL und API-Key auf
    public RR_UserClient_Service(RestClient.Builder builder) {
        // Basisadresse der API setzen
        this.restClient = builder
                .baseUrl("https://reqres.in/api")
                // Standard-Header mit API-Key hinzufügen
                .defaultHeader("x-api-key", "reqres-free-v1")
                // RestClient bauen
                .build();
    }

    public List<RR_User> getAllUsers() {
        // GET-Anfrage an /users?page=2 starten
        RR_Response response = restClient.get()
                .uri("/users?page=2")
                // Anfrage ausführen und Antwort holen
                .retrieve()
                // Antwort als ReqResResponse-Objekt parsen
                .body(RR_Response.class);

        // Antwort in Konsole ausgeben
        System.out.println("Antwort erhalten: " + response);

        // Liste der Benutzer aus der Antwort zurückgeben
        return response.data();
    }

    // Methode um neuen Benutzer über "POST" anzulegen
    public RR_Create_User_Response createUser(RR_Create_User_Request request) {
        // POST-Anfrage an /users mit Request-Body starten
        return restClient.post()
                .uri("/users")
                .body(request)
                .retrieve()
                // Antwort als ReqResCreateUserResponse-Objekt parsen und zurückgeben
                .body(RR_Create_User_Response.class);
    }

    // Benutzer aktualisieren
    public RR_Update_User_Response updateUser(int id, RR_Update_User_Request request) {
        return restClient.put()
                .uri("/users/" + id)
                .body(request)
                .retrieve()
                .body(RR_Update_User_Response.class);
    }

    // Benutzer löschen
    public void deleteUser(int id) {
        restClient.delete()
                .uri("/users/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}
