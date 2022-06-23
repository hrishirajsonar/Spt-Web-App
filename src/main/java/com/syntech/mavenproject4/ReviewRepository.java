/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hrishi
 */
@Stateless
public class ReviewRepository {
    
    @PersistenceContext(unitName = "reviewUnit")
    private EntityManager em; 

    public ReviewRepository() {
    }

    public EntityManager getEntityManager() {
        return em;
    }
    
    public void persistReview(Review review) {
        em.merge(review);
        em.flush();
    }
    
    public void persistMultipleReviews(Collection<Review> reviewList) {
        
        for(Review review: reviewList){
            em.merge(review);
            em.flush();
        }
    }
    
    public List<Review> findAll() {
        return em.createQuery("SELECT a FROM Review a", Review.class).getResultList();
    }
}
