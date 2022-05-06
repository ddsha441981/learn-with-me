package com.codewithcup.learnwithme.subcategory;

import com.codewithcup.learnwithme.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findByCategory(Category category);
    SubCategory findBySubName(String subName);

    @Query("select s from SubCategory s where s.isPublished = true")
    List<SubCategory> getSubCategoryByActiveOnly();

    @Query("select s from SubCategory s where s.isPublished = false")
    List<SubCategory> findByGetSubCategoryFalse();

    @Query(value = "select * from subcategory as s where s.is_published=true and s.category_category_id=?1" ,nativeQuery = true)
    List<SubCategory> findByCategoryByActiveAll(Long categoryId);

    @Query(value = "select * from subcategory order by scat_id desc limit 10",nativeQuery = true)
    Set<SubCategory> findByLatestTags();

    @Query(value = "select * from category",nativeQuery = true)
    List<Category> findByCategoryId();
}
