package com.board_of_ads.service.interfaces;

import com.board_of_ads.models.kladr.City;


import java.util.List;

public interface CityService {

    List<City> findAll();

    List<City> findAllByName(String name);

    List<City> findAllByRegionId(Long regionId);
}
