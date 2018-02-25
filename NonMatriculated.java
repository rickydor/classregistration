/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignmen1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static programmingassignmen1.Matriculated.url;
import static programmingassignmen1.NonMatriculated.states;


/**
 *
 * @author Ricardo
 */
public class NonMatriculated {
    //info for accessing mysql database
    static String url = "jdbc:mysql://localhost:3306/boolauniversity?useSSL=false";
    static String user = "ricky";
    static String password = "legendary,12";
    
    //textfield for students information
    private static TextField ssn = new TextField();
    private static TextField firstName = new TextField();
    private static TextField middleName = new TextField();  
    private static TextField lastName = new TextField();
    private static TextField streetAddress = new TextField();
    private static TextField city = new TextField();
    private static TextField zipCode = new TextField();
    private static TextField date = new TextField(); 
    static ComboBox<String> states;                                //combobox and checkbox for states and prerequisites
    static CheckBox box1 = new CheckBox("High School Diploma");
    static CheckBox box2 = new CheckBox("Immunization");
     
  //method for getting all the information for students
    public static void display() {
    Stage window = new Stage();   
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Non-Matriculated"); 
    
    states = new ComboBox();
    states.getItems().addAll(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
            "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", 
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
            "VA", "WA", "WV", "WI", "WY"
            
    );
    
    //button for executing the admission method
    Button submit = new Button("Submit");
    submit.setOnAction(e -> {
        try {
            gettingAdmission();
            ssn.clear();
            firstName.clear();
            middleName.clear();
            lastName.clear();
            streetAddress.clear();
            city.clear();
            zipCode.clear();
            date.clear();
        } catch (SQLException ex) {
            Logger.getLogger(NonMatriculated.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
    
    //promptext for textfield 
    ssn.setPromptText("Enter social security number");
    firstName.setPromptText("Enter first name");
    middleName.setPromptText("Enter middle name");
    lastName.setPromptText("Enter last name");
    streetAddress.setPromptText("Enter street address");
    city.setPromptText("Enter city");
    zipCode.setPromptText("Enter zipcode");
    date.setPromptText("Enter today's date");
    Button cancel = new Button("Cancel");
    cancel.setOnAction(e-> window.close());
   
    //gridpane to add all the fields created
    GridPane gridPane = new GridPane();
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
    gridPane.add(new Label("Prerequisites : "), 0, 8);
    gridPane.add(box1, 1, 8);
    gridPane.add(box2, 1, 9);
    gridPane.add(submit, 1, 11);
    gridPane.add(cancel, 2, 11);
    
     Scene scene = new Scene(gridPane,380,320,Color.CHARTREUSE);
     window.setScene(scene);
     window.showAndWait();

    
}


 private static Connection  initializeDB() {
      Connection connection = null;
    try {  
   connection = DriverManager.getConnection(url, user, password);
   System.out.println("Database connected");
  }
   catch( Exception exc){
   exc.printStackTrace();
        
} return connection;
}
 
 //method for uploading all the students info into the database   
  private static void gettingAdmission() throws SQLException
  {
      
      String ssNumber = ssn.getText().trim();
      String fName = firstName.getText().trim();
      String mName = middleName.getText().trim();
      PreparedStatement  mystmt = null;
      Connection connection = DriverManager.getConnection(url, user, password);
      
      try {
      System.out.println("Database connected");   
      
      String sql = "INSERT INTO studentsinfo(ssn ,firstname, middlename, lastname, streetadress, city, zipcode, date) "
              + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; 
      mystmt = connection.prepareStatement(sql);
      mystmt.setString(1, ssn.getText());
      mystmt.setString(2, firstName.getText());
      mystmt.setString(3, middleName.getText());
      mystmt.setString(4, lastName.getText());
      mystmt.setString(5, streetAddress.getText());
      mystmt.setString(6, city.getText());
      mystmt.setString(7, zipCode.getText());
      mystmt.setString(8, date.getText());
      mystmt.setString(8, states.getSelectionModel().getSelectedItem());
      mystmt.setString(10, box1.getText());
      mystmt.setString(11, box2.getText());
}
      catch(Exception e) {
           System.out.println("Error data entry, prosibly already existed");
      } finally {
          mystmt.close();
          connection.close();
      }
  }   
   
}