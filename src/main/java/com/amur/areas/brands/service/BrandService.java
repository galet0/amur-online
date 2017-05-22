package com.amur.areas.brands.service;

import com.amur.areas.brands.entity.Brand;
import com.amur.areas.brands.models.view.BrandViewModel;

import java.util.List;

public interface BrandService {

    List<String> getAllBrands();

    Brand findOneByName(String brandName);
}
