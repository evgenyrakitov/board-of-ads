package com.board_of_ads.service.impl;

import com.board_of_ads.models.dto.LocationItemDTO;
import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.service.interfaces.LocationItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationItemServiceImpl implements LocationItemService {

    @Override
    public List<LocationItemDTO> buildLocationItemDTOListFromRegions(List<Region> regionList) {
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

    @Override
    public List<LocationItemDTO> buildLocationItemDTOListFromCities(List<City> cityList) {
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
