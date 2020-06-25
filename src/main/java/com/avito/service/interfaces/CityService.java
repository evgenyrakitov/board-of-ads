package com.avito.service.interfaces;

import com.avito.models.kladr.City;


import java.util.List;

public interface CityService {

    List<City> findAll();

    List<City> findAllByName(String name);

    List<City> findAllByRegionId(Long regionId);
}
