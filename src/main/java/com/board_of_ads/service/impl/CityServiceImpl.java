package com.board_of_ads.service.impl;

import com.board_of_ads.models.kladr.City;
import com.board_of_ads.repository.CityRepository;
import com.board_of_ads.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    @Override
    public List<City> findAllByName(String name) {
        return cityRepository.findAllByName(name);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public List<City> findAllByRegionId(Long regionId) {
        return cityRepository.findByRegionId(regionId);
    }

    @Override
    public City findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }
}
