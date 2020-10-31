/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create two buttons and place them above each other in the scene
        StackPane p = new StackPane();
        //Or we can create a Pane which takes the stack layout by default
        //Pane p = new Pane();
        
        //Pane p = new StackPane(); works okay because StackPane is a child of Pane
        
        Button btn1 = new Button(" 1 ");
        Button btn2 = new Button("22222222");
        p.getChildren().addAll(btn2, btn1);
        p.setStyle("-fx-background-color: #87CEFA;");
        
        Scene scene = new Scene(p, 600, 400);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
