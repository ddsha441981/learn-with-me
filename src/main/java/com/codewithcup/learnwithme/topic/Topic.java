package com.codewithcup.learnwithme.topic;

import com.codewithcup.learnwithme.content.Content;
import com.codewithcup.learnwithme.subcategory.SubCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "topic")
public class Topic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long topicId;
    private String topicName;


    //Many Content contains only one SubCategory
    @ManyToOne(fetch = FetchType.EAGER)
    private SubCategory subcategory;

    @OneToMany(mappedBy = "topic",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Content> content = new LinkedHashSet<>();

    public Topic() {
    }

    public Topic(Long topicId, String topicName, SubCategory subcategory, Set<Content> content) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.subcategory = subcategory;
        this.content = content;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public SubCategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategory subcategory) {
        this.subcategory = subcategory;
    }

    public Set<Content> getContent() {
        return content;
    }

    public void setContent(Set<Content> content) {
        this.content = content;
    }

}
