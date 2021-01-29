/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession02;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Bader
 */
public class BasicGUI extends Application {

    @Override
    public void start(Stage primaryStage) {

        FlowPane p = new FlowPane();
        Label bader = new Label("Label 1");
        TextField txt = new TextField("Enter text here");
        TextArea txtArea = new TextArea("Enter large text here");
        CheckBox check = new CheckBox("Select one or more options");
        RadioButton rb = new RadioButton("Choose one option");

        p.getChildren().add(bader);
        p.getChildren().addAll(txt, txtArea, check, rb);

        Scene scene = new Scene(p, 400, 400);

        primaryStage.setTitle("GUI Application");
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
