package com.codewithcup.learnwithme.subcategory;

import com.codewithcup.learnwithme.category.Category;

import java.util.List;
import java.util.Set;

public interface SubCategoryService {
    public SubCategory addSubCategory(SubCategory subCategory);
    public SubCategory getSubCategoryList(Long scatId);
    public Set<SubCategory> getSubCategories();
    public SubCategory updateSubCategory(SubCategory subCategory);
    public SubCategory deleteSubCategory(Long scatId);

    //Bussiness Logic
    public SubCategory getSubcategoryUsingName(String subName);
    //getting subcategory by using its categorise
    public List<SubCategory> getSubcategoriesOfCategory(Category category);
    public List<SubCategory> getCategoriesByActiveId(Long  categoryId);
    public List<SubCategory> getActiveSubCategories();
    public List<SubCategory> getInActiveSubCategoriesAll();
    public Set<SubCategory> getLatestTags();

    public List<Category> getCategoriesDis();
}
