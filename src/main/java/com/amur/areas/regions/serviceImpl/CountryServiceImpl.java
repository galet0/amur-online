package com.amur.areas.regions.serviceImpl;

import com.amur.areas.regions.entity.Country;
import com.amur.areas.regions.repository.CountryRepository;
import com.amur.areas.regions.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    private final CountryRepository countryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getCountriesNames() {
        List<String> countries = this.countryRepository.getCountriesNames();
        return countries;
    }

    @Override
    public Country findOneByName(String name) {
        Country country = this.countryRepository.findOneByName(name);
        return country;
    }
}
