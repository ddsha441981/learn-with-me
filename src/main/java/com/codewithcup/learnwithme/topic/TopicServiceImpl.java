package com.codewithcup.learnwithme.topic;

import com.codewithcup.learnwithme.subcategory.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public Topic addTopic(Topic topic) {
        Topic saveTopic = topicRepository.save(topic);
        return saveTopic;
    }

    @Override
    public Topic updateTopic(Topic topic) {
        Topic updateTopic = topicRepository.save(topic);
        return updateTopic;
    }

    @Override
    public Set<Topic> getTopic() {
        return new LinkedHashSet(topicRepository.findAll());
    }

    @Override
    public Topic getByIdTopic(Long topicId) {
        Topic topicById = topicRepository.findById(topicId).orElse(null);
        return topicById;
    }

    @Override
    public Topic deleteTopic(Long topicId) {
        return null;
    }

    @Override
    public Set<Topic> getTopicBySubCategory(SubCategory subCategory) {
        return new LinkedHashSet<>(topicRepository.findBySubcategory(subCategory));
    }
}
