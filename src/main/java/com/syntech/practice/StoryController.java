/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice;

import com.syntech.mavenproject4.StoryRepository;
import com.syntech.mavenproject4.Story;
import java.io.Serializable;
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
public class StoryController implements Serializable {

    private Story story;
//    private List<Story> storyList;

    private String result;

    @Inject
    StoryRepository sr;

    @PostConstruct
    public void init() {
        story = new Story();
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public StoryRepository getSr() {
        return sr;
    }

    public void setSr(StoryRepository sr) {
        this.sr = sr;
    }

    public void submit() {
        result = "Reviewer:" + story.getReviewer();
        System.out.println(result);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void check() {

        Story story = new Story(100L, "Started", 2, "Ram", "Ramesh", "Rabindra");
//        Story story1 = new Story();
//       story1.setId(1L);
//        story1.setStatus("Started");
//        story1.setPoints(2);
//        story1.setDeveloper("Tilak");
//        story1.setRequestor("Ramesh");
//        story1.setReviewer("Neelu");
        sr.persistStory(story);
//        story = new Story();
    }
    
//    public void checkFind(){
//        Story sty = sr.find(100);
//    }

//    public void findAndRemoveById() {
//        story = sr.getEntityManager().find(Story.class, 1);
//        
////        System.out.println("after find(): " + em.contains(employee));
//        sr.getEntityManager().remove(story);
////        System.out.println("after remove(): " + em.contains(employee));
//        sr.getEntityManager().flush();
//    }
}
