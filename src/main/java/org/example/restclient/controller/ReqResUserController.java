package org.example.restclient.controller;

import org.example.restclient.model.*;
import org.example.restclient.service.ReqResUserClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Kennzeichnet die Klasse als REST-Controller, der HTTP-Anfragen beantwortet
@RequestMapping("/api/users") // Basis-URL für alle Endpunkte in dieser Klasse (z.B. /api/users)
public class ReqResUserController {

    private final ReqResUserClientService service; // Service, der die Logik für User-Operationen kapselt

    // Konstruktor-Injektion: "Spring" fügt automatisch die Service-Instanz ein
    public ReqResUserController(ReqResUserClientService service) {
        this.service = service;
    }

    @GetMapping // Reagiert auf GET-Anfragen an /api/users
    public List<ReqResUser> getAllUsers() {
        // Ruft alle Benutzer von der externen API ab und gibt sie zurück
        return service.getAllUsers();
    }

    @PostMapping // Reagiert auf POST-Anfragen an /api/users
    public ReqResCreateUserResponse createUser(@RequestBody ReqResCreateUserRequest request) {
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
    public ReqResUpdateUserResponse updateUser(@PathVariable int id, @RequestBody ReqResUpdateUserRequest request) {
        return service.updateUser(id, request);
    }
}
