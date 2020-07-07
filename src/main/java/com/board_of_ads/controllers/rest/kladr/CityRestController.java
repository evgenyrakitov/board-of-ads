package com.avito.controllers.rest.kladr;


import com.avito.models.kladr.City;
import com.avito.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/kladr/city")
@AllArgsConstructor
public class CityRestController {
    private static final Logger logger = LoggerFactory.getLogger(CityRestController.class);

    private final CityService cityService;

    @GetMapping("/allCities")
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<City>> getCitiesByName(@RequestParam String name) {
        return ResponseEntity.ok(cityService.findAllByName(name));
    }

    @GetMapping("/regionCities")
    public ResponseEntity<List<City>> getCitiesByRegionId(@RequestParam Long id) {
        return ResponseEntity.ok(cityService.findAllByRegionId(id));
    }

}
