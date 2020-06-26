package com.avito.controllers.rest.kladr;


import com.avito.models.kladr.Region;
import com.avito.service.interfaces.RegionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/kladr/region")
@AllArgsConstructor
public class RegionRestController {
    private static final Logger logger = LoggerFactory.getLogger(CityRestController.class);

    private final RegionService regionService;

    @GetMapping("/allRegions")
    public List<Region> getAllRegions(){
        return regionService.getAllRegions();
    }

    @GetMapping
    public Region getRegionByName(@RequestParam String name) {
        return regionService.findByName(name);
    }


}
