package com.codewithcup.learnwithme.category;

import java.util.List;

public interface CategoryService {
    public Category addCategory(Category  category);
    public Category getCategory(Long categoryId);
    public List<Category> getCategories();
    public Category getCategoryUsingName(String categoryName);
    public Category updateCategory(Category category);
    public Category deleteCategory(Long categoryId);
    public List<Category> getCategoriesActivate();
}
