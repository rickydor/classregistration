/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignmen1;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Ricardo
 */
public class StudentsSchedule {
    
   private final SimpleStringProperty ssNumber;  
  private final SimpleStringProperty firstName;  // class for getting info to display in class schedule option
  private final SimpleStringProperty middleName;
  private final SimpleStringProperty lastName;   
  private final SimpleStringProperty classes;
  private final SimpleStringProperty matriculation;
  private final SimpleStringProperty courseNumber;
  private final SimpleStringProperty courseName;

  
   StudentsSchedule(String ssn, String name, String middle, String last, String nClass, 
                    String matriculated, String course, String coursename) {        
  
  this.ssNumber = new SimpleStringProperty(ssn);    
  this.firstName = new SimpleStringProperty(name);
  this.middleName = new SimpleStringProperty(middle);
  this.lastName = new SimpleStringProperty(last);
  this.classes = new SimpleStringProperty(nClass);
  this.matriculation = new SimpleStringProperty(matriculated);
  this.courseNumber = new SimpleStringProperty(course);
  this.courseName = new SimpleStringProperty(coursename);
    }
   
    public String getSsNumber() {         //setters and getters for class shedule option
        return ssNumber.get();
    }
     
     public void setSsNumber(String ssn) {
        firstName.set(ssn);
    } 
  
  public String getFirstName() {
        return firstName.get();
    }
     
     public void setFirstName(String name) {
        firstName.set(name);
    } 
     
     public String getMiddleName() {
        return middleName.get();
    }
      
     public void setMiddleName(String middle) {
        middleName.set(middle);
    } 
     
    public String getLastName() {
        return lastName.get();
    }
    
     public void setLastName(String last) {
        lastName.set(last);
    } 
     
    public String getClasses() {
        return classes.get();
    }
    
     public void setClasses(String nClass) {
        classes.set(nClass);
    }   
     
      public String getMatriculation() {
        return matriculation.get();
    }
    
     public void setMatriculation(String matriculate) {
        matriculation.set(matriculate);
    }
     
    public String getCourseNumber() {
        return courseNumber.get();
    }
    public void setCourseNumber(String course) {
        courseNumber.set(course);
    }
        
    public String getCourseName() {
        return courseName.get();
    }
    public void setCourseName(String name) {
        courseName.set(name);
    }  
       
    
}
