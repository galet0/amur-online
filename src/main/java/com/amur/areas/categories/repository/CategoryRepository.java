package com.amur.areas.categories.repository;

import com.amur.areas.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//
//    @Query(value = "SELECT c.name FROM Category AS c")
//    Set<String> getCategoriesNames();

    Category findOneByName(String categoryName);

    @Modifying
    @Query(value = "UPDATE Category " +
            "SET name =:categoryName " +
            "WHERE id =:id")
    void findOneToEdit(@Param(value = "id") long id,
                       @Param(value = "categoryName") String categoryName);

    @Query(value = "SELECT c.name FROM Category AS c " +
            "WHERE c.id =:id")
    String findCategoryNameById(@Param(value = "id") long id);
}
