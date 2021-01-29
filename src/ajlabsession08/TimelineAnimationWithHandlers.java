/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession08;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author bader-aul
 */
public class TimelineAnimationWithHandlers extends Application {

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
        //adding this line of code to explicitly show the image in the bottom half of the scene
        animatedImage.setY(300);

        //use Timeline to animate ImageView
        //use inner class event handler to change the images
        t = new Timeline(new KeyFrame(Duration.millis(100), new AnimationEventHandler()));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();

        //create a stack pane to show two images on top of each other (animatedImage on top of backgroundImage)
        Pane root = new Pane();
        root.getChildren().addAll(backgroundImage, animatedImage);
        //removing this line of code as generic Pane does not have setAlignmentMethod
//        root.setAlignment(Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);

        //Adding event handlers
        //=====================
        /*
        ========================================================================
        setX & setY don't work when the ImageView is positioned in a StackPane
        since StackPane is a managed layout pane, i.e. it does not allow changing
        the layout positions explicitly.
        
        Instead, use setTranslateX and setTranslateY inside a StackPane, but will
        need to keep track of original x and y positions.
        => changed root from StackPane to Pane
        ========================================================================
         */
        //========================================================================
        //onMouseClicked will get fired when we drag the image => conflict with onMouseDragged 
        //=> add mouse event handler to the ImageView and differentiate between the two types of events
        /*
        animatedImage.setOnMouseDragged((event) -> {
            handleDrag(event); //created my own method
        });

        animatedImage.setOnMouseClicked((event) -> {
            if (t.getStatus() == Animation.Status.RUNNING) {
                t.pause();
            } else {
                t.play();
            }
        });
         */
        //generic event handler that handles all types of mouse events
        animatedImage.addEventHandler(MouseEvent.ANY, new MouseEventHandler());

        //handling event when mouse has entered the dimensions of the ImageView
        animatedImage.setOnMouseEntered((MouseEvent event) -> {
            handleClick();
        });

        //handling event when mouse has exited the dimensions of the ImageView
        animatedImage.setOnMouseExited((MouseEvent event) -> {
            handleClick();
        });

        scene.setOnKeyPressed((event) -> {
            if (event.getCode().getName().equalsIgnoreCase("r")) {
                animatedImage.setX(0);
                animatedImage.setY(300);
                if (t.getStatus() != Animation.Status.RUNNING) {
                    t.play();
                }

            }
        });

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

    private void handleDrag(MouseEvent event) {
        animatedImage.setX(event.getX());
        animatedImage.setY(event.getY());
    }

    private void handleClick() {
        if (t.getStatus() == Animation.Status.RUNNING) {
            t.pause();
        } else {
            t.play();
        }
    }

    //inner event handler for mouse events on ImageView
    private class MouseEventHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                handleClick();
            } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                handleDrag(event);
            }
        }

    }

    //inner event handler class for animation change
    class AnimationEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (index + 1 == imagePaths.length) {
                index = 0; //to keep the animation repeating, we are showing the first image in the array after the last one has loaded
            } else {
                index++; //to use the next image path in the array
            }
            animatedImage.setImage(new Image(imagePaths[index])); //change image displayed in the ImageView
        }
    }

}
