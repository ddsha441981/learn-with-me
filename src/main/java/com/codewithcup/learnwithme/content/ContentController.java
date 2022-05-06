package com.codewithcup.learnwithme.content;

import com.codewithcup.learnwithme.configuration.CurrentDateUtility;
import com.codewithcup.learnwithme.exceptions.CategoriesNotFoundException;
import com.codewithcup.learnwithme.exceptions.ContentNotFoundException;
import com.codewithcup.learnwithme.exceptions.ResourceNotFoundException;
import com.codewithcup.learnwithme.subcategory.SubCategory;
import com.codewithcup.learnwithme.topic.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/content")
public class ContentController {
    private final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @Autowired
    private ContentService contentService;

    @PostMapping("/")
    public ResponseEntity<Content> sAddContent(@RequestBody Content content) {
        CurrentDateUtility dateUtility =  new CurrentDateUtility();
        content.setCreatedDate(dateUtility.currentDate());
        Content aContent = contentService.addContent(content);
        System.out.println(aContent);
        if (aContent == null) {
            logger.info("DataNotSavedException Occurs {} ", aContent);
            throw new RuntimeException("Data not saved");
        }
        logger.info("Content Added --> {} : ", aContent.getContentId());
        return ResponseEntity.status(HttpStatus.OK).body(aContent);
    }

    @PutMapping("/update")
    public ResponseEntity<?> uUpdateContent(@RequestBody Content content) {
        Content uContent = contentService.updateContent(content);
        if (uContent == null) {
            logger.info("DataNotSavedException Occurs {} ", uContent);
            throw new RuntimeException("Data not saved");
        }
        logger.info("Content Added --> {} : ", uContent.getContentId());
        return ResponseEntity.status(HttpStatus.OK).body(uContent);
    }

    @GetMapping("/all")
    public ResponseEntity<?> gGetAllContent() {
        Set<Content> gContentsList = contentService.getContents();
        if (gContentsList.isEmpty()) {
            logger.info("data not found  --> {} : " , gContentsList);
            throw new ContentNotFoundException("data not found");
        }
        logger.info("data found with  --> {} : " , gContentsList);
        return ResponseEntity.status(HttpStatus.OK).body(gContentsList);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<?> gGetContentById(@PathVariable Long contentId) {
        Content gContentsById = contentService.getContent(contentId);
        if (gContentsById == null) {
            logger.info("data not found with id  --> {} : ", contentId);
            throw new ResourceNotFoundException("data not found with id {}" + contentId);
        }
        logger.info("data found with id  --> {} : ", contentId);
        return ResponseEntity.status(HttpStatus.OK).body(gContentsById);
    }

    @DeleteMapping("/delete/{contentId}")
    public ResponseEntity<?> dDeleteSubCategory(@PathVariable Long contentId) {
        Content dContent = contentService.deleteContent(contentId);
        if (dContent != null) {
            logger.info("data deleted with id --> :  {}", contentId);
            return new ResponseEntity<>("Data deleted Successfully", HttpStatus.OK);
        }
        logger.info("data not deleted with id --> :  {}", contentId);
        throw new RuntimeException("data not deleted");
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Content> gContentById(@PathVariable Long contentId) {
        Content dContent = new Content();
        dContent.setContentId(contentId);
        Content gContent = contentService.getContentById(dContent);
        if (gContent == null) {
            logger.info("data not found with id  --> {} : ", contentId);
            throw new ResourceNotFoundException("data not found with id {}" + contentId);
        }
        logger.info("data found with id  --> {} : ", contentId);
        return ResponseEntity.status(HttpStatus.OK).body(gContent);
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<?> rRetrivedSpecificTopicData(@PathVariable Long topicId) {
        Topic topic = new Topic();
        topic.setTopicId(topicId);
        Set<Content> contentOfTopics = contentService.getContentsOfTopic(topic);
        if (contentOfTopics == null) {
            logger.warn("data not found with this id  --> {} : " , topicId);
            throw new ContentNotFoundException("Not Available");
        }
        logger.info("Data found with id  --> {} : " , topicId);
        return new ResponseEntity<>(contentOfTopics, HttpStatus.OK);
    }

    @GetMapping("/latest-post")
    public ResponseEntity<?> getLatestPostFromDB(){
        Set<Content> latestPost = contentService.getLatestContentPost();
        if(latestPost.isEmpty()) {
            logger.info("data not found  --> {} : ", latestPost);
            throw new ContentNotFoundException("data not found");
        }
        logger.info("data found with  --> {} : " , latestPost);
        return ResponseEntity.status(HttpStatus.OK).body(latestPost);
    }

    @GetMapping("/recent-post")
    public ResponseEntity<?> getRecentPostFromDB(){
        Set<Content> recentPost = contentService.getRecentContentPost();
        if(recentPost.isEmpty()) {
            logger.info("data not found  --> {} : ", recentPost);
            throw new ContentNotFoundException("data not found");
        }
        logger.info("data found with  --> {} : " , recentPost);
        return ResponseEntity.status(HttpStatus.OK).body(recentPost);
    }

}
