package com.amur.areas.products.repository;

import com.amur.areas.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product AS p")
    List<Product> findAll();

    Page<Product> findAllBySubcategoryId(Pageable pageable, long subcategoryId);

    Page<Product> findAllByCategoryId(Pageable pageable, long categoryId);

    @Modifying
    @Query(value = "UPDATE Product " +
            "SET name =:name," +
            "price =:price," +
            "size =:size," +
            "weight =:weight," +
            "length =:length," +
            "description =:description " +
            "WHERE id =:id")
    void updateProduct(@Param(value = "name") String name,
                       @Param(value = "price") Double price,
                       @Param(value = "size") Double size,
                       @Param(value = "weight") Double weight,
                       @Param(value = "length") Float length,
                       @Param(value = "description") String description,
                       @Param(value = "id") long id);

    @Query(value = "SELECT p FROM Product AS p " +
            "WHERE p.id =:productId " +
            "AND p.category.id =:categoryId " +
            "AND p.subcategory.id =:subcategoryId")
    Product findOneByIdCategoryIdAndSubcategoryId(@Param(value = "categoryId") long categoryId,
                                                  @Param(value = "subcategoryId") long subcategoryId,
                                                  @Param(value = "productId") long productId);

    @Query(value = "SELECT p FROM Product AS p " +
            "WHERE p.createdOn  BETWEEN :firstDate AND :secondDate " +
            "ORDER BY p.createdOn DESC ")
    List<Product> findTop6AfterDate(@Param(value = "firstDate") Date firstDate, @Param(value = "secondDate") Date secondDate);

    @Query(value = "SELECT p FROM Product AS p " +
            "WHERE p.name LIKE CONCAT('%',:name,'%')")
    Page<Product> findAllByName(@Param(value = "name") String name, Pageable pageable);

}
