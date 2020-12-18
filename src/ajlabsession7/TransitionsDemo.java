/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession7;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Bader
 */
public class TransitionsDemo extends Application {

    Transition setTranslateTransition(Node node) {
        //Creating Translate Transition 
        TranslateTransition translateTransition = new TranslateTransition();

        //Setting the node for the transition 
        translateTransition.setNode(node);

        //Setting the duration of the transition  
        translateTransition.setDuration(Duration.millis(1000));

        //Setting the cycle count for the transition 
        translateTransition.setCycleCount(2);
        //Note that setting auto reverse true needs cycle count > 1

        //Setting auto reverse value
        translateTransition.setAutoReverse(true);

        //Setting the value of the transition only along the x axis results in horizontal translation
        //Setting the value of the transition only along the y axis results in vertical translation
        //Setting the value of the transition along the x and y axis results in diagonal translation
        translateTransition.setByX(300);
        //Or use setFromX and setToX instead

        return translateTransition;
    }

    Transition setScaleTransition(Node node) {
        //Creating scale Transition 
        ScaleTransition scaleTransition = new ScaleTransition();

        //Setting the node for the transition 
        scaleTransition.setNode(node);

        //Setting the duration for the transition 
        scaleTransition.setDuration(Duration.millis(1000));

        //Setting the dimensions for scaling 
        scaleTransition.setByX(1.1);
        scaleTransition.setByY(1.1);

        //Setting the cycle count for the translation 
        scaleTransition.setCycleCount(2);

        //Setting auto reverse value to true 
        scaleTransition.setAutoReverse(true);

        return scaleTransition;
    }

    Transition setFillTransiton(Node node) {
        //Creating Fill Transition and setting its properties in constructor
        FillTransition fillTransition = new FillTransition(Duration.millis(2000), (Shape) node,
                Color.LIGHTSKYBLUE, Color.BLUEVIOLET);
        fillTransition.setCycleCount(50);
        fillTransition.setAutoReverse(true);
        return fillTransition;
    }

    @Override
    public void start(Stage primaryStage) {
        //Drawing a Circle 
        Circle circle = new Circle();

        //Setting the position of the circle 
        circle.setCenterX(150.0);
        circle.setCenterY(300.0);

        //Setting the radius of the circle 
        circle.setRadius(50.0);

        //Setting the color of the circle 
        circle.setFill(Color.LIGHTSKYBLUE);

        //Setting the stroke width of the circle 
        circle.setStrokeWidth(20);

        //set translate transition to circle
        Transition translateTransition = this.setTranslateTransition(circle);
//        translateTransition.play();

        //set scale transition to circle
        Transition scaleTransition = this.setScaleTransition(circle);
//        scaleTransition.play();

        Transition fillTransition = this.setFillTransiton(circle);
//        fillTransition.play();

        //set sequential transition to circle
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(translateTransition, scaleTransition);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
//        str.play();

        ParallelTransition parallelTransition = new ParallelTransition(fillTransition, sequentialTransition);
        parallelTransition.play();

        //Creating a Group object  
        Group root = new Group(circle);

        //Creating a scene object 
        Scene scene = new Scene(root, 600, 600);

        //Setting title to the Stage 
        primaryStage.setTitle("Transitions Demo");

        //Adding scene to the stage 
        primaryStage.setScene(scene);

        //Displaying the contents of the stage 
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
