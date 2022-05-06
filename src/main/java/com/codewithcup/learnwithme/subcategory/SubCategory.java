package com.codewithcup.learnwithme.subcategory;

import com.codewithcup.learnwithme.category.Category;
import com.codewithcup.learnwithme.content.Content;
import com.codewithcup.learnwithme.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@ToString
@Entity
@Table(name="subcategory")
public class SubCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scatId;
    private String subName;
    private Boolean isPublished;
//    Many Sub Category contains one category
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "subcategory",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Topic> topic = new LinkedHashSet<>();
    //private Set<Content> content =  new LinkedHashSet<>();

    public SubCategory() {
    }

    public Set<Topic> getTopic() {
        return topic;
    }

    public void setTopic(Set<Topic> topic) {
        this.topic = topic;
    }

    public SubCategory(Long scatId, String subName, Boolean isPublished, Category category, Set<Topic> topic) {
        this.scatId = scatId;
        this.subName = subName;
        this.isPublished = isPublished;
        this.category = category;
        this.topic = topic;


    }

    public Long getScatId() {
        return scatId;
    }

    public void setScatId(Long scatId) {
        this.scatId = scatId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

