package org.example.restclient.controller;

import org.example.restclient.dto.RUM_CharacterDTO;
import org.example.restclient.service.RUM_ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST-Controller für API-Endpunkte
@RestController
// Basis-URL für alle Endpunkte
@RequestMapping("/api")
public class RUM_Controller {

    // Service für API-Abfragen
    private final RUM_ClientService service;

    // Konstruktor: Initialisiert den Service
    public RUM_Controller(RUM_ClientService service) {
        this.service = service;
    }

    // Endpunkt: Alle Charaktere, optional nach Status gefiltert
    @GetMapping("/characters")
    public List<RUM_CharacterDTO> getAllReducedChars(@RequestParam(required = false) String status) {
        if (status != null) {
            // Gibt gefilterte Charaktere zurück
            return service.getReducedCharsByStatus(status);
        }
        // Gibt alle Charaktere zurück
        return service.getAllReducedChars();
    }

    // Endpunkt: Einzelner Charakter nach ID
    @GetMapping("/characters/{id}")
    public RUM_CharacterDTO getReducedCharacterById(@PathVariable int id) {
        // Gibt den Charakter mit der angegebenen ID zurück
        return service.getReducedCharById(id);
    }

    // Endpunkt: Statistik für eine Spezies
    @GetMapping("/species-statistic")
    public int getSpeciesStatistic(@RequestParam String species) {
        // Gibt die Anzahl der Charaktere einer Spezies zurück
        return service.getSpeciesStatistic(species);
    }
}
