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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static programmingassignmen1.FullTime.selected;
import static programmingassignmen1.PartTime.url;

/**
 *
 * @author Ricardo
 */


public class NonCredit {
   static String url = "jdbc:mysql://localhost:3306/boolauniversity?useSSL=false";
   static String user = "";
   static String password = "";
   
   private static TextField id = new TextField();      //textfield for students names
   private static TextField ssn = new TextField();  
   private static TextField firstName = new TextField();
   private static TextField middleName = new TextField();  
   private static TextField lastName = new TextField();
   
   
   static ObservableList<CourseSelection> data = FXCollections.observableArrayList();
   static TableView<CourseSelection> table = new TableView<CourseSelection>();
   static ObservableList<CourseSelection> selected;     
   
public static void display() {
    
    Stage window = new Stage();   
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Non-Credit");  
   
        //Inserting non credits courses in an observableArraylist
        data =
        FXCollections.observableArrayList(
            new CourseSelection("NC100", "Basic Cookie Baking", "NC", "$150"),
            new CourseSelection("NC200", "Advanced Tire Inflation", "NC","$150"),
            new CourseSelection("NC300", "Intro to Sinusitis", "NC", "$150"),
            new CourseSelection("NC400", "Shoe Polish and You", "NC", "$150"),
            new CourseSelection("NC500", "Gout for Fun and Profit", "NC", "$150")
        );
            
   final Label label = new Label("NC Courses Registration");
        label.setFont(new Font("Arial", 20));
 
        TableColumn courseNumber = new TableColumn("Course Number");
        courseNumber.setMinWidth(100);
        courseNumber.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("courseNumber"));
 
        TableColumn courseName = new TableColumn("Course Name");
        courseName.setMinWidth(200);
        courseName.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("courseName"));
 
        TableColumn credits = new TableColumn("Credits");
        credits.setMinWidth(100);
        credits.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("credits"));   //Establishing column names in TableColumn
        
        TableColumn price = new TableColumn("Credits");
        price.setMinWidth(100);
        price.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("coursePrice"));
        
     table.setItems(data);
     table.getColumns().addAll(courseNumber, courseName, credits, price);
    
    Button register = new Button("Register");
    register.setOnAction(e -> setSelection());
    register.setOnAction(e -> {
        try {
            registering();
        } catch (SQLException ex) {
           System.out.println("Error, couldn't registered any courses."); 
        }
    }); 
    
    ssn.setPromptText("Enter social security number");
    
    Button cancel = new Button("Cancel");
   cancel.setOnAction(e-> window.close());
  
    
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("ID numbers : "), 0, 0); //gridpane for adding all the fields and table
    gridPane.add(id, 1, 0);
    gridPane.add(new Label("First Name : "), 0, 1);
    gridPane.add(firstName, 1, 1);
    gridPane.add(new Label("Middle Name : "), 0, 2);
    gridPane.add(middleName, 1, 2);
    gridPane.add(new Label("Last Name : "), 0, 3);
    gridPane.add(lastName, 1, 3);
    gridPane.add(new Label("SS numbers : "), 0, 4); //gridpane for adding all the fields and table
    gridPane.add(ssn, 1, 4);
    gridPane.add(label,1, 5);
    gridPane.add(table, 1, 6);
    gridPane.add(register, 1, 7);
    gridPane.add(cancel, 1, 8);
    
    Scene scene = new Scene(gridPane,700,620);
    window.setScene(scene);
    window.showAndWait();
    
    
}
  private static ObservableList<CourseSelection> setSelection() { 
  selected = table.getSelectionModel().getSelectedItems();
  System.out.println("items selected");
  return selected;
}  
  private static void registering() throws SQLException
  {
      String studentsID = id.getText().trim();
      String ssNumber = ssn.getText().trim();
      String fName = firstName.getText().trim();
      String mName = middleName.getText().trim();
      String lName = lastName.getText().trim();
      
      PreparedStatement  mystmt = null;
      Connection connection = DriverManager.getConnection(url, user, password);
      try {
      System.out.println("Database connected");  
     // static ObservableList<CourseSelection> selected; 
      String sql = "INSERT INTO boolauniversity.courseregistration4(id, ssn, firstname, middlename, lastname, coursenumbers, coursename, credits, price) "
              + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
      mystmt = connection.prepareStatement(sql); 
      mystmt.setString(1, id.getText());
      mystmt.setString(2, ssn.getText());                  //inserting values into database
      mystmt.setString(3, firstName.getText());
      mystmt.setString(4, middleName.getText());
      mystmt.setString(5, lastName.getText());
      mystmt.setNString(6, table.getSelectionModel().getSelectedItem().getCourseNumber());
      mystmt.setNString(7, table.getSelectionModel().getSelectedItem().getCourseName());
      mystmt.setNString(8, table.getSelectionModel().getSelectedItem().getCredits());
      mystmt.setNString(9, table.getSelectionModel().getSelectedItem().getCoursePrice());
      mystmt.executeUpdate();
     
 }
      catch(Exception e) {
          //e.printStackTrace();
           System.out.println("Database error, please try again.");
      } finally {
          mystmt.close();
          connection.close();
      }
  }
    
}
