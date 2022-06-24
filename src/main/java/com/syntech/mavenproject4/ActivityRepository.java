/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.mavenproject4;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hrishi
 */
@Stateless
public class ActivityRepository {
    
    @PersistenceContext(unitName = "sptDS")
    private EntityManager em;

    public ActivityRepository() {
    }
    
    public EntityManager getEntityManager() {
        return em;
    }
    
    public void persistActivity(Activity activity) {
        em.merge(activity);
        em.flush();
    }
    
    public List<Activity> findAll() {
        return em.createQuery("SELECT a FROM Activity a", Activity.class).getResultList();
    }
    
    public Activity findById(long id) {
        Activity activityFound = (Activity) em.find(Activity.class, id);
        return activityFound;
    }
    
    public void delete(long id) {
        Activity activity = findById(id);
        
        getEntityManager().remove(activity);
        getEntityManager().flush();
    }
}
