package com.amur.areas.subcategories.repository;

import com.amur.areas.subcategories.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Set<Subcategory> findAllByCategoryName(String categoryName);

    Set<Subcategory> findAllByCategoryId(long categoryId);

//    @Query(value = "SELECT s.name FROM Subcategory AS s " +
//            "WHERE s.category = :categoryName")
//    Set<String> getSubCategoriesNamesByCategoryName(@Param(value = "categoryName") String categoryName);

    Subcategory findOneByName(String subcategoryName);

    @Modifying
    @Query(value = "UPDATE Subcategory " +
            "SET name =:name " +
            "WHERE id =:id")
    void findOneToEdit(@Param(value = "id") long id,
                       @Param(value = "name") String name);

    @Query(value = "SELECT s.name FROM Subcategory AS s " +
            "WHERE s.id =:id")
    String findSubcategoryNameBySubcategoryId(@Param(value = "id") long id);

    @Query(value = "SELECT s.category.name FROM Subcategory AS s " +
            "WHERE s.id =:id")
    String findCategoryNameBySubcategoryId(@Param(value = "id")long id);
}
