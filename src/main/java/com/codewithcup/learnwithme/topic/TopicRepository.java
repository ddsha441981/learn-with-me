package com.codewithcup.learnwithme.topic;

import com.codewithcup.learnwithme.subcategory.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
    Set<Topic> findBySubcategory(SubCategory subCategory);
}
