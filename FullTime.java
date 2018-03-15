package programmingassignmen1;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.ConditionalFeature.FXML;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import static programmingassignmen1.FullTime.data;
import static programmingassignmen1.Matriculated.url;
import static programmingassignmen1.Receivables.url;

/**
 *
 * @author Ricardo
 */
    
  public class FullTime {
   
      //info for connecting database
   static String url = "jdbc:mysql://localhost:3306/boolauniversity?useSSL=false";
   static String user = "";
   static String password = "";
   
   //tableview for putting all data like course number, course name, price and number of credits
   static TableView<CourseSelection> table = new TableView<CourseSelection>();
   static ObservableList<CourseSelection> data = FXCollections.observableArrayList(); 
   private static TextField id = new TextField();      //textfield for students names
   private static TextField ssn = new TextField();  
   private static TextField firstName = new TextField();
   private static TextField middleName = new TextField();  
   private static TextField lastName = new TextField();
    @FXML
   static ObservableList<CourseSelection> selected; 
   
   
   static String java = "Introduction to Computers";
   static String keyboarding = "Keyboarding 1";   
   static String english1 = "English 1";
   static String webProgramming = "Web Programming";
   static String cPlusPlusPro = "C++ Programming"; 
    
  public static void display() {  
    Stage window = new Stage();   
    window.initModality(Modality.APPLICATION_MODAL); //creating second window 
    window.setTitle("Full-Time"); 
     
   final Label label = new Label("Courses Registration");     //creating all tableview colunms for serting the data
   label.setFont(new Font("Cambria", 20));
   
    TableColumn courseNumber = new TableColumn("Course Number");
    courseNumber.setMinWidth(100);
    courseNumber.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("courseNumber"));
 
    TableColumn courseName = new TableColumn("Course Name");
    courseName.setMinWidth(150);
    courseName.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("courseName"));
 
    TableColumn credits = new TableColumn("Credits");
    credits.setMinWidth(100);
    credits.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("credits"));
        
    TableColumn price = new TableColumn("Price");
    price.setMinWidth(100);
    price.setCellValueFactory(
                new PropertyValueFactory<CourseSelection, String>("coursePrice"));
    
   data.add(new CourseSelection("CMP220", java, "3","$285"));
   data.add(new CourseSelection("OIM220", keyboarding, "3", "$285"));  //adding all data into the table
   data.add(new CourseSelection("ENG111", english1, "3", "$285"));
   data.add(new CourseSelection("CMP545", webProgramming, "3", "$285"));
   data.add(new CourseSelection("CMP237", cPlusPlusPro, "3", "$285")); 
   table.setItems(data);
        
   table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
   table.getColumns().addAll(courseNumber,courseName,credits, price); 
    
   Button register = new Button("Register");  //register buttion for excuting registering method
   register.setOnAction(e -> setSelection());
   register.setOnAction(e -> {
        try {
            registering();
        } catch (SQLException ex) {
            System.out.println("Error, please try again.");
           // Logger.getLogger(FullTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }); 
   
   ssn.setPromptText("Enter social security number");
   
   Button cancel = new Button("Cancel");  //cancel button
   cancel.setOnAction(e-> window.close());
 
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(label, 1, 4);
    gridPane.add(new Label("Student ID numbers : "), 0, 0); //gridpane for adding all the fields and table
    gridPane.add(id, 1, 0);
    gridPane.add(new Label("First Name : "), 0, 1);
    gridPane.add(firstName, 1, 1);
    gridPane.add(new Label("Middle Name : "), 0, 2);
    gridPane.add(middleName, 1, 2);
    gridPane.add(new Label("Last Name : "), 0, 3);
    gridPane.add(lastName, 1, 3);
    gridPane.add(new Label("Student SS numbers : "), 0, 4); //gridpane for adding all the fields and table
    gridPane.add(ssn, 1, 4);
    gridPane.add(table, 1, 6);
    gridPane.add(register, 1, 7); 
    gridPane.add(cancel, 1, 8); 
    
    Scene scene = new Scene(gridPane,650,600);
    window.setScene(scene);
    window.showAndWait();
    
  }
  
  // method for selection courses wanted
  private static ObservableList<CourseSelection> setSelection() {  
  selected = table.getSelectionModel().getSelectedItems();
  System.out.println("items selected");
  return selected;
}
   //registering method for uploading data in the databaase 
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
    
      String sql = "INSERT INTO boolauniversity.courseregistration3(id, ssn, firstname, middlename, lastname, coursenumbers, coursename, credits, price) "
              + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
      mystmt = connection.prepareStatement(sql); 
      mystmt.setString(1, id.getText());
      mystmt.setString(2, ssn.getText());
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
           System.out.println("Error, please check ur student id, ssn numbers;"
                   + "or you not registered on the system.");
      } finally {
          mystmt.close();
          connection.close();
      }
  }
}
