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
public class Studentsinfo {
  
  private final SimpleStringProperty ssNumber;  
  private final SimpleStringProperty firstName;
  private final SimpleStringProperty middleName; //
  private final SimpleStringProperty lastName;   
  private final SimpleStringProperty coursename;
  private final SimpleStringProperty price;
  private final SimpleStringProperty credits; 

  Studentsinfo(String ssn, String name, String middle, String last, String cname, String prices, String credit) {        
  
  this.ssNumber = new SimpleStringProperty(ssn);    
  this.firstName = new SimpleStringProperty(name);           //constructor for getting the info and display in receivable option
  this.middleName = new SimpleStringProperty(middle);
  this.lastName = new SimpleStringProperty(last);
  this.coursename = new SimpleStringProperty(cname);
  this.price = new SimpleStringProperty(prices);
  this.credits = new SimpleStringProperty(credit);
  
    }
  
   public String getSsNumber() {   //setters and getters for receivable option
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
     
    public String getCname() {
        return credits.get();
    }
     
     public void setCname(String course) {
        coursename.set(course);
    }   
     
     public String getPrice() {
        return price.get();
    }
     
     public void setPrice(String prices) {
        price.set(prices);
    }   
     
    public String getCredit() {
        return credits.get();
    }
     
     public void setCredit(String credit) {
        credits.set(credit);
    }   
        
    
}
