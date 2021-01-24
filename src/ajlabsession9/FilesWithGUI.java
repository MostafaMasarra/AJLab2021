/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession9;

import java.io.File;
import java.io.FilenameFilter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author bader-aul
 */
public class FilesWithGUI extends Application {

    private File[] listOfFiles;
    private File fileChosen = null;
    String res;
    Label resLabel;
    GridPane gridPane;
    ToggleGroup group;
    TextField txt;

    @Override
    public void start(Stage primaryStage) {
        VBox pane = new VBox();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(10);
        pane.setPadding(new Insets(20));

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setMinWidth(600);
        hbox.setSpacing(10);

        //access the directory named "files" in the root directory of the project
        File fileDirectory = new File("files");
        //check if directory exists
        if (fileDirectory.exists()) {
            //list the files inside the directory and save them in an array of files
            //use a FilenameFilter to save only the files with the .txt extension
            listOfFiles = fileDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File pathname, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            });

            Label label = new Label("Choose a file: ");

            //create a ComboBox of type <File> and use it to display the list of files that we have
            ComboBox<File> combo = new ComboBox<>();
            combo.getItems().addAll(listOfFiles);
            //placeholder will show if there are no files to show (e.g. chosen directory is empty)
            combo.setPlaceholder(new Label("No available files"));

            //action event of ComboBox to set the name of the chosen file and to display the GridPane below it
            combo.setOnAction((ActionEvent event) -> {

                if (fileChosen == null) {
                    //add elements to GridPane that we want to show here
                    //only if fileChosen == null so that elements are added only once, and not every time a user selects a file
                    //however, not recommended
                    //instead, use setVisible
                }
                //show gridPane
                gridPane.setVisible(true);
                //get name selected file
                fileChosen = combo.getValue();
            });
            //add label and comboBox to HBox
            hbox.getChildren().addAll(label, combo);
        } else {
            //display a message to the user that the directory doesn't exist
            hbox.getChildren().add(new Label("Chosen directory does not exist."));
        }

        //gender label
        Label l = new Label("Choose gender: ");

        //gender toggles inside a flow pane
        group = new ToggleGroup();
        RadioButton female = new RadioButton("Female");
        RadioButton male = new RadioButton("Male");
        female.setToggleGroup(group);
        male.setToggleGroup(group);

        FlowPane p = new FlowPane(female, male);
        p.setHgap(10);

        //baby name label
        Label l2 = new Label("Enter baby name: ");

        //baby name text field
        txt = new TextField();

        //search button
        Button searchBtn = new Button("Search");
        searchBtn.setOnAction(new SearchEventHandler());

        gridPane = new GridPane();
        gridPane.setVisible(false);
        gridPane.setMinWidth(600);
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        gridPane.addRow(0, l, p);
        gridPane.addRow(1, l2, txt);
        gridPane.add(searchBtn, 2, 1);

        resLabel = new Label();
        resLabel.setVisible(false);

        pane.getChildren().addAll(hbox, gridPane, resLabel);

        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("File IO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    class SearchEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //call the function readNames present in BabyNamesIO file
            BabyNamesIO.readNames();
            //get text inputted by user
            String name = txt.getText();
            //get selected radio button as cast it from Toggle to RadioButton to be able to access RadioButton method (getText())
            RadioButton selectedToggle = (RadioButton) group.getSelectedToggle();

            //if user didn't select a radio button, the selectedToggle is null
            if (selectedToggle == null) {
                //create an alert and show it to the user
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please choose a gender");
                alert.show();
            } else if (name.isEmpty()) { //OR if name.length() == 0 OR if name.equals("") i.e. empty string
                new Alert(Alert.AlertType.ERROR, "Please enter a baby name").show();
                //directly accessing show method without assigning the alert into a variable since it is not needed for later access
            } else {
                String gender = selectedToggle.getText();
                String nameOfFile = fileChosen.getName();

                /*
                not required, extra feature
                getting the year from the name of the file by using substring method
                substring method can be accessed from any string, in most programming languages
                it takes as parameters beginIndex, i.e. where to begin cutting the string
                and endIndex, i.e. where to stop cutting the string
                if endIndex is not given, it automatically continues till the end of the string
                and returns the cut string

                in our case, we need the string starting from nameOfFile.length() - 8, till nameOfFile.length() - 4
                E.g.
                Babynamesranking|2002|.txt
                      length - 8|    |length - 4
                 */
                String yearStr = nameOfFile.substring(nameOfFile.length() - 8, nameOfFile.length() - 4);
                //convert the String returned to integer
                int year = Integer.parseInt(yearStr);

                //get the ranking returned from SearchArray method found in BabyNamesIO file
                int ranking = BabyNamesIO.SearchArray(year, gender, name);

                //SearchArray returns -1 if name is not found
                if (ranking != -1) {
                    res = name + " is ranked #" + (ranking + 1) + " in year " + year + " (" + gender + ")";
                } else {
                    res = "The name " + name + " is not ranked in year " + year + " (" + gender + ")";
                }
                //show results in resLabel
                resLabel.setText(res);
                resLabel.setVisible(true);
            }
        }
    }

}
