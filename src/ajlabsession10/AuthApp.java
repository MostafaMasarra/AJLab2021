/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author bader-aul
 */
public class AuthApp extends Application {

    private Stage primaryStage;
    private Scene originalScene, signInScene, signUpScene;
    private Button signIn, signUp;

    private Scene SignInForm() {
        Label usernameLabel = new Label("Username");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username"); //placeholder text

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button backButton = new Button("Back");
        backButton.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(originalScene); //change scenes
            primaryStage.centerOnScreen(); //keep stage centered to screen even when dimensions are changed
        });

        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String error = "", username = usernameField.getText(), password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    error = "Fields cannot be empty";
                } else {
                    File file = new File("files/users.txt");

                    try {
                        Scanner s = new Scanner(file);
                        boolean found = false;

                        while (s.hasNext()) {
                            if (s.next().equals(username)) {
                                s.next(); //skip email
                                s.next(); //skip phone
                                if (s.next().equals(password)) {
                                    new Alert(Alert.AlertType.CONFIRMATION).show();
                                    found = true;
                                    break;
                                } else {
                                    s.next(); //skip country
                                }
                            }
                        }

                        if (!found) {
                            error = "Incorrect username or password";
                        }

                    } catch (FileNotFoundException ex) {
                        System.err.println(ex);
                        error = "File not found";
                    }

                }
                if (!error.equals("")) {
                    new Alert(Alert.AlertType.ERROR, error).show();
                }
            }
        });

        //adding backButton & signInButton to HBox to make them take one column in gridPane instead of two
        HBox hbox = new HBox(backButton, signInButton);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setSpacing(10);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.addRow(0, usernameLabel, usernameField);
        gridPane.addRow(1, passwordLabel, passwordField);
        gridPane.add(hbox, 1, 2);

        return new Scene(gridPane, 500, 500);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Image backgroundImage = new Image("File:images/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);

        signIn = new Button("Sign In");
        signUp = new Button("Sign Up");

        signIn.setOnAction(new NavHandler());
        signUp.setOnAction(new NavHandler());

        VBox vbox = new VBox(signIn, signUp);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, vbox);

        originalScene = new Scene(root, 400, 400);
        signInScene = this.SignInForm();
        signUpScene = new Scene(new SignUpForm(this).getParentPane(), 1000, 1000);

        primaryStage.setTitle("My Application");
        primaryStage.setScene(originalScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    void changeScene(String nameOfScene) {
        if (nameOfScene.equals("Sign In"));
        primaryStage.setScene(signInScene);
        primaryStage.centerOnScreen();
    }

    public class NavHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Button sourceBtn = (Button) event.getSource();
            if (sourceBtn.getText().equals("Sign In")) { //not recommended because if I change name of button I'll have to manually change this condition
                primaryStage.setScene(signInScene);
            } else if (sourceBtn.equals(signUp)) {
                primaryStage.setScene(signUpScene);
            }
            primaryStage.centerOnScreen();
        }
    }

}
