package com.amur.areas.regions.service;

import com.amur.areas.regions.entity.Region;

import java.util.List;

public interface RegionService {

    List<String> getRegionsNames();

    Region findOneByName(String name);
}
