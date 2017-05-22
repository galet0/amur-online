package com.amur.areas.regions.service;


import com.amur.areas.regions.entity.Country;

import java.util.List;

public interface CountryService {

    List<String> getCountriesNames();

    Country findOneByName(String name);
}
