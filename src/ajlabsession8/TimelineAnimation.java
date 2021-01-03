/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession8;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author bader-aul
 */
public class TimelineAnimation extends Application {

    //index to iterate image series
    private int index = 0;
    //create ImageView component for background image
    private final ImageView backgroundImage = new ImageView("File:images/bg.jpg"); //File path on macOS => change to appropriate path for Windows machines
    private final String[] imagePaths = new String[9];
    private ImageView animatedImage;
    private Timeline t;
    private TranslateTransition pathT;

    @Override
    public void start(Stage primaryStage) {
        //create an array of image paths to load images from
        for (int i = 1; i < 10; i++) {
            imagePaths[i - 1] = "File:images/" + i + ".png"; //File path on macOS => change to appropriate path for Windows machines
        }

        //set first image to display of the animated series
        animatedImage = new ImageView(imagePaths[index]);
        animatedImage.setFitHeight(300);
        animatedImage.setPreserveRatio(true);

        //use Timeline to animate ImageView
        //use inner class event handler to change the images
        t = new Timeline(new KeyFrame(Duration.millis(100), new AnimationEventHandler()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        //use path transition animation to move the position of the animated image along the x-axis (-400 => 800)
        pathT = new TranslateTransition(Duration.millis(5000));
        pathT.setNode(animatedImage);
        pathT.setFromX(-400);
        pathT.setToX(800);
        pathT.setCycleCount(Timeline.INDEFINITE);
        
        //create a stack pane to show two images on top of each other (animatedImage on top of backgroundImage)
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImage, animatedImage);
        root.setAlignment(Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Animation Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //inner event handler class for animation change
    class AnimationEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            pathT.play(); //start the path transition animation with the timeline animation
            if (index + 1 == imagePaths.length) {
                index = 0; //to keep the animation repeating, we are showing the first image in the array after the last one has loaded
            } else {
                index++; //to use the next image path in the array
            }
            animatedImage.setImage(new Image(imagePaths[index])); //change image displayed in the ImageView

        }
    }

}
