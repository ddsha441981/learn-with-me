package com.codewithcup.learnwithme.category;

import com.codewithcup.learnwithme.exceptions.CategoriesNotFoundException;
import com.codewithcup.learnwithme.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")

public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> sCategory(@RequestBody Category category) {
        Category aCategory = categoryService.addCategory(category);
        if (aCategory == null) {
            logger.warn("DataNotSavedException Occurs --> {}: ", aCategory);
            throw new RuntimeException("Data not saved");
        }
        logger.info("Category Saved --> {} :",aCategory.getCategoryId());
        return ResponseEntity.status(HttpStatus.OK).body(aCategory);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> uCategory(@RequestBody Category category) {
        Category uPCategory = categoryService.addCategory(category);

        if (uPCategory == null) {
            logger.warn("DataNotUpdateException Occurs {} ", uPCategory);
            throw new RuntimeException("Data not Update");
        }
        logger.info("Data updated with id --> {} : ", uPCategory.getCategoryId());
        return ResponseEntity.status(HttpStatus.OK).body(uPCategory);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<?> gGetCategory(@PathVariable Long categoryId) {
        Category getCategory = categoryService.getCategory(categoryId);
        if (getCategory == null) {
            logger.info("data not found with id {}", categoryId);
            throw new ResourceNotFoundException("data not found with id {}" + categoryId);
        }
        logger.info("Category Found with Id --> {} :",categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(getCategory);
    }

    @GetMapping("/all-categories")
    public ResponseEntity<?> gGetCategories() {
        List<Category> gCategories = categoryService.getCategories();
        if (gCategories.isEmpty()) {
            logger.warn("data not found --> {} :" + gCategories);
            throw new CategoriesNotFoundException("data not found");
        }
        logger.warn("data found --> {} :" , gCategories);
        return ResponseEntity.status(HttpStatus.OK).body(gCategories);
    }

    @GetMapping("/all-categories/actives")
    public ResponseEntity<?> gGetCategoriesActive() {
        List<Category> gCategoriess = categoryService.getCategoriesActivate();
        if (gCategoriess.isEmpty()) {
            logger.warn("data not found --> {} :" + gCategoriess);
            throw new CategoriesNotFoundException("data not found");
        }
        logger.warn("data found --> {} :" , gCategoriess);
        return ResponseEntity.status(HttpStatus.OK).body(gCategoriess);
    }

    @GetMapping("/by-name/{categoryName}")
    public ResponseEntity<?> gGetCategoryUsingName(@PathVariable String categoryName) {
        Category gCategoryName = categoryService.getCategoryUsingName(categoryName);
        if (gCategoryName == null) {
            logger.warn("data not found with this name --> {} :" , categoryName);
            throw new RuntimeException("data not found");
        }
        logger.warn("data found with this name --> {} :" , categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(gCategoryName);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> dCategory(@PathVariable Long categoryId) {
        Category dcategory = categoryService.deleteCategory(categoryId);
        if (dcategory != null) {
            logger.info("data deleted with id --> :  {}", categoryId);
            return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
        }
        logger.info("data not deleted with id --> :  {}", categoryId);
        throw new RuntimeException("data not deleted");
    }


}
