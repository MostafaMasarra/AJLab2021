/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Drawing extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        myDrawingPane m = new myDrawingPane();
        myDrawingPane k = new myDrawingPane();
        myDrawingPane h = new myDrawingPane();
        myDrawingPane j = new myDrawingPane();
       
        FlowPane f = new FlowPane();
        f.getChildren().addAll(m,k, h, j);
        
        Scene scene = new Scene(f, 600, 400);
        
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
