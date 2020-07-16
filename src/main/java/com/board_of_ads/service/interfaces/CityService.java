package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.kladr.City;


import java.util.List;

public interface CityService {

    City findById(Long id);

    List<City> findAll();

    List<City> findAllByName(String name);

    List<City> findAllByRegionId(Long regionId);

    City findCityByName(String name);
}
