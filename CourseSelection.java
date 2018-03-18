/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignmen1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Ricardo
 */
public class CourseSelection {
    
    private final SimpleStringProperty courseNumber; // diferent options for course registration
    private final SimpleStringProperty courseName;
    private final SimpleStringProperty credits;
    private final SimpleStringProperty coursePrice;
    
 
    CourseSelection (String number, String course, String credit, String price) {   //arguments for courseSelection class
        
        this.courseNumber = new SimpleStringProperty(number);    // constructor for course option
        this.courseName = new SimpleStringProperty(course);
        this.credits = new SimpleStringProperty(credit);
        this.coursePrice = new SimpleStringProperty(price);
    }
 
    public String getCourseNumber() {
        return courseNumber.get();
    }
    public void setCourseNumber(String course) {
        courseNumber.set(course);
    }
        
    public String getCourseName() {
        return courseName.get();                     // setters and getters for course option
    }
    public void setCourseName(String name) {
        courseName.set(name);
    }
    
    public String getCredits() {
        return credits.get();
    }
    public void setCredits(String credit) {
        credits.set(credit);
    }
        
     public String getCoursePrice() {
        return coursePrice.get();
    }
    public void setCoursePrice(String cost) {
        coursePrice.set(cost);
    } 
}
       
    

