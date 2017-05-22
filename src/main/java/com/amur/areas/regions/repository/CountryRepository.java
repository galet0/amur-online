package com.amur.areas.regions.repository;

import com.amur.areas.regions.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "SELECT c.name FROM Country AS c")
    List<String> getCountriesNames();

    Country findOneByName(String name);
}
