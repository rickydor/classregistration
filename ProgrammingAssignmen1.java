/*
Ricardo Dorancy 
Professor Richmond 
CIT 285
Programming assignment 1 
10/15/2016

This program is developed for boola boola university. It's task is to get all students information
and save them into mysql database. And another tasks is to registers students for different classes,
then show their class schedule. The program is developed in javaFX using Netbeans; All table for the 
database have already been created. For registering for class, id and social  security is needed. Both are the
same number.

Design
Create javafx scene, put the three main menus Create the menu items and the scences for each of them.
create all the textfield and combobox, checkbox for matriculte students, non matriculate students, and
set up the quit option. Create a tableview  to under registration full time, part time and non credits option.
Creat tablbeview for for receivable and schedule option.Insert all the data into to dislay in the table, then 
get a connect to mysql database. Create the different table in my sequel , and get a connection then insert the 
data.

-Here is some students that I have saved in the database already

'123456781', 'Mack', 'MD', 'Durand', '28 Otis st', 'Revere', 'CA', '2178', '10/05/2016', '2014', 'Senior', 'Associate Of Arts In Humanities', 'High School Diploma', 'Immunization'
'123456782', 'James', 'JA', 'Abraham', '87 mass ave', 'Cambrigde', 'MA', '2344', '10/2/2016', '2013', 'Freshman', 'Associate Of Science In Computer Programming', 'High School Diploma', 'Immunization'
'123456783', 'Jeff', 'JP', 'Paul', '24 Perl st', 'Smurl', 'CT', '2377', '10/2/2016', '2015', 'Sophomore', 'Associate Of Science In Computer Programming', 'High School Diploma', 'Immunization'
'123456784', 'Ryck', 'RJ', 'Jhon', '23 Park st', 'Malden', 'CO', '2177', '12/17/2016', '2015', 'Sophomore', 'Associate Of Science In Computer Programming', 'High School Diploma', 'Immunization'
'123456789', 'Bird', 'BM', 'Man', '23 Oak st', 'Malden', 'MA', '2134', '10/12/2016', '2015', 'Freshman', 'Associate Of Science In Computer Programming', 'High School Diploma', 'Immunization'
'123456798', 'Jack', 'JR', 'Rick', '23 Bird st', 'Boston', 'MA', '2134', '10/12/2016', '2015', 'Freshman', 'Associate Of Science In Computer Programming', 'High School Diploma', 'Immunization'
'222222229', 'Young', 'YJ', 'Jeezy', '24 Park st', 'Melbourne', 'AK', '2123', '10/12/2016', '2015', 'Junior', 'Associate Of Arts In Humanities', 'High School Diploma', 'Immunization'

-Here are some students that already register for clases
'123456781', '123456781', 'Mack', 'MD', 'Durand', 'CMP237', 'C++ Programming', '3', '$300'
'123456782', '123456782', 'James', 'JA', 'Abraham', 'OIM220', 'Keyboarding 1', '3', '$300'
'123456789', '123456789', 'Bird', 'BM', 'Man', 'ENG111', 'English 1', '3', '$285'
'123456798', '123456798', 'Jack', 'JR', 'Rich', 'ENG111', 'English 1', '3', '$285'
'222222229', '222222229', 'Young', 'YJ', 'Jeezy', 'CMP545', 'Web Programming', '3', '$300'


 */
package programmingassignmen1;

//import Matriculated;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 *
 * @author Ricardo
 */
public class ProgrammingAssignmen1 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        launch(args);
    }
  
    @Override
    public void start(Stage primaryStage) throws Exception {
   
   
    //Menus for admission, registration, reports
    Menu admission = new Menu("Admissions");
    Menu registration = new Menu("Registration");
    Menu reports = new Menu("Reports");        
   
    //Menu items for matriculated students, non matriculated and quit option
    MenuItem matriculatedStudents = new MenuItem("Matriculated(Degree Seeking)");
    matriculatedStudents.setOnAction(e -> Matriculated.display());
    MenuItem nonMatriculatedStudents = new MenuItem("Non-Matriculated");
    nonMatriculatedStudents.setOnAction(e -> NonMatriculated.display());
    MenuItem quit = new MenuItem("Quit");
    quit.setOnAction(e -> Quit.display("message"));
    admission.getItems().addAll(matriculatedStudents, nonMatriculatedStudents,quit);
    
    //Menu item for full time, part time, and non credits
    MenuItem fullTime = new MenuItem("Full-Time");
    fullTime.setOnAction(e -> FullTime.display());
    MenuItem partTime = new MenuItem("Part-Time");
    partTime.setOnAction(e -> PartTime.display());
    MenuItem nonCredit = new MenuItem("Non-Credit");
    nonCredit.setOnAction(e -> NonCredit.display());
    registration.getItems().addAll(fullTime, partTime, nonCredit);
    
    //menu items for receivable
    MenuItem receivable = new MenuItem("Receivable");
    receivable.setOnAction(e ->  {
        try {
            Receivables.display();
        } catch (SQLException ex) {
            System.out.println("Error, please try again.");
           // Logger.getLogger(ProgrammingAssignmen1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }); 
    // menu item for class schedule
    MenuItem classSchedule = new MenuItem("Class Schedule");
    classSchedule.setOnAction(e -> ClassSchedule.display());
    reports.getItems().addAll(receivable, classSchedule);
 
    
     MenuBar menubar = new MenuBar();
     menubar.getMenus().addAll(admission, registration, reports);
     
    // creating Image and Imageview for adding an image
     Image image = new Image("boola.jpg");
     ImageView imageview = new ImageView();
     imageview.setImage(image);
     imageview.setFitWidth(480);
     imageview.setPreserveRatio(true);
     imageview.setSmooth(true);
     imageview.setCache(true);
     imageview.setImage(image);
     imageview.setLayoutX(10);
     imageview.setLayoutY(65);
     
     Label label = new Label("BOOLA BOOLA UNIVERSITY");
     label.setFont(new Font("Cambria", 30));
  
    
    BorderPane layout = new BorderPane();
               layout.setTop(menubar);
               layout.setRight(label);
               layout.getChildren().addAll(imageview);
               
               
                  
    Scene scene = new Scene(layout,500, 400);
    primaryStage.setTitle("BOOLA UNIVERSITY"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); 
        
    }
    
}
