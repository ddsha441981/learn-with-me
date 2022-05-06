package com.codewithcup.learnwithme.content;

import com.codewithcup.learnwithme.topic.Topic;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="seq",initialValue = 1,allocationSize = 1000)
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="seq")
    private Long contentId;
    private String title;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String contentDescription;
    private String createdDate;
    private String autherName;
    private Boolean isPublished;

    //Many Content contains only one SubCategory
    @ManyToOne(fetch = FetchType.EAGER)
    private Topic topic;

    public Content() {
    }


    public Content(Long contentId, String title, String contentDescription, String createdDate, String autherName, Boolean isPublished, Topic topic) {
        this.contentId = contentId;
        this.title = title;
        this.contentDescription = contentDescription;
        this.createdDate = createdDate;
        this.autherName = autherName;
        this.isPublished = isPublished;
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

}
