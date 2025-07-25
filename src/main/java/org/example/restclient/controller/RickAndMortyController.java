package org.example.restclient.controller;


import org.example.restclient.service.RUM_Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")

public class RUM_Controller {

    private final RUM_Service service;

    public RUM_Controller(RUM_Service service) {
        this.service = service;
    }

    @GetMapping("/characters")
    public List<Character> getAllCharacters() {
        return service.getAllCharacters();
    }

    @GetMapping("/characters/{id}")
    public Character getCharacterById(@PathVariable String id) {
        return service.getCharacterById(id);
    }


}