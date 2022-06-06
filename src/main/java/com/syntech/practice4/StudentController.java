/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syntech.practice4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hrishi
 */
@Named
@ViewScoped
public class StudentController implements Serializable {
    
    private Student student;
    
    private static final List<Student> studentList = new ArrayList<Student>(Arrays.asList(
            new Student("Vivek","MCA 3rd","MCA/07/40",29),
            new Student("Sunil","MCA 3rd","MCA/07/41",33),
            new Student("Bharat","MCA 3rd","MCA/07/42",27),
            new Student("Richi","MCA 3rd","MCA/07/43",28),
            new Student("Sahdev","MCA 3rd","MCA/07/44",28)));
    
    public List<Student> getStudents(){
        return studentList;
    } 
    
    public String editStudent(){
	    student.setEditable(true);
	    return null;
   }
    
    public String saveStudents(){
        
        for(Student std: studentList){
            std.setEditable(false);
        }
        return null;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
