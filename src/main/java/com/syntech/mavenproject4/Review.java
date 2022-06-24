/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hrishi
 */
@Entity
@Table(name = "review")
public class Review implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long reviewId;

//    @JsonIgnore
    
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @Column(name = "reviewer", nullable = false)
    private String reviewer;

    @Column(name = "reviewComment", nullable = false)
    private String reviewComment;

    public Review() {
    }

    public Review(Long reviewId, String reviewer, String reviewName, String reviewDescription) {
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.reviewComment = reviewDescription;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

}
