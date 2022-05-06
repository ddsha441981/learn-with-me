package com.codewithcup.learnwithme.category;

import com.codewithcup.learnwithme.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private  CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        Category cSave = categoryRepository.save(category);
        return cSave;
    }

    @Override
    public Category getCategory(Long categoryId) {
        Category gCategory = categoryRepository.findById(categoryId).orElse(null);
//        if (gCategory == null)
//            throw new ResourceNotFoundException("Data not found");
        return gCategory;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoriesActivate() {
        return categoryRepository.findAllActive();
    }


    @Override
    public Category getCategoryUsingName(String categoryName) {

        Category byCategoryName = categoryRepository.findByCategoryName(categoryName);
        if(byCategoryName == null)
            throw new RuntimeException("Data not listed right now");
        return byCategoryName;
    }

    @Override
    public Category updateCategory(Category category) {
        Category cUpdate = categoryRepository.save(category);
        return cUpdate;
    }

    @Override
    public Category deleteCategory(Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        categoryRepository.deleteById(categoryId);
        return category;
    }

}
