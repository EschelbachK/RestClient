package org.example.restclient.controller;

import org.example.restclient.model.*;
import org.example.restclient.service.RR_UserClient_Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Basis-URL für alle Endpunkte in dieser Klasse
@RequestMapping("/api/users")
public class RR_User_Controller {

    private final RR_UserClient_Service service;

    // Konstruktor-Injektion: "Spring" fügt automatisch die Service-Instanz ein
    public RR_User_Controller(RR_UserClient_Service service) {
        this.service = service;
    }

    @GetMapping
    public List<RR_User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public RR_Create_User_Response createUser(@RequestBody RR_Create_User_Request request) {
        // Nimmt JSON aus dem Request-Body, erstellt einen neuen User und gibt die Antwort zurück
        return service.createUser(request);
    }

    // Benutzer löschen per ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    // Benutzer aktualisieren per ID und Request-Body
    @PutMapping("/{id}")
    public RR_Update_User_Response updateUser(@PathVariable int id, @RequestBody RR_Update_User_Request request) {
        return service.updateUser(id, request);
    }
}
