/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignmen1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static programmingassignmen1.Receivables.table;

/**
 *
 * @author Ricardo
 */
public class ClassSchedule {
 static String url = "jdbc:mysql://localhost:3306/boolauniversity?useSSL=false";
static String user = "ricky";
static String password = "legendary,12";
     
static TableView<StudentsSchedule> table = new TableView<StudentsSchedule>();
static ObservableList<StudentsSchedule> data = FXCollections.observableArrayList();
  
public static void display(){
Stage window = new Stage();   
window.initModality(Modality.APPLICATION_MODAL);
window.setTitle("Schedule"); 
    
TableColumn ssNumber = new TableColumn("SS NUmber");   //table colunms for displaying data
    ssNumber.setMinWidth(150);
    ssNumber.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("ssNumber"));
 
    TableColumn firstName = new TableColumn("First Name");
    firstName.setMinWidth(150);
    firstName.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("firstName"));
 
    TableColumn middleName = new TableColumn("M Name");
    middleName.setMinWidth(80);
    middleName.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("middleName"));
        
    TableColumn lastName = new TableColumn("Last Name");
    lastName.setMinWidth(150);
    lastName.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("lastName"));
    
    TableColumn classe = new TableColumn("Class");
    classe.setMinWidth(100);
    classe.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("classes"));
    
    TableColumn matriculate = new TableColumn("Matriculation");
    matriculate.setMinWidth(100);
    matriculate.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("matriculation"));
    
    TableColumn courseNumber = new TableColumn("Course Number");
    courseNumber.setMinWidth(100);
    courseNumber.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("courseNumber"));
 
    TableColumn courseName = new TableColumn("Course Name");
    courseName.setMinWidth(150);
    courseName.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("courseName"));
      
    TableColumn meetPlace = new TableColumn("Meeting/Time");
    meetPlace.setMinWidth(100);
    meetPlace.setCellValueFactory(
                new PropertyValueFactory<StudentsSchedule, String>("meeting"));
    
    
  table.getColumns().addAll(ssNumber,firstName,middleName,lastName,classe,matriculate,courseNumber,courseName,meetPlace);   
  
   Button load = new Button("Load Info");  //button for executing the query qnd show the resultset
   load.setOnAction(e -> {
   try {
   Statement statement = null;
   Connection connection = DriverManager.getConnection(url, user, password);      
   System.out.println("Database connected");
   statement = connection.createStatement();
   ResultSet resultSet = statement.executeQuery("select * from boolauniversity.courseregistration3, studentsinfo ");
   while (resultSet.next()) {
   data.add(new StudentsSchedule(resultSet.getString("ssn"),
                                resultSet.getString("firstname"), 
                                resultSet.getString("middlename"),    
                                resultSet.getString("lastname"),
                                resultSet.getString("class"),
                                resultSet.getString("yearmatriculated"),
                                resultSet.getString("coursenumbers"),
                                resultSet.getString("coursename")));
   
   table.setItems(data);
        }
    } catch (SQLException ex) { 
        // Logger.getLogger(Receivables.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Database error,"
                           + "couldn't load any data.");
        // Logger.getLogger(Receivables.class.getName()).log(Level.SEVERE, null, ex);
     }
   }
); 
 
 Button cancel = new Button("Cancel");
 cancel.setOnAction(e-> window.close());
   
 GridPane gridPane = new GridPane();
 gridPane.setHgap(5);
 gridPane.setVgap(5);                        //gridpane for putting the table and the buttons
 gridPane.add(table, 2, 2);
 gridPane.add(load, 2, 4);
 gridPane.add(cancel, 2, 5);    
    
Scene scene = new Scene(gridPane,1100,480);
window.setScene(scene);
window.showAndWait();   
}
}
