
package org.example.restclient.controller;


import org.example.restclient.model.RickAndMortyChar;
import org.example.restclient.service.RickAndMortyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rick")
public class RickAndMortyController {

    private final RickAndMortyService service;

    public RickAndMortyController(RickAndMortyService service) {
        this.service = service;
    }

    @GetMapping
    public List<RickAndMortyChar> getAllChars(){
        return service.getAllChars();
    }

    @GetMapping("/{id}")
    public RickAndMortyChar getCharById(@PathVariable int id){
        return service.getCharById(id);
    }
    @GetMapping("/search")
    public List<RickAndMortyChar> getCharsByStatus(@RequestParam String status){
        return service.getCharsByStatus(status);
    }

    @GetMapping("/species-statistic")
    public int getSpeciesStatistic(@RequestParam String species){
        return service.getSpeciesStatistic(species);
    }
}