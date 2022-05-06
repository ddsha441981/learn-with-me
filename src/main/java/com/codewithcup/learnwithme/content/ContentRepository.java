package com.codewithcup.learnwithme.content;

import com.codewithcup.learnwithme.subcategory.SubCategory;
import com.codewithcup.learnwithme.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content ,Long> {

    List<Content> findByTopic(Topic topic);
    @Query(value = "select * from content where content_id = 101" ,nativeQuery = true)
    public Content findByContentId(Content content);

    @Query(value = "select * from content order by content_id desc limit 10",nativeQuery = true)
    List<Content> findByLatestPost();
    @Query(value = "select * from content order by content_id asc limit 10",nativeQuery = true)
    List<Content> findByRecentPost();
}
