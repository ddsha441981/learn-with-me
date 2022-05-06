package com.codewithcup.learnwithme.content;

import com.codewithcup.learnwithme.subcategory.SubCategory;
import com.codewithcup.learnwithme.topic.Topic;

import java.util.Set;

public interface ContentService {
    public Content addContent(Content content);
    public Content updateContent(Content content);
    public Set<Content> getContents();
    public Content getContent(Long contentId);
    public Set<Content> getContentsOfTopic(Topic topic);
    public Content deleteContent(Long contentId);

    //Business Logic
    public Content getContentById(Content content);
    //get latest data from database 1 to 10
    public Set<Content> getLatestContentPost();
    //get recent data from darabase 11 to 20
    public Set<Content> getRecentContentPost();
}
