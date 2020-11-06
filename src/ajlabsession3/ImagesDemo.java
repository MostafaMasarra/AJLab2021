/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Bader
 */
public class ImagesDemo extends Application {
    
    Text createTextComponent(){
        Text text1 = new Text(10, 20, "Programming is fun.");
        text1.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 22));
        text1.setFill(Color.GREEN);
        text1.setUnderline(true);
        text1.setStrikethrough(true);
        return text1;
    }
    
    ImageView createImageFromURL(String imageURL){
        Image image = new Image(imageURL, 200, 200, true, true);
        ImageView imageView = new ImageView(image);
        return imageView;
    }
    
    ImageView createImageFromPath (String imagePath) throws FileNotFoundException{
        InputStream in = new FileInputStream(imagePath);
        Image image2 = new Image(in);
        ImageView imageView2 = new ImageView(image2);
        imageView2.setX(50);
        imageView2.setY(50);
        imageView2.setFitHeight(200);
        imageView2.setFitWidth(200);
        imageView2.setPreserveRatio(true);
        return imageView2;
    }
    
    ImageView createImageFromPath_2 (String imagePath){
        ImageView imageView = new ImageView("file:///" + imagePath);
        return imageView;
    }

    //we can also set horizontal alignment, vertical alignment, horizontal gap, vertical gap
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException { //take note
        VBox vBoxPane = new VBox();
        //TODO: add components here
        vBoxPane.setPadding(new Insets(10));
        vBoxPane.setStyle("-fx-background-color: red");
        vBoxPane.setAlignment(Pos.CENTER);

        //All in VBOX 
        //add first component to vbox
        vBoxPane.getChildren().add(createTextComponent());

        //Images in HBOX
        HBox hBoxPane = new HBox();
        hBoxPane.setPadding(new Insets(20));
        hBoxPane.setAlignment(Pos.CENTER);
        hBoxPane.setSpacing(15);
        
        //add second component to hbox
        String imageURL = "https://homepages.cae.wisc.edu/~ece533/images/fruits";
        hBoxPane.getChildren().add(createImageFromURL(imageURL));

        //add third component to hbox
        String imagePath = "C:\\Users\\Bader\\Documents\\NetBeansProjects\\AJLab2021\\girl.png";
        hBoxPane.getChildren().add(createImageFromPath(imagePath));
        
        //add same image using different method
        hBoxPane.getChildren().add(createImageFromPath_2(imagePath));
        
        //add hbox to vbox
        vBoxPane.getChildren().add(hBoxPane);
        
        //Labels in HBOX
        HBox hBoxPane2 = new HBox();
        hBoxPane2.setPadding(new Insets(20));
        hBoxPane2.setAlignment(Pos.CENTER);
        hBoxPane2.setSpacing(15);
        
       
        Label [] labels = {new Label("Javascript"), new Label("Java"), new Label("PHP"), new Label("Python")};
        for (Label label : labels) {
            label.setRotate(30);
            hBoxPane2.getChildren().add(label); //this OR addALL
        }
        //hBoxPane2.getChildren().addAll(labels);
        
        //add hbox2 to vbox
        vBoxPane.getChildren().add(hBoxPane2);
        

        Scene scene = new Scene(vBoxPane, 300, 250);

        primaryStage.setTitle("Text Demo");
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
