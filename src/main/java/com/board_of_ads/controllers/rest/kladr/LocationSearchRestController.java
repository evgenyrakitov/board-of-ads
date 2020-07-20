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
public class LocationSearchRestController {
    private static final Logger logger = LoggerFactory.getLogger(CityRestController.class);

    private final CityService cityService;
    private final RegionService regionService;

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

        locationItemDTOList.addAll(buildLocationItemDTOListFromRegions(regionList));
        locationItemDTOList.addAll(buildLocationItemDTOListFromCities(cityList));

        if (cityList.size() == 1 && regionList.size() == 0) {
            regionList.add(cityList.get(0).getRegion());
            locationItemDTOList.addAll(buildLocationItemDTOListFromRegions(regionList));
        }

        return ResponseEntity.ok(locationItemDTOList);
    }

    private List<LocationItemDTO> buildLocationItemDTOListFromRegions(List<Region> regionList) {
        List<LocationItemDTO> locationItemDTOList = new ArrayList<>();

        for (Region region : regionList) {
            LocationItemDTO locationItemDTO = new LocationItemDTO();
            locationItemDTO.setCityId(-1);
            locationItemDTO.setRegionId(region.getId());
            locationItemDTO.setShortName(region.getBeatyName());
            locationItemDTO.setBeautyName(region.getBeatyName());
            locationItemDTOList.add(locationItemDTO);
        }

        return locationItemDTOList;
    }

    private List<LocationItemDTO> buildLocationItemDTOListFromCities(List<City> cityList) {
        List<LocationItemDTO> locationItemDTOList = new ArrayList<>();

        for (City city : cityList) {
            LocationItemDTO locationItemDTO = new LocationItemDTO();
            locationItemDTO.setCityId(city.getId());
            locationItemDTO.setRegionId(city.getRegion().getId());
            locationItemDTO.setShortName(city.getName());
            locationItemDTO.setBeautyName(
                    new StringBuilder()
                            .append(city.getName())
                            .append(" (")
                            .append(city.getRegion().getBeatyName())
                            .append(")")
                            .toString());
            locationItemDTOList.add(locationItemDTO);
        }

        return locationItemDTOList;
    }
}
