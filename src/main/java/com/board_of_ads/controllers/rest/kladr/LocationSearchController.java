package com.board_of_ads.controllers.rest.kladr;

import com.board_of_ads.models.dto.LocationItemDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.RegionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/location")
@AllArgsConstructor
public class LocationSearchController {
    private static final Logger logger = LoggerFactory.getLogger(CityRestController.class);

    private final CityService cityService;
    private final RegionService regionService;

    @GetMapping("search/{query}")
    public ResponseEntity<List<LocationItemDTO>> searchLocation(@PathVariable String query) {
        List<Region> regionList = regionService.findByName(query);
        List<City> cityList = cityService.findAllByName(query);
        List<LocationItemDTO> locationItemDTOList = new ArrayList<>();

        return ResponseEntity.ok(locationItemDTOList);
    }
}
