package com.amur.areas.brands.repository;

import com.amur.areas.brands.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query(value = "SELECT b.name FROM Brand AS b")
    List<String> getBrandsNames();

    @Query(value = "SELECT b FROM Brand AS b " +
            "WHERE b.name =:brandName")
    Brand findOneByName(@Param(value = "brandName") String brandName);
}
