package org.example.restclient.controller;

import org.example.restclient.model.*;
import org.example.restclient.service.UserClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// Basis-URL für alle Endpunkte in dieser Klasse
@RequestMapping("/api/users")
public class UserController {

    private final UserClientService service;

    // Konstruktor-Injektion: "Spring" fügt automatisch die Service-Instanz ein
    public UserController(UserClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
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
    public Update_User_Response updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        return service.updateUser(id, request);
    }
}
