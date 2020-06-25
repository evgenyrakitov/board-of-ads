package com.avito.service.interfaces;

import com.avito.models.kladr.Region;

import java.util.List;

public interface RegionService {

    List<Region> getAllRegions();

    Region findByName(String name);
}
