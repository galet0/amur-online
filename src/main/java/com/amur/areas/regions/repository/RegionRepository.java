package com.amur.areas.regions.repository;

import com.amur.areas.regions.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query(value = "SELECT r.name FROM Region AS r")
    List<String> getRegionsNames();

    Region findOneByName(String name);
}
