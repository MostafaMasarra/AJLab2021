package ajlabsession5;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;

/*
Unidirectional vs. Bidirectional Binding
 */
public class RolePlayer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    TextField txtCharacter;
    TextField txtActor;

    @Override
    public void start(Stage primaryStage) {
        // Create the Character label
        Label lblCharacter = new Label("Character's Name:");
        lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);
        //set MaxWidth and minWidth to give the label a fixed width
        lblCharacter.setMaxWidth(100);
        lblCharacter.setMinWidth(100);

        // Create the Character text field
        txtCharacter = new TextField();
        txtCharacter.setMinWidth(200);
        txtCharacter.setMaxWidth(200); //depends on pixels
//        txtCharacter.setPrefColumnCount(1); //depends on how many characters to display

        //sets placeholder text: different from setText which actually changes the value of the text field
        txtCharacter.setPromptText("Enter the name of the character here.");

        // Create the Actor label
        Label lblActor = new Label("Actor's Name:");
        lblActor.setAlignment(Pos.BOTTOM_RIGHT);
        lblActor.setMaxWidth(100);
        lblActor.setMinWidth(100);

        // Create the Actor text field
        txtActor = new TextField();
        txtActor.setMinWidth(200);
        txtActor.setMaxWidth(200);
        txtActor.setPromptText("Enter the name of the actor here.");

        // Create the Role labels
        Label lblRole1 = new Label("The role of ");
        Label lblRole2 = new Label(); //label to bind txtCharacter property to
        Label lblRole3 = new Label(" will be played by ");
        Label lblRole4 = new Label(); //label to bind txtActor property to

        lblRole2.textProperty().bind(txtCharacter.textProperty());
        lblRole4.textProperty().bind(txtActor.textProperty());

        // Create the Character pane using HBox
        HBox paneCharacter = new HBox(20, lblCharacter, txtCharacter);
        paneCharacter.setPadding(new Insets(10));

        // Create the Actor pane using HBox
        HBox paneActor = new HBox(20, lblActor, txtActor);
        paneActor.setPadding(new Insets(10));

        // Create the Role pane using HBox
        HBox paneRole = new HBox(lblRole1, lblRole2, lblRole3, lblRole4);
        paneRole.setPadding(new Insets(10));

        // Add the Character and Actor panes to a VBox
        VBox pane = new VBox(10, paneCharacter, paneActor, paneRole);

        // Set the stage
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Role Player");
        primaryStage.show();
    }
}
