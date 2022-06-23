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
import javax.persistence.Query;

/**
 *
 * @author hrishi
 */
@Stateless
public class EmployeeRepository {
    
    @PersistenceContext(unitName = "sptDS")
    private EntityManager em;    

    public EmployeeRepository() {
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void persistEmployee(Employee employee) {
        em.merge(employee);
        em.flush();
    }
    
    public Employee findByUserName(String username) {
        Query query = em.createQuery("Select a from Employee a where a.username = :u",Employee.class);
        query.setParameter("u", username);
        Employee employee = (Employee)query.getSingleResult();
        return employee;
    }
    
    public Employee findById(Long id) {
        Employee employeeFound = (Employee) em.find(Employee.class, id);
        return employeeFound;
    }
    
//    public void delete(long id) {
//        getEntityManager().remove(findById(id));
//        getEntityManager().flush();
//    }
    
    public List<Employee> findAll() {
        return em.createQuery("SELECT a FROM Employee a", Employee.class).getResultList();
    }
}
