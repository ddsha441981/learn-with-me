package com.codewithcup.learnwithme.topic;

import com.codewithcup.learnwithme.subcategory.SubCategory;

import java.util.Set;

public interface TopicService {

    //add topic by using subcategory
    public Topic addTopic(Topic topic);
    public Topic updateTopic(Topic topic);
    public Set<Topic> getTopic();
    public Topic getByIdTopic(Long topicId);
    public Topic deleteTopic(Long topicId);

    //Business Method
    public Set<Topic> getTopicBySubCategory(SubCategory subCategory);

}
