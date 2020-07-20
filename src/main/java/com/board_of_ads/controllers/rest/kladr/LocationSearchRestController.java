package com.board_of_ads.controllers.rest.kladr;

import com.board_of_ads.models.dto.LocationItemDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.service.interfaces.CityService;
import com.board_of_ads.service.interfaces.LocationItemService;
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
public class LocationSearchRestController {
    private static final Logger logger = LoggerFactory.getLogger(CityRestController.class);

    private final CityService cityService;
    private final RegionService regionService;
    private final LocationItemService locationItemService;

    /**
     * Ищет по строке подходящие города и регионы и возвращает из них список ДТО.
     * <p>
     * Если по запросу найден только один город - то возвращает ещё и регион этого города в добавок. Это сделано
     * для удобства пользователя на сайте, если он вдруг захочет расширить область поиска.
     *
     * @param query - текст в свободной форме
     * @return - список ДТО локейшенов
     */
    @GetMapping("search/{query}")
    public ResponseEntity<List<LocationItemDTO>> searchLocation(@PathVariable String query) {

        List<Region> regionList = regionService.findByBeautyName(query);
        List<City> cityList = cityService.findAllByName(query);

        List<LocationItemDTO> locationItemDTOList = new ArrayList<>();

        locationItemDTOList.addAll(locationItemService.buildLocationItemDTOListFromRegions(regionList));
        locationItemDTOList.addAll(locationItemService.buildLocationItemDTOListFromCities(cityList));

        if (cityList.size() == 1 && regionList.size() == 0) {
            regionList.add(cityList.get(0).getRegion());
            locationItemDTOList.addAll(locationItemService.buildLocationItemDTOListFromRegions(regionList));
        }

        return ResponseEntity.ok(locationItemDTOList);
    }
}
