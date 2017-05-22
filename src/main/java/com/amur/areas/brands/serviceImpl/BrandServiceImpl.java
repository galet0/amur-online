package com.amur.areas.brands.serviceImpl;

import com.amur.areas.brands.entity.Brand;
import com.amur.areas.brands.models.view.BrandViewModel;
import com.amur.areas.brands.repository.BrandRepository;
import com.amur.areas.brands.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getAllBrands() {
        List<String> brands = this.brandRepository.getBrandsNames();
        return brands;
    }

    @Override
    public Brand findOneByName(String brandName) {
        Brand brand = this.brandRepository.findOneByName(brandName);
        return brand;
    }
}
