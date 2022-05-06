package com.codewithcup.learnwithme.subcategory;

import com.codewithcup.learnwithme.category.Category;
import com.codewithcup.learnwithme.exceptions.CategoriesNotFoundException;
import com.codewithcup.learnwithme.exceptions.ContentNotFoundException;
import com.codewithcup.learnwithme.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/")
    public ResponseEntity<SubCategory> sAddSubCategory(@RequestBody SubCategory subCategory) {
        SubCategory aSubCategory = subCategoryService.addSubCategory(subCategory);
        if (aSubCategory == null) {
            logger.info("DataNotSavedException Occurs {} ", aSubCategory);
            throw new RuntimeException("Data not saved");
        }
        logger.info("Sub Category Added --> {} : ", aSubCategory.getScatId());
        return ResponseEntity.status(HttpStatus.OK).body(aSubCategory);
    }

    @PutMapping("/update")
    public ResponseEntity<?> uUpdateSubCategory(@RequestBody SubCategory subCategory) {
        SubCategory uPsubCategory = subCategoryService.updateSubCategory(subCategory);
        if (uPsubCategory == null) {
            logger.info("DataNotUpdateException Occurs {} ", uPsubCategory);
            throw new RuntimeException("Data not Update");
        }
        logger.info("Data updated with id --> {} : ", uPsubCategory.getScatId());
        return ResponseEntity.status(HttpStatus.OK).body(uPsubCategory);
    }

    @DeleteMapping("/delete/{scatId}")
    public ResponseEntity<?> dDeleteSubCategory(@PathVariable Long scatId) {
        SubCategory dSubCategory = subCategoryService.deleteSubCategory(scatId);
        if (dSubCategory != null) {
            logger.info("data deleted with id --> :  {}", scatId);
            return new ResponseEntity<String>("Data deleted Successfully", HttpStatus.OK);
        }
        logger.info("data not deleted with id --> :  {}", scatId);
        throw new RuntimeException("data not deleted");
    }


    @GetMapping("/{scatId}")
    public ResponseEntity<SubCategory> rRetriveSubListById(@PathVariable Long scatId) {
        SubCategory getSubCategory = subCategoryService.getSubCategoryList(scatId);
        if (getSubCategory == null) {
            logger.info("data not found with id  --> {} : ", scatId);
            throw new ResourceNotFoundException("data not found with id {}" + scatId);
        }
        logger.info("data found with id  --> {} : ", scatId);
        return ResponseEntity.status(HttpStatus.OK).body(getSubCategory);
    }

    @GetMapping("/all")
    public ResponseEntity<?> rRetriveSubList() {
        Set<SubCategory> gSubCategories = subCategoryService.getSubCategories();
        if (gSubCategories.isEmpty()) {
            logger.info("data not found  --> {} : " , gSubCategories);
            throw new CategoriesNotFoundException("data not found");
        }
        logger.info("data found with  --> {} : " , gSubCategories);
        return ResponseEntity.status(HttpStatus.OK).body(gSubCategories);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> rRetrivedSpecificCategorizeSubCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        List<SubCategory> subcategoriesOfCategory = subCategoryService.getSubcategoriesOfCategory(category);
        if (subcategoriesOfCategory == null) {
            logger.warn("data not found with this id  --> {} : " , categoryId);
            throw new ContentNotFoundException("Not Available");
        }
        logger.info("Data found with id  --> {} : " , categoryId);
        return new ResponseEntity<>(subcategoriesOfCategory, HttpStatus.OK);
    }

    @GetMapping("/name/{subName}")
    public ResponseEntity<?> rRetrivedSubCategoryByName(@PathVariable String subName) {
        SubCategory subUsingName = subCategoryService.getSubcategoryUsingName(subName);
        if (subUsingName == null) {
            logger.warn("data not found with this name {}" , subUsingName);
            throw new ContentNotFoundException("Not Available");
        }
        logger.warn("data found with this name  --> {} : ", subUsingName);
        return new ResponseEntity<>(subUsingName, HttpStatus.OK);

    }

    @GetMapping("/category/active/{categoryId}")
    public ResponseEntity<?> rRetrivedCategoryByActive(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        List<SubCategory> actives = subCategoryService.getCategoriesByActiveId(categoryId);
        if (actives == null) {
            logger.warn("ContentNotFoundException Occurs --> {} : " , actives);
            throw new ContentNotFoundException("Not Available");
        }
        logger.warn("All Active data is   --> {} : " , actives);
        return new ResponseEntity<>(actives, HttpStatus.OK);
    }

    @GetMapping("/active/all")
    public ResponseEntity<?> rRetrivedSubCategoryByActive() {
        List<SubCategory> actives = subCategoryService.getActiveSubCategories();
        if (actives == null) {
            logger.warn("ContentNotFoundException Occurs --> {} : " , actives);
            throw new ContentNotFoundException("Not Available");
        }
        logger.warn("All Active data is   --> {} : " , actives);
        return new ResponseEntity<>(actives, HttpStatus.OK);
    }

    @GetMapping("/inactive/all")
    public ResponseEntity<?> rRetrivedSubCategoryByInActive() {
        List<SubCategory> inActives = subCategoryService.getInActiveSubCategoriesAll();
        if (inActives == null) {
            logger.warn("ContentNotFoundException Occurs --> {} :" , inActives);
            throw new ContentNotFoundException("Not Available");
        }
        logger.warn("All InActive data is --> {} : " , inActives);
        return new ResponseEntity<>(inActives, HttpStatus.OK);
    }

    @GetMapping("/tags/all")
    public ResponseEntity<?> getTags() {
        Set<SubCategory> tag = subCategoryService.getLatestTags();
        if (tag == null) {
            logger.warn("ContentNotFoundException Occurs --> {} : " , tag);
            throw new ContentNotFoundException("Not Available");
        }
        logger.warn("All Tag data is   --> {} : " , tag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<?> getDis() {
        List<Category> tag = subCategoryService.getCategoriesDis();
        if (tag == null) {
            logger.warn("ContentNotFoundException Occurs --> {} : " , tag);
            throw new ContentNotFoundException("Not Available");
        }
        logger.warn("All Category data is   --> {} : " , tag);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}
