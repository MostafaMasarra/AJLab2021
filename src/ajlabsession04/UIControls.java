/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession04;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author Bader
 */
public class UIControls extends Application {

    //add components to Scene
    @Override
    public void start(Stage primaryStage) {
        //Button UI control
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        //add button to scene: acceptable because button is of type parent
        Scene scene1 = new Scene(btn, 300, 250);

        //Stack Pane UI Control: default alignment is center
        //add pane with button to scene: acceptable
        StackPane root1 = new StackPane();

        root1.getChildren().add(btn);
        Scene scene3 = new Scene(root1, 300, 250);

        //======================================//
        //Image View UI Control
        String imagePath = "C:\\Users\\Bader\\Documents\\NetBeansProjects\\AJLab2021\\girl.png"; //change image path to a file in your directory
        ImageView imageView = new ImageView("file:///" + imagePath);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        //add image view to scene : not acceptable => will give error because ImageView is not of type parent
        // Scene scene2 = new Scene(imageView, 300, 250);
        //======================================//
        //add pane with Image View to scene: acceptable
        StackPane root2 = new StackPane();
        root2.getChildren().add(imageView);
        Scene scene4 = new Scene(root2, 300, 250);

        //======================================//
        //adding values through constructor
       //Line l = new Line(150, 450, 200, 0);
        //is equivalent to
        Line l = new Line();
        l.setStartX(50);
        l.setStartY(0);
        l.setEndX(150);
        l.setEndY(100);

        //add shape to scene: not acceptable because shape is not of type parent
        // Scene scene5 = new Scene(l, 300, 250);
        //======================================//
        StackPane root3 = new StackPane();
        
        //set pane background color using CSS
        //root3.setStyle("-fx-background-color: #CD5C5C");
        //similar to
        BackgroundFill bf = new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY); //predefined color
        BackgroundFill bf2 = new BackgroundFill(Color.web("CD5C5C", 0.5), CornerRadii.EMPTY, Insets.EMPTY); //hex code
        root3.setBackground(new Background(bf2)); //OR root3.setBackground(new Background(bf2);

        //add pane with shape to scene: acceptable
        root3.getChildren().add(l);
        Scene scene6 = new Scene(root3, 300, 250);

        //======================================//
        //Polygon shape
        Polygon p = new Polygon();
        p.getPoints().addAll(new Double[]{
            200.0, 50.0,
            400.0, 50.0,
            450.0, 150.0,
            400.0, 250.0,
            200.0, 250.0,
            150.0, 150.0
        });

        //setting style properties via CSS
        p.setStyle("-fx-fill:red; -fx-opacity:0.5; -fx-stroke:black");
        //is equivalent to
//        p.setFill(Color.RED);
//        p.setOpacity(0.5);
//        p.setStroke(new Color(0, 0, 0, 1));

        //add pane with shape to scene: acceptable
        StackPane root4 = new StackPane();
        root4.getChildren().add(p);
        Scene scene7 = new Scene(root4);

        //======================================//
        //create flow pane and add all controls
        FlowPane flowPane = new FlowPane();

        flowPane.setAlignment(Pos.CENTER);
        
        //horizontal orientation
        //flowPane.setOrientation(Orientation.HORIZONTAL);
        //flowPane.setHgap(10);
        
        //or vertical orientation
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setVgap(20);

        flowPane.getChildren().addAll(btn, imageView, l, p);
        Scene scene = new Scene(flowPane);

        //======================================//
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene); //setScenes: scene1, scene2, scene3, scene4, scene5, scene6, scene7 to test the application
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
