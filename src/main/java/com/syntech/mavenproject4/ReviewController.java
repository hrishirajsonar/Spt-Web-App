/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import com.syntech.practice.StoryController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class ReviewController implements Serializable {

    private Review review;

    private Story story;

    private List<Review> reviewList;

    public Review getReview() {
        return review;
    }

//    public void addReview() {
//        
//        reviewList.add(review);
//    }
    
    @Inject
    StoryController storyController;
    
    public void saveStoryAndReview() {

//        story = sr.findById(274);
        System.out.println(storyController.getStory().getDeveloper());
        review.setStory(storyController.getStory());

        System.out.println(review.getReviewId());
        System.out.println(review.getStory().getPoints());

        story.getReviews().add(review);

        rr.persistMultipleReviews((List<Review>) story.getReviews());
    }

    public void setReview(Review review1) {
        this.review = review1;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Inject
    private ReviewRepository rr;

    @Inject
    private StoryRepository sr;

    @PostConstruct
    public void init() {
        review = new Review();

//        reviewList = new ArrayList<>();
    }

    public void persistReview() {
        rr.persistReview(review);
        review = new Review();
    }

    public void persistTwoReviews() {

        rr.persistMultipleReviews(reviewList);

        reviewList = new ArrayList<>();
    }

//    public void persistThreeReviews() {
//        reviewList.add(review1);
//        reviewList.add(review2);
//        reviewList.add(review3);
//        rr.persistMultipleReviews(reviewList);
//        
//        review1 = new Review();
//        review2 = new Review();
//        review3 = new Review();
//        reviewList = new ArrayList<>();
//    }
}
