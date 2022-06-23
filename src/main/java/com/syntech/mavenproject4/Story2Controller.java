/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.syntech.util.MessageUtil;
import java.util.Date;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.TransactionRequiredException;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class Story2Controller implements Serializable {

    private Story story;

    private Review review;

//    private Long idToDelete;
    private Long idToView;

    private List<Story> storyList;

    public Story2Controller() {
    }

    private List<Story> requestorStoryList;

    private List<Story> developerStoryList;

    private List<Story> reviewerStoryList;

    @Inject
    private MessageUtil messageUtil;

    @Inject
    StoryRepository sr;

    @Inject
    ActivityRepository ar;

    @Inject
    EmployeeController employeeController;

    @Inject
    ReviewController reviewController;

    @Inject
    ActivityController activityController;

    @PostConstruct
    public void init() {
        story = new Story();
        review = new Review();
        storyList = new ArrayList<>(sr.findAll());
        requestorStoryList = new ArrayList<>(sr.findAllByRequestor(employeeController.getLoggedInUser().getUsername()));
        developerStoryList = new ArrayList<>(sr.findAllByDeveloper(employeeController.getLoggedInUser().getUsername()));
        reviewerStoryList = new ArrayList<>(sr.findAllByReviewerList(employeeController.getLoggedInUser().getUsername()));
    }

    public Date getToday() {
        return new Date();
    }

    public String find(List<Review> reviews) {
        for (Review r : story.getReviews()) {
            if (r.getReviewer().equals(employeeController.getLoggedInUser().getEmployeeName())) {
                return r.getReviewer();
            }
        }
        return null;
    }

    public Story getStory() {
        return story;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public List<Story> getStoryList() {
        return storyList;
    }

    public void setStoryList(List<Story> storyList) {
        this.storyList = storyList;
    }

    public List<Story> getRequestorStoryList() {
        return requestorStoryList;
    }

    public void setRequestorStoryList(List<Story> requestorStoryList) {
        this.requestorStoryList = requestorStoryList;
    }

    public List<Story> getDeveloperStoryList() {
        return developerStoryList;
    }

    public void setDeveloperStoryList(List<Story> developerStoryList) {
        this.developerStoryList = developerStoryList;
    }

    public List<Story> getReviewerStoryList() {
        return reviewerStoryList;
    }

    public void setReviewerStoryList(List<Story> reviewerStoryList) {
        this.reviewerStoryList = reviewerStoryList;
    }

    public Long getIdToView() {
        return idToView;
    }

    public void setIdToView(Long idToView) {
        this.idToView = idToView;
    }

    public void addToList() {
        review.setStory(story);
        story.getReviews().add(review);
//        story.addReviews(review);
        this.review = new Review();
        story.setReviewCount(story.getReviewCount() + 1);

    }

    public void beforeAddReview() {
        review = null;
        review = new Review();
        review.setStory(story);
    }

    public List<String> reviewerNames1() {

        List<String> name = new ArrayList<>();
        for (Review r : story.getReviews()) {
            System.out.println(r.getReviewer());
            name.add(r.getReviewer());
        }

        return name;
    }

    public List<String> reviewerNames(Story story) {

        List<String> name = new ArrayList<>();
        for (Review r : story.getReviews()) {
            System.out.println(r.getReviewer());
            name.add(r.getReviewer());
        }

        return name;
    }

    public void saveStoryAndReview() {

//        story = sr.findById(274);
        story.setReviewedCount(0);
        story.setStatus("Unstarted");
        story.setRequestor(employeeController.getLoggedInUser().getUsername());
        sr.persistStory(story);
//        reviewController.getReview().setStory(story);
//        
//        System.out.println(reviewController.getReview().getReviewId());
//        System.out.println(reviewController.getReview().getStory().getPoints());
//
//        story.getReviews().add(reviewController.getReview());
//
//        rr.persistMultipleReviews((List<Review>) story.getReviews());

    }

    @Inject
    private ReviewRepository rr;

    public void persistStory() {
        Activity a = new Activity();
        a.setActivityDateTime(new Date());
        a.setEmployee(employeeController.getLoggedInUser().getUsername());
        a.setStory(story);

        story.setStatus("Unstarted");
        a.setType("Story Created");
        story.setRequestor(employeeController.getLoggedInUser().getUsername());

        sr.persistStory(story);
        ar.persistActivity(a);
        requestorStoryList = sr.findAllByRequestor((employeeController.getLoggedInUser().getUsername()));
        story = new Story();
        messageUtil.showInfo("Story Created Successfully!");
    }

    public void editStory() {
//        story.setStatus("Unstarted");
//        story.setRequestor(employeeController.getLoggedInUser().getUsername());
        sr.edit(story);
        requestorStoryList = sr.findAllByRequestor((employeeController.getLoggedInUser().getUsername()));
        story = new Story();
        messageUtil.showInfo("Story Edited Successfully!");
    }

    public void editStatus() {

        sr.edit(story);
        developerStoryList = sr.findAllByDeveloper((employeeController.getLoggedInUser().getUsername()));
        messageUtil.showInfo("Status Changed Successfully!");
    }

    public void beforeAddStory() {
        story = new Story();
        story.setReviews(new ArrayList<>());
    }

    public void beforeEdit(Story st) {
        story = sr.findById(st.getId());
    }

    public void editStatusDev(Story s) {

        Activity a = new Activity();
        a.setActivityDateTime(new Date());
        a.setEmployee(employeeController.getLoggedInUser().getUsername());
        a.setStory(s);

        story = sr.findById(s.getId());
        String status = s.getStatus();
        if (status.equals("Unstarted")) {
            story.setStatus("Started");
            a.setType("Story Started");
        } else if (status.equals("Started")) {
            story.setStatus("Finished");
            a.setType("Story Finished");
        } else if (status.equals("Finished")) {
            story.setStatus("Delivered");
            a.setType("Story Delivered");
        }
        ar.persistActivity(a);

        sr.edit(story);
        developerStoryList = sr.findAllByDeveloper((employeeController.getLoggedInUser().getUsername()));
    }

    public Boolean checkReviewer(Story s) {
        story = sr.findById(s.getId());
        int a = story.getReviewedCount();
        Review r = story.getReviews().get(a);
        return r.getReviewer().equals(employeeController.getLoggedInUser().getUsername());
    }

    public void acceptStory(Story story) {

//        story = sr.findById(s.getId());
//        Review r1 = s.getReviews().get(story.getReviewedCount());
//        story.setCurrentReviewer(r1.getReviewer());
//        System.out.println(story.getCurrentReviewer());
        Activity a = new Activity();
        a.setActivityDateTime(new Date());
        a.setEmployee(employeeController.getLoggedInUser().getUsername());
        a.setStory(story);

        story.setReviewedCount(story.getReviewedCount() + 1);

        if (story.getReviewedCount() == story.getReviewCount()) {

            story.setStatus("Accepted");
            a.setType("Story Accepted");
            story.setReviewedCount(0);
        } else {
//            story.setReviewedCount(story.getReviewedCount()+1);
            story.setStatus("Review " + story.getReviewedCount() + " Completed");
            a.setType("Review " + story.getReviewedCount() + " Completed");
        }

        sr.edit(story);
        ar.persistActivity(a);
        reviewerStoryList = sr.findAllByReviewerList((employeeController.getLoggedInUser().getUsername()));
    }

    public void rejectStory(Story s) {
        Activity a = new Activity();
        a.setActivityDateTime(new Date());
        a.setEmployee(employeeController.getLoggedInUser().getUsername());
        a.setStory(s);

        story = sr.findById(s.getId());
        story.setStatus("Unstarted");
        a.setType("Story Rejected");

        sr.edit(story);
        ar.persistActivity(a);
        reviewerStoryList = sr.findAllByReviewerList((employeeController.getLoggedInUser().getUsername()));
    }

    public boolean renderButtonRev(Story s) {

        String status = s.getStatus();

        int a = s.getReviewedCount();
        Review r = s.getReviews().get(a);
//        r.getReviewer().equals(employeeController.getLoggedInUser().getUsername());

        if ((status.equals("Delivered") || Pattern.matches("Review . Completed", status) || status.equals("Being Reviewed")) && (r.getReviewer().equals(employeeController.getLoggedInUser().getUsername()))) {
            return true;
        }
        return false;
    }

    public String editButtonDev(Story s) {

        String status = s.getStatus();

        if (status.equals("Unstarted")) {
            return "Start";
        } else if (status.equals("Started")) {
            return "Finish";
        } else if (status.equals("Finished")) {
            return "Deliver";
        } else if (status.equals("Delivered")) {
            return "Deliver";
        }
        return "Accepted";
    }

    public boolean renderButtonDev(Story s) {

        String status = s.getStatus();

        if (status.equals("Delivered") || status.equals("Accepted") || status.equals("Being Reviewed")) {
            return false;
        }
        return true;
    }

    public String setStatusColor() {
//        String status = s.getStatus();

        if (story.getStatus().equals("Accepted")) {
            return "lightgreen";
        }
        if (story.getStatus().equals("Unstarted")) {
            return "darkgray";
        }
        if (story.getStatus().equals("Started")) {
            return "lightpink";
        }
        if (story.getStatus().equals("Finished")) {
            return "lightskyblue";
        }
        if (story.getStatus().equals("Delivered")) {
            return "sandybrown";
        }
        return null;
    }

    public String setStatusColor(Story s) {
        String status = s.getStatus();

        if (status.equals("Accepted")) {
            return "lightgreen";
        }
        if (status.equals("Unstarted")) {
            return "darkgray";
        }
        if (status.equals("Started")) {
            return "lightpink";
        }
        if (status.equals("Finished")) {
            return "lightskyblue";
        }
        if (status.equals("Delivered")) {
            return "sandybrown";
        }
        return null;
    }

    public int byMeOverdueSize() {
        int overdue = 0;
        for (Story s : requestorStoryList) {
            System.out.println(s.getDeadline());
            if (s.getDeadline().before(new Date())) {
                overdue++;
                System.out.println(overdue);
            }
        }
        return overdue;
    }

    public int byMeUnstartedSize() {
        int unstarted = 0;
        for (Story s : requestorStoryList) {
            if (s.getStatus().equals("Unstarted")) {
                unstarted++;
            }
        }
        return unstarted;
    }

    public int byMeStartedSize() {
        int started = 0;
        for (Story s : requestorStoryList) {
            if (s.getStatus().equals("Started")) {
                started++;
            }
        }
        return started;
    }

    public int byMeFinishedSize() {
        int finished = 0;
        for (Story s : requestorStoryList) {
            if (s.getStatus().equals("Finished")) {
                finished++;
            }
        }
        return finished;
    }

    public int byMeDeliveredSize() {
        int delivered = 0;
        for (Story s : requestorStoryList) {
            if (s.getStatus().equals("Delivered")) {
                delivered++;
            }
        }
        return delivered;
    }

    public int byMeAcceptedSize() {
        int accepted = 0;
        for (Story s : requestorStoryList) {
            if (s.getStatus().equals("Accepted")) {
                accepted++;
            }
        }
        return accepted;
    }

    public int forMeDevOverdueSize() {
        int overdue = 0;
        for (Story s : developerStoryList) {
            if (s.getDeadline().before(new Date()) && (!s.getStatus().equals("Accepted") && !s.getStatus().equals("Delivered"))) {
                overdue++;
            }
        }
        return overdue;
    }

    public int forMeDevUnstartedSize() {
        int unstarted = 0;
        for (Story s : developerStoryList) {
            if (s.getStatus().equals("Unstarted")) {
                unstarted++;
            }
        }
        return unstarted;
    }

    public int forMeDevStartedSize() {
        int started = 0;
        for (Story s : developerStoryList) {
            if (s.getStatus().equals("Started")) {
                started++;
            }
        }
        return started;
    }

    public int forMeDevFinishedSize() {
        int finished = 0;
        for (Story s : developerStoryList) {
            if (s.getStatus().equals("Finished")) {
                finished++;
            }
        }
        return finished;
    }

    public int forMeDevDeliveredSize() {
        int delivered = 0;
        for (Story s : developerStoryList) {
            if (s.getStatus().equals("Delivered")) {
                delivered++;
            }
        }
        return delivered;
    }

    public int forMeDevAcceptedSize() {
        int accepted = 0;
        for (Story s : developerStoryList) {
            if (s.getStatus().equals("Accepted")) {
                accepted++;
            }
        }
        return accepted;
    }

    public int forMeRevOverdueSize() {
        int overdue = 0;
        for (Story s : reviewerStoryList) {
            if (s.getDeadline().before(new Date())) {
                overdue++;
            }
        }
        return overdue;
    }

    public int forMeRevUnstartedSize() {
        int unstarted = 0;
        for (Story s : reviewerStoryList) {
            if (s.getStatus().equals("Unstarted")) {
                unstarted++;
            }
        }
        return unstarted;
    }

    public int forMeRevStartedSize() {
        int started = 0;
        for (Story s : reviewerStoryList) {
            if (s.getStatus().equals("Started")) {
                started++;
            }
        }
        return started;
    }

    public int forMeRevFinishedSize() {
        int finished = 0;
        for (Story s : reviewerStoryList) {
            if (s.getStatus().equals("Finished")) {
                finished++;
            }
        }
        return finished;
    }

    public int forMeRevDeliveredSize() {
        int delivered = 0;
        for (Story s : reviewerStoryList) {
            if (s.getStatus().equals("Delivered")) {
                delivered++;
            }
        }
        return delivered;
    }

    public int forMeRevAcceptedSize() {
        int accepted = 0;
        for (Story s : reviewerStoryList) {
            if (s.getStatus().equals("Accepted")) {
                accepted++;
            }
        }
        return accepted;
    }

    public void deleteStory(Long idToDelete) {

//        try {
//        story = sr.findById(idToDelete);
//        Activity a = new Activity();
//        a.setActivityDateTime(new Date());
//        a.setEmployee(employeeController.getLoggedInUser().getUsername());
//        a.setStory(story);
//        a.setType("Story Deleted");
//        ar.persistActivity(a);
//            fail("Should have thrown TransactionRequiredException");
//        } catch (TransactionRequiredException ex) {
//            // expected
//        }

        sr.delete(idToDelete);

        requestorStoryList = sr.findAllByRequestor((employeeController.getLoggedInUser().getUsername()));
        messageUtil.showInfo("Story Deleted Successfully!");
    }

    public void viewStory() {
        story = sr.findById(idToView);
    }

    public void onRowSelect(SelectEvent<Story> event) {
        FacesMessage msg = new FacesMessage("Story Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
