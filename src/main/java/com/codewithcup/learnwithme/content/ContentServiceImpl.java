package com.codewithcup.learnwithme.content;

import com.codewithcup.learnwithme.exceptions.ContentNotFoundException;
import com.codewithcup.learnwithme.subcategory.SubCategory;
import com.codewithcup.learnwithme.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Content addContent(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public Content updateContent(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public Set<Content> getContents() {
        return new LinkedHashSet<>(contentRepository.findAll());
    }

    @Override
    public Content getContent(Long contentId) {
        Content contentUsingId = contentRepository.findById(contentId).orElse(null);
        if(contentUsingId == null)
            throw new ContentNotFoundException("Content not available");
        return contentUsingId;
    }

    @Override
    public Set<Content> getContentsOfTopic(Topic topic) {
        List<Content> byTopic = contentRepository.findByTopic(topic);
        return new LinkedHashSet(byTopic);
    }

    @Override
    public Content deleteContent(Long contentId) {
        Content content = new Content();
        content.setContentId(contentId);
        contentRepository.deleteById(contentId);
        return content;
    }

    @Override
    public Content getContentById(Content content) {
        Content contentUsingId = contentRepository.findByContentId(content);
        if(contentUsingId == null)
            throw new ContentNotFoundException("Content not available");
        return contentUsingId;
    }

//     1 to 10
    @Override
    public Set<Content> getLatestContentPost() {
        return new LinkedHashSet(this.contentRepository.findByLatestPost());
    }
// 11 to 20
    @Override
    public Set<Content> getRecentContentPost() {
        return new LinkedHashSet(this.contentRepository.findByRecentPost());
    }
}
