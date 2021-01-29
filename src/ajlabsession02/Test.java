package ajlabsession02;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {
    private String textName;
    
    //default constructor
    public Test() {
        System.out.println("Test constructor is invoked");
    }
    
    //constructor with parameter
    public Test(String textName){
        this.textName = textName;
    }
    
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        System.out.println("start method is invoked");
    }

    public static void main(String[] args) {
        System.out.println("launch application");
        Application.launch(args);
    }
}