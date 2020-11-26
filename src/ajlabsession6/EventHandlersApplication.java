/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession6;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Bader
 */
public class EventHandlersApplication extends Application {

    //define these controls here to be able to access them from inner class
    Button btnEnlarge, btnShrink;
    ResizableCircle circlePane;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createGridPane(); //exported into separate function for code readability

        btnEnlarge = new Button("Enlarge");
        btnShrink = new Button("Shrink");
        
        EventHandler<ActionEvent> myCircleInnerHandler = new CircleInnerHandler(); //add type of event such as <ActionEvent> to prevent warning in output
        
        //set same handler to two buttons: this is an advantage of implementing handlers as inner or outer class over anonymous function
        btnEnlarge.setOnAction(myCircleInnerHandler);
        btnShrink.setOnAction(myCircleInnerHandler);

        //add button to hbox
        HBox hbox = new HBox(btnEnlarge, btnShrink);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        
        //create resizable circle which sits inside stack pane (default alignment position of stack pane is center, no need to set it)
        circlePane = new ResizableCircle(50);
        
        //add all panes into vbox and set it to scene
        VBox vbox = new VBox(gridPane, circlePane, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        //create new button and set event handler using outer class
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        //create instance of MyHandler EventHandler
        EventHandler<ActionEvent> myHandler = new MyHandler();
        // or MyHandler myHandler = new MyHandler();
        //create instance of MyInnerHandler EventHandler
        EventHandler<ActionEvent> myInnerHandler = new MyInnerHandler();
        //use setOnAction to add event handler
        btn.setOnAction(myHandler);
        //or use addEventHandler and set the type of event
        btn.addEventHandler(ActionEvent.ACTION, myHandler);
        //create new text field and set event handler using inner class
        TextField txtField = new TextField();
        txtField.setPrefColumnCount(20);
        txtField.setPromptText("Press Enter to say Hello World!");
        txtField.setOnAction(myInnerHandler);
        //create another button and set event handler using anonymous class
        Button btn1 = new Button("Welcome to AJ Lab");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Anonymous Event Class is saying: Welcome to AJLab!");
            }
        });
        //create another text field and set event handler using anonymous lambda expression
        TextField txtField1 = new TextField();
        txtField1.setPrefColumnCount(20);
        txtField1.setPromptText("Type here!");
        txtField1.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            //get key being pressed
            System.out.println(event.getCode());
        });
        //add controls to grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(btn, 0, 0);
        gridPane.add(txtField, 1, 0);
        gridPane.add(btn1, 0, 1);
        gridPane.add(txtField1, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    //first inner class
    class MyInnerHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            System.out.println("Inner Event Class is saying: Hello World!");
        }
    }

    //second inner class
    class CircleInnerHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == btnEnlarge) {
                circlePane.enlarge();
            } else if (event.getSource() == btnShrink) {
                circlePane.shrink();
            }
        }

    }
}
