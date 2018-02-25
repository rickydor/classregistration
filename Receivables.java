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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static programmingassignmen1.FullTime.table;
import static programmingassignmen1.NonMatriculated.url;
import static programmingassignmen1.PartTime.url;

/**
 *
 * @author Ricardo
 */
public class Receivables {
static String url = "jdbc:mysql://localhost:3306/boolauniversity?useSSL=false";
static String user = "ricky";
static String password = "legendary,12";

final static TableView<Studentsinfo> table = new TableView<Studentsinfo>();
static ObservableList<Studentsinfo> data = FXCollections.observableArrayList();
       
    
public static void display() throws SQLException {   
    
 Stage window = new Stage();   
 window.initModality(Modality.APPLICATION_MODAL);
 window.setTitle("Receivable");                      //creating all the tableview colunms for displaying data in tableview
 
  TableColumn ssNumber = new TableColumn("SS Number");
    ssNumber.setMinWidth(150);
    ssNumber.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("ssNumber"));
 
    TableColumn firstName = new TableColumn("First Name");
    firstName.setMinWidth(150);
    firstName.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("firstName"));
 
    TableColumn middleName = new TableColumn("M Name");
    middleName.setMinWidth(80);
    middleName.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("middleName"));
        
    TableColumn lastName = new TableColumn("Last Name");
    lastName.setMinWidth(120);
    lastName.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("lastName"));
    
    TableColumn coursename = new TableColumn("Credits Course");
    coursename.setMinWidth(150);
    coursename.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("coursename"));
    
    TableColumn credits = new TableColumn("Credits");
    credits.setMinWidth(100);
    credits.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("credist"));
    
    TableColumn price = new TableColumn("Credits");
    price.setMinWidth(80);
    price.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("price"));
     
    
    
  table.getColumns().addAll(ssNumber,firstName,middleName,lastName,coursename,price, credits);   
  
   Button load = new Button("Load Info");         // button for executing query 
   load.setOnAction(e -> {
   try {
   Statement statement = null;
   Connection connection = DriverManager.getConnection(url, user, password);      
   System.out.println("Database connected");
   statement = connection.createStatement();
   ResultSet resultSet = statement.executeQuery("select *from boolauniversity.courseregistration3");
   while (resultSet.next()) {
   data.add(new Studentsinfo(resultSet.getString("ssn"),
                                resultSet.getString("firstname"), 
                                resultSet.getString("middlename"),    
                                resultSet.getString("lastname"),
                                resultSet.getString("coursename"),
                                resultSet.getString("credits"),
                                resultSet.getString("price")));
   table.setItems(data);
        }
    } catch (SQLException ex) { 
         //Logger.getLogger(Receivables.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Database error,"
                           + "couldn't load any data.");
     }
   }
); 
   
 Button cancel = new Button("Cancel");
 cancel.setOnAction(e-> window.close());  
 
 GridPane gridPane = new GridPane();
 gridPane.setHgap(5);
 gridPane.setVgap(5);

 gridPane.add(table, 2, 2);
 gridPane.add(load, 2, 4);
 gridPane.add(cancel,2,5);
    
 Scene scene = new Scene(gridPane,845,480);
 window.setScene(scene);
 window.showAndWait();   
 }
} 
   
    
 