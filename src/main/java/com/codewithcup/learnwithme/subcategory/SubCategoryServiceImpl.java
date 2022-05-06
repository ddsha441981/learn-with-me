package com.codewithcup.learnwithme.subcategory;

import com.codewithcup.learnwithme.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory addSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory getSubCategoryList(Long scatId) {
        return subCategoryRepository.findById(scatId).get();
    }

    @Override
    public Set<SubCategory> getSubCategories() {
        return new HashSet<>(subCategoryRepository.findAll());
    }

    @Override
    public SubCategory updateSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory deleteSubCategory(Long scatId) {
        SubCategory subCategory = new SubCategory();
        subCategory.setScatId(scatId);
        subCategoryRepository.deleteById(scatId);
        return subCategory;
    }

    /*====================================Business Logic Method====================================*/

    @Override
    public SubCategory getSubcategoryUsingName(String subName) {
        SubCategory subCategoryName = subCategoryRepository.findBySubName(subName);
        return subCategoryName;
    }

    @Override
    public List<SubCategory> getSubcategoriesOfCategory(Category category) {
        List<SubCategory> bySubCategory = subCategoryRepository.findByCategory(category);
        return bySubCategory;
    }

    @Override
    public List<SubCategory> getCategoriesByActiveId(Long categoryId) {
        List<SubCategory> byCategoryByActiveAll = subCategoryRepository.findByCategoryByActiveAll(categoryId);
        return byCategoryByActiveAll;
    }

    @Override
    public List<Category> getCategoriesDis() {
        List<Category> subCategoryByActiveOnly = subCategoryRepository.findByCategoryId();
        Stream<String> stringStream = subCategoryByActiveOnly.stream().map(Category::getCategoryName);
        System.out.println(subCategoryByActiveOnly);
        return subCategoryByActiveOnly;

    }

    @Override
    public List<SubCategory> getActiveSubCategories() {
        List<SubCategory> subCategoryByActiveOnly = subCategoryRepository.getSubCategoryByActiveOnly();
        return subCategoryByActiveOnly;
    }

    @Override
    public List<SubCategory> getInActiveSubCategoriesAll() {

        return subCategoryRepository.findByGetSubCategoryFalse();
    }

    @Override
    public Set<SubCategory> getLatestTags() {
        Set<SubCategory> byLatestTags = this.subCategoryRepository.findByLatestTags();
        return new LinkedHashSet(byLatestTags);
    }

    /*====================================Business Logic Method====================================*/
}
