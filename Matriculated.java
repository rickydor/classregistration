/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignmen1;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */ 
    public class Matriculated {
    //mysql info to access to the database
    static String url = "jdbc:mysql://localhost:3306/boolauniversity?useSSL=false";
    static String user = "";
    static String password = "";
    
    //Creating textfield for students information
    private static TextField ssn = new TextField();
    private static TextField firstName = new TextField();
    private static TextField middleName = new TextField();
    private static TextField lastName = new TextField();
    private static TextField streetAddress = new TextField();
    private static TextField city = new TextField();
    private static TextField zipCode = new TextField();
    private static TextField date = new TextField(); 
    private static TextField yearMatriculation = new TextField();
    private static ComboBox<String> states = new ComboBox();  // combobox for states, degree, and class
    private static ComboBox<String> degree = new ComboBox();
    private static ComboBox<String> studentClass = new ComboBox();
    private static CheckBox box1 = new CheckBox("High School Diploma"); // checkbox for imunization
    private static CheckBox box2 = new CheckBox("Immunization");
     
        
    public static void display() {
     
    Stage window = new Stage();   
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Matriculated"); 
  
    ssn.setPromptText("Enter social security number"); // setting promptext
    //ssn.getPrefColumnCount();
    firstName.setPromptText("Enter first name");
    middleName.setPromptText("Enter middle name");
    lastName.setPromptText("Enter last name");
    streetAddress.setPromptText("Enter street address");
    city.setPromptText("Enter city");
    zipCode.setPromptText("Enter zipcode");
    date.setPromptText("Enter today's date");
    yearMatriculation.setPromptText("Enter the year of matriculation");
   
    //getting items for states and degree
    states.getItems().addAll(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
            "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", 
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
            "VA", "WA", "WV", "WI", "WY"
    );   
    
    
    degree.getItems().addAll(
            "Associate Of Science In Computer Programming",
            "Associate Of Arts In Humanities"
    );
    
    studentClass.getItems().addAll(
            "Freshman",
            "Sophomore",
            "Junior",
            "Senior"
    );
    
    Button submit = new Button("Submit");
    submit.setOnAction(e -> {
        try {
            gettingAdmission();
            ssn.clear();
            firstName.clear();
            middleName.clear();
            lastName.clear();   //clearing fields
            streetAddress.clear();
            city.clear();
            zipCode.clear();
            date.clear();
            yearMatriculation.clear();
           
        } catch (SQLException ex) {
            
        }
    });
    
    Button cancel = new Button("Cancel");  //cancel button
    cancel.setOnAction(e-> window.close());
   
    GridPane gridPane = new GridPane(); //gridPane for adding all the nodes created
    gridPane.setStyle("-fx-border-color: green; ");
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("SSN # :"), 0, 0);
    gridPane.add(ssn, 1, 0);
    gridPane.add(new Label("First Name :"), 0, 1);
    gridPane.add(firstName, 1, 1);
    gridPane.add(new Label("Middle :"), 0, 2);
    gridPane.add(middleName, 1, 2);
    gridPane.add(new Label("Last Name :"), 0, 3);
    gridPane.add(lastName, 1, 3);
    gridPane.add(new Label("Street Address :"), 0, 4);
    gridPane.add(streetAddress, 1, 4);
    gridPane.add(new Label("City : "), 0, 5);
    gridPane.add(city, 1, 5);
    gridPane.add(new Label("Sate : "), 0, 6);
    gridPane.add(states, 1, 6);
    gridPane.add(new Label("Zip code : "), 0, 7);
    gridPane.add(zipCode, 1, 7);
    gridPane.add(new Label("Today's Date : "), 0, 8);
    gridPane.add(date, 1, 8);
    gridPane.add(new Label("Year Of Matriculation : "), 0, 9);
    gridPane.add(yearMatriculation, 1, 9);
    gridPane.add(new Label("Class : "), 0, 10);
    gridPane.add(studentClass, 1, 10);
    gridPane.add(new Label("Degree : "), 0, 11);
    gridPane.add(degree, 1, 11);
    gridPane.add(new Label("Prerequisites : "), 0, 12);
    gridPane.add(box1, 1, 12);
    gridPane.add(box2, 1, 13);
    gridPane.add(submit, 1, 14);
    gridPane.add(cancel, 2, 14);
       
    Scene scene = new Scene(gridPane,560,450);
    window.setScene(scene);
    window.showAndWait();

    }
 
    //function for uploading all information into mysql database
  private static void gettingAdmission() throws SQLException
  {
      PreparedStatement  mystmt = null;
      Connection connection = DriverManager.getConnection(url, user, password);
      
      try {
      System.out.println("Database connected");    
      
      String sql = "INSERT INTO studentsinfo(ssn ,firstname, middlename, lastname, streetaddress, city, states, zipcode, date, yearmatriculated, class, degree, prerequisite1, prerequisite2) " 
              + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
      mystmt = connection.prepareStatement(sql);
      mystmt.setString(1, ssn.getText());
      mystmt.setString(2, firstName.getText());
      mystmt.setString(3, middleName.getText()); 
      mystmt.setString(4, lastName.getText());
      mystmt.setString(5, streetAddress.getText());
      mystmt.setString(6, city.getText());
      mystmt.setString(7, states.getSelectionModel().getSelectedItem());
      mystmt.setString(8, zipCode.getText());
      mystmt.setString(9, date.getText());
      mystmt.setString(10, yearMatriculation.getText());
      mystmt.setString(11, studentClass.getSelectionModel().getSelectedItem());
      mystmt.setString(12, degree.getSelectionModel().getSelectedItem());
      
      if (box1.getText() == null && box2.getText() == null )
      {
          System.out.println("Registration not allowed; missing imunization.");
      }
      else
      mystmt.setString(13, box1.getText());  
      mystmt.setString(14, box2.getText());
      mystmt.executeUpdate();
      }
      catch(Exception e) {
           System.out.println("Error data entry, prosibly already existed");
      } finally {
          mystmt.close();
          connection.close();
      }
  }   
   
}

    
