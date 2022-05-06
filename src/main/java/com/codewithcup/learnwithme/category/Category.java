package com.codewithcup.learnwithme.category;

import com.codewithcup.learnwithme.subcategory.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

//import net.minidev.json.annotate.JsonIgnore;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@ToString
@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String categoryName;
    @Column(length = 5000)
    private String categoryDescription;
    private Boolean isPublished;
//    One Category contains multiple SubCategory
    @OneToMany(mappedBy="category",cascade =  CascadeType.ALL)
    @JsonIgnore
    private Set<SubCategory> subCategory = new LinkedHashSet<>();

    public Category() {
    }

    public Category(Long categoryId, String categoryName, String categoryDescription, Boolean isPublished, Set<SubCategory> subCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.isPublished = isPublished;
        this.subCategory = subCategory;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Set<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Set<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

}
