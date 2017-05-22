package com.amur.areas.products.serviceImpl;

import com.amur.areas.brands.entity.Brand;
import com.amur.areas.brands.service.BrandService;
import com.amur.areas.categories.entity.Category;
import com.amur.areas.products.entity.Product;
import com.amur.areas.products.models.binding.ProductBindingModel;
import com.amur.areas.products.models.binding.ProductEditBindingModel;
import com.amur.areas.products.models.view.ProductDeleteModel;
import com.amur.areas.products.models.view.ProductDetailsView;
import com.amur.areas.products.models.view.ProductView;
import com.amur.areas.products.repository.ProductRepository;
import com.amur.areas.categories.service.CategoryService;
import com.amur.areas.products.service.ProductService;
import com.amur.areas.subcategories.entity.Subcategory;
import com.amur.areas.subcategories.service.SubcategoryService;
import com.amur.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    private final BrandService brandService;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, SubcategoryService subcategoryService, BrandService brandService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductView> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        return this.mapProductToProductView(products);
    }

    @Override
    public ProductView findById(long id) {
        Product product = this.productRepository.findOne(id);
        if(product == null){
            throw new ProductNotFoundException();
        }
        ProductView productView = this.modelMapper.map(product, ProductView.class);
        return productView;
    }

    @Override
    public Page<ProductView> findAll(Pageable pageable) {
        Page<Product> products = this.productRepository.findAll(pageable);
        List<ProductView> productViewList = this.mapProductToProductViewPage(products);
        Long totalElements = products.getTotalElements();
        Page<ProductView> productViews = new PageImpl(productViewList, pageable, totalElements);
        return productViews;
    }

    @Override
    public Page<ProductView> getAllProductsBySubcategoryId(Pageable pageable, long subcategoryId) {
        Page<Product> products = this.productRepository.findAllBySubcategoryId(pageable, subcategoryId);
        List<ProductView> productViews = this.mapProductToProductViewPage(products);
        Long totalElements = products.getTotalElements();
        Page<ProductView> productViewPage = new PageImpl(productViews, pageable, totalElements);
        return productViewPage;
    }

    @Override
    public Page<ProductView> getAllProductsByCategoryId(Pageable pageable, long id) {
        Page<Product> products = this.productRepository.findAllByCategoryId(pageable, id);
        List<ProductView> productViews = this.mapProductToProductViewPage(products);
        Long totalElements = products.getTotalElements();
        Page<ProductView> productViewPage = new PageImpl(productViews, pageable, totalElements);
        return productViewPage;
    }

    @Override
    public void setOrderQuantity(Integer quantity, long productId) {
        Product product = this.productRepository.findOne(productId);
        product.setQuantity(quantity);
    }

    @Override
    public List<ProductView> getNewProducts(Date firstDate, Date secondDate) {
        List<Product> products = this.productRepository.findTop6AfterDate(firstDate, secondDate);
        return this.mapProductToProductView(products);
    }

    @Override
    public Page<ProductView> findAllByName(String name, Pageable pageable) {
        Page<Product> products = this.productRepository.findAllByName(name, pageable);
        List<ProductView> productViews = this.mapProductToProductViewPage(products);
        Long totalElements = products.getTotalElements();
        Page<ProductView> productViewPage = new PageImpl(productViews, pageable, totalElements);

        return productViewPage;
    }

    private List<ProductView> mapProductToProductView(List<Product> products){
        List<ProductView> productViews = new ArrayList<>();
        for (Product product : products) {
            if(product == null){
                throw new ProductNotFoundException();
            }
            ProductView productView = this.modelMapper.map(product, ProductView.class);
            productViews.add(productView);
        }
        return productViews;
    }

    private List<ProductView> mapProductToProductViewPage(Page<Product> products){
        List<ProductView> productViews = new ArrayList<>();
        for (Product product : products) {
            if(product == null){
                throw new ProductNotFoundException();
            }
            ProductView productView = this.modelMapper.map(product, ProductView.class);
            productViews.add(productView);
        }
        return productViews;
    }


    @Override
    public ProductDetailsView showProduct(long id) {
        Product product = this.productRepository.findOne(id);
        if(product == null){
            throw new ProductNotFoundException();
        }

        ProductDetailsView productDetailsView = this.modelMapper.map(product, ProductDetailsView.class);
        return productDetailsView;
    }

    @Override
    public void create(ProductBindingModel productBindingModel) {
        Product product = this.modelMapper.map(productBindingModel, Product.class);

        String categoryName = productBindingModel.getCategoryName();
        Category category = this.categoryService.findOneByName(categoryName);
        product.setCategory(category);

        String subcategoryName = productBindingModel.getSubcategoryName();
        Subcategory subcategory = this.subcategoryService.findOneByName(subcategoryName);
        product.setSubcategory(subcategory);

        String brandName = productBindingModel.getBrandName();
        Brand brand = this.brandService.findOneByName(brandName);
        product.setBrand(brand);

        product.setCreatedOn(new Date());
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public ProductEditBindingModel findOneByIdToEdit(long productId) {
        Product product = this.productRepository.findOne(productId);
        ProductEditBindingModel productEditBindingModel = this.modelMapper.map(product, ProductEditBindingModel.class);
        return productEditBindingModel;
    }

    @Transactional
    @Override
    public void updateProduct(ProductEditBindingModel productEditBindingModel) {
        Product product = this.modelMapper.map(productEditBindingModel, Product.class);
        String name = product.getName();
        Double price = product.getPrice();
        Double size = product.getSize();
        Double weight = product.getWeight();
        Float length = product.getLength();
        String description = product.getDescription();
        long id = product.getId();
        this.productRepository.updateProduct(name, price, size, weight, length, description, id);
    }

    @Override
    public ProductDeleteModel findOneToDelete(long productId) {
        Product product = this.productRepository.findOne(productId);
        ProductDeleteModel productBindingModel = this.modelMapper.map(product, ProductDeleteModel.class);
        return productBindingModel;
    }

    @Override
    public void delete(ProductDeleteModel productDeleteModel) {
        Product product = this.modelMapper.map(productDeleteModel, Product.class);
        this.productRepository.delete(product);
    }

    @Override
    public ProductDetailsView getProductDetails(long categoryId, long subcategoryId, long productId) {
        Product product = this.productRepository.findOneByIdCategoryIdAndSubcategoryId(categoryId, subcategoryId, productId);
        if(product == null){
            throw new ProductNotFoundException();
        }
        ProductDetailsView productDetailsView = this.modelMapper.map(product, ProductDetailsView.class);
        return productDetailsView;
    }

}
