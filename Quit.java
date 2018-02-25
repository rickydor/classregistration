/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignmen1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo
 */
public class Quit {
     public static void display(String message) {
     
    Stage window = new Stage();   
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Quit"); 
    message =" Are you sure? ";
    
    // label for putting the message 
    Label label = new Label();
    label.setText(message);
    
    HBox quit = new HBox();
   
    // creating yes and no buttons 
    Button button2 = new Button("NO");
    button2.setOnAction(e-> window.close());   //closing window for yes and no button
    Button button3 = new Button ("Yes");
    button3.setMnemonicParsing(true);
    button3.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        window.close();
        Platform.exit();
      }
    });
            
    quit.setAlignment(Pos.CENTER);
    button3.setAlignment(Pos.BOTTOM_RIGHT);
    button2.setAlignment(Pos.BOTTOM_LEFT);
    quit.getChildren().addAll(label,button3, button2);
    
    Scene scene = new Scene(quit,200,100);
    window.setScene(scene);
    window.showAndWait();
    
}
}
