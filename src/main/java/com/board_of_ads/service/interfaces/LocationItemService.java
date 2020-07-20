package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.dto.LocationItemDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;

import java.util.List;

public interface LocationItemService {

    List<LocationItemDTO> buildLocationItemDTOListFromRegions(List<Region> regionList);

    List<LocationItemDTO> buildLocationItemDTOListFromCities(List<City> cityList);
}
