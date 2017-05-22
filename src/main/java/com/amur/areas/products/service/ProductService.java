package com.amur.areas.products.service;

import com.amur.areas.products.models.binding.ProductBindingModel;
import com.amur.areas.products.models.binding.ProductEditBindingModel;
import com.amur.areas.products.models.view.ProductDeleteModel;
import com.amur.areas.products.models.view.ProductDetailsView;
import com.amur.areas.products.models.view.ProductView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ProductService {

    void create(ProductBindingModel productBindingModel);

    ProductView findById(long id);

    ProductDetailsView showProduct(long id);

    ProductEditBindingModel findOneByIdToEdit(long productId);

    void updateProduct(ProductEditBindingModel productEditBindingModel);

    ProductDeleteModel findOneToDelete(long productId);

    void delete(ProductDeleteModel productDeleteModel);

    List<ProductView> getAllProducts();

    ProductDetailsView getProductDetails(long categoryId, long subcategoryId, long productId);

    Page<ProductView> findAll(Pageable pageable);

    Page<ProductView> getAllProductsBySubcategoryId(Pageable pageable, long subcategoryId);

    Page<ProductView> getAllProductsByCategoryId(Pageable pageable, long id);

    void setOrderQuantity(Integer quantity, long productId);

    List<ProductView> getNewProducts(Date firstDate, Date secondDate);

    Page<ProductView> findAllByName(String name, Pageable pageable);

}
