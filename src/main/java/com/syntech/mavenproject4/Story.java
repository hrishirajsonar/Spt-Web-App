package com.syntech.mavenproject4;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hrishi
 */
@Entity
@Table(name = "story")
public class Story implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "storyName", nullable = false)
    private String storyName;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "points", nullable = false)
    private int points;

    @Column(name = "storyType", nullable = false)
    private String storyType;

    @Column(name = "requestor", nullable = false)
    private String requestor;

    @Column(name = "developer", nullable = false)
    private String developer;

//    @Column(name = "reviewer", nullable = false)
//    private String reviewer;
    @Column(name = "deadline", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @Column(name = "description", nullable = true)
    private String description;

//    @JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "story")
    private List<Review> reviews = new ArrayList<>();

    private int reviewCount;
    private int reviewedCount;
    private String currentReviewer;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "story")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "story")
//    @JsonIgnore
    @JsonManagedReference
    private List<Activity> activities;

    public Story() {
    }

    public Story(Long id, String status, int points, String requestor, String developer, String reviewer, String storyName, Date deadline, String description, List<Review> reviews, List<Activity> activities) {

//        this.id = id;
        this.status = status;
        this.points = points;
        this.requestor = requestor;
        this.developer = developer;
//        this.reviewer = reviewer;
        this.storyName = storyName;
        this.deadline = deadline;
        this.description = description;
        this.reviews = reviews;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoryType() {
        return storyType;
    }

    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

//    public String getReviewer() {
//        return reviewer;
//    }
//
//    public void setReviewer(String reviewer) {
//        this.reviewer = reviewer;
//    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getReviewedCount() {
        return reviewedCount;
    }

    public void setReviewedCount(int reviewedCount) {
        this.reviewedCount = reviewedCount;
    }

    public String getCurrentReviewer() {
        return currentReviewer;
    }

    public void setCurrentReviewer(String currentReviewer) {
        this.currentReviewer = currentReviewer;
    }

//    public void addReviews(Review review) {
//        if (this.reviews == null || this.reviews.isEmpty()) {
//            this.reviews = new ArrayList<>();
//        }
//        this.reviews.add(review);
//    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.points);
        hash = 97 * hash + Objects.hashCode(this.requestor);
        hash = 97 * hash + Objects.hashCode(this.developer);
//        hash = 97 * hash + Objects.hashCode(this.reviewer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Story other = (Story) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.points, other.points)) {
            return false;
        }
        if (!Objects.equals(this.requestor, other.requestor)) {
            return false;
        }
        if (!Objects.equals(this.developer, other.developer)) {
            return false;
        }
//        if (!Objects.equals(this.reviewer, other.reviewer)) {
//            return false;
//        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Story{" + " status=" + status + ", points=" + points + ", requestor=" + requestor + ", developer=" + developer + '}';
    }

}
