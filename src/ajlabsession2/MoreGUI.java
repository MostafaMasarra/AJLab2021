package ajlabsession2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class MoreGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        FlowPane p = new FlowPane();

        Button btn = new Button("ABC");
        p.getChildren().add(btn);

        Label lbl = new Label("DEF");
        p.getChildren().add(lbl);
        TextField txt = new TextField();
        p.getChildren().add(txt);
        TextArea txtA = new TextArea();
        p.getChildren().add(txtA);
        CheckBox chk = new CheckBox("HIJ");
        p.getChildren().add(chk);
        RadioButton rdb = new RadioButton("KLM");
        p.getChildren().add(rdb);
        ComboBox cmb = new ComboBox();
        p.getChildren().add(cmb);
        PasswordField pf = new PasswordField();
        p.getChildren().add(pf);
        ProgressBar pg = new ProgressBar();
        p.getChildren().add(pg);

        ScrollBar sb = new ScrollBar();
        p.getChildren().add(sb);

        Slider sl = new Slider();
        p.getChildren().add(sl);

        ToggleButton tb = new ToggleButton();
        p.getChildren().add(tb);

        Scene scene = new Scene(p, 300, 200);
        primaryStage.setTitle("MyJavaFX"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
