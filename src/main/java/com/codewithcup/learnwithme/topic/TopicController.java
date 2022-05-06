package com.codewithcup.learnwithme.topic;

import com.codewithcup.learnwithme.content.Content;
import com.codewithcup.learnwithme.content.ContentController;
import com.codewithcup.learnwithme.exceptions.ContentNotFoundException;
import com.codewithcup.learnwithme.exceptions.ResourceNotFoundException;
import com.codewithcup.learnwithme.subcategory.SubCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("topic")
@CrossOrigin("*")
public class TopicController {
    private final Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;
    //add topic by subcategory
    @PostMapping("/add-topic")
    public ResponseEntity<?> addTopicToSave(@RequestBody Topic topic){
        Topic topicadd = topicService.addTopic(topic);
        if (topicadd == null) {
            logger.info("DataNotSavedException Occurs {} ", topicadd);
            throw new RuntimeException("Data not saved");
        }
        logger.info("Topic Added --> {} : ", topicadd.getTopicId());
        return ResponseEntity.status(HttpStatus.OK).body(topicadd);
    }

    @PutMapping("/update")
    public ResponseEntity<?> uUpdateTopic(@RequestBody Topic topic) {
        Topic utopic = topicService.updateTopic(topic);
        if (utopic == null) {
            logger.info("DataNotSavedException Occurs {} ", utopic);
            throw new RuntimeException("Data not saved");
        }
        logger.info("Content Updated --> {} : ", topic.getTopicId());
        return ResponseEntity.status(HttpStatus.OK).body(utopic);
    }

    @GetMapping("/all-topic")
    public ResponseEntity<?> gGetAllTopic() {
        Set<Topic> gTopicList = topicService.getTopic();
        if (gTopicList.isEmpty()) {
            logger.info("data not found  --> {} : " , gTopicList);
            throw new ContentNotFoundException("data not found");
        }
        logger.info("data found with  --> {} : " , gTopicList);
        return ResponseEntity.status(HttpStatus.OK).body(gTopicList);
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<?> gGetTopicById(@PathVariable Long topicId) {
        Topic gTopicById = topicService.getByIdTopic(topicId);
        if (gTopicById == null) {
            logger.info("data not found with id  --> {} : ", topicId);
            throw new ResourceNotFoundException("data not found with id {}" + topicId);
        }
        logger.info("data found with id  --> {} : ", topicId);
        return ResponseEntity.status(HttpStatus.OK).body(gTopicById);
    }

    @GetMapping("/subcategory/{scatId}")
    public ResponseEntity<?> rRetrivedSpecificSubCategoriesData(@PathVariable Long scatId) {
        SubCategory subCategory = new SubCategory();
        subCategory.setScatId(scatId);
        Set<Topic> topicOfSubCategories = topicService.getTopicBySubCategory(subCategory);
        if (topicOfSubCategories == null) {
            logger.warn("data not found with this id  --> {} : " , scatId);
            throw new ContentNotFoundException("Not Available");
        }
        logger.info("Data found with id  --> {} : " , scatId);
        return new ResponseEntity<>(topicOfSubCategories, HttpStatus.OK);
    }

}
