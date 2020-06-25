package com.avito.service.impl;

import com.avito.dao.interfaces.CityRepository;
import com.avito.models.kladr.City;
import com.avito.service.interfaces.CityService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private CityRepository cityRepository;

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
}
