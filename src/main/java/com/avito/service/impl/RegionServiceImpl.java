package com.avito.service.impl;

import com.avito.dao.interfaces.RegionRepository;
import com.avito.models.kladr.Region;
import com.avito.service.interfaces.RegionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {
    private static final Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);

    private RegionRepository regionRepository;

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();}

    @Override
    public Region findByName(String name) {
        return regionRepository.findByName(name);
    }
}
