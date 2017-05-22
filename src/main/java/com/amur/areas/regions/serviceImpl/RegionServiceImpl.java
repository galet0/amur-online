package com.amur.areas.regions.serviceImpl;

import com.amur.areas.regions.entity.Region;
import com.amur.areas.regions.repository.RegionRepository;
import com.amur.areas.regions.service.RegionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository, ModelMapper modelMapper) {
        this.regionRepository = regionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getRegionsNames() {
        List<String> regions = this.regionRepository.getRegionsNames();
        return regions;
    }

    @Override
    public Region findOneByName(String name) {
        Region region = this.regionRepository.findOneByName(name);
        return region;
    }
}
