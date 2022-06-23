/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import com.syntech.mavenproject4.Story;
import com.syntech.mavenproject4.Story2Controller;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author hrishi
 */
@Stateless
public class StoryRepository {

    @PersistenceContext(unitName = "sptDS")
    private EntityManager em;

    public void StoryRepository() {
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public void persistStory(Story story) {
        em.persist(story);
        em.flush();
    }
    
    public void edit(Story story) {
        em.merge(story);
        em.flush();
    }

    public Story findById(long id) {
        Story storyFound = (Story) em.find(Story.class, id);
        return storyFound;
    }

    public void delete(long id) {
        getEntityManager().remove(findById(id));
        getEntityManager().flush();
    }

    public List<Story> findAll() {
        return em.createQuery("SELECT a FROM Story a", Story.class).getResultList();
    }

    public List<Story> findAllByRequestor(String username) {
        Query query = em.createQuery("Select a from Story a where a.requestor = :u", Story.class);
        query.setParameter("u", username);
        List<Story> storyList = query.getResultList();
        return storyList;
    }

    public List<Story> findAllByDeveloper(String username) {
        Query query = em.createQuery("Select a from Story a where a.developer = :u", Story.class);
        query.setParameter("u", username);
        List<Story> storyList = query.getResultList();
        return storyList;
    }
    
//    public List<Story> findAllByReviewer(String username) {
//        Query query = em.createQuery("Select a from Story a where a.reviewer = :u", Story.class);
//        query.setParameter("u", username);
//        List<Story> storyList = query.getResultList();
//        return storyList;
//    }
    
    public List<Story> findAllByReviewerList(String username) {
        
       Query query =  em.createQuery("Select s from Story s where exists ( select 1 from Review r where r.story = s and r.reviewer = :name)", Story.class);
        
        query.setParameter("name", username);
        List<Story> storyList = query.getResultList();
        return storyList;
    }
    
    public List<Story> findAllCompleted(String username) {
        Query query = em.createQuery("Select a from Story a where a.reviewer = :u and a.status = :v", Story.class);
        query.setParameter("u", username);
        query.setParameter("v", "Completed");
        List<Story> storyList = query.getResultList();
        return storyList;
    }
    
//    public void delete(Story story) {
//        System.out.println("ID is ");
//        getEntityManager().remove(findById(story.getPoints()));
//        getEntityManager().flush();
//    }
//    public List<Story> findAll() {
//        return getEntityManager().createQuery("Select t from " + getEntityClass().class + " t").getResultList();
//    }
//    public Story findByName(String name) {
//        Query query = em.createQuery("SELECT s FROM Story s WHERE s.name=:name", Story.class);
//        query.setParameter("name", name);
//        Story story = (Story) query.getSingleResult();
//        
//        System.out.println("Hello" + story);
//        return story;
//    }
}
