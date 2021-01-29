/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author bader-aul
 */
public final class SignUpForm extends Pane {

    //list of countries names
    private final String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    private Pane parentPane;
    private ToggleGroup radioGroup;
    private CheckBox arabicCheckBox, englishCheckBox;
    private final Label errorLabel;

    //return the created pane in order to be accessed in AuthApp file
    public Pane getParentPane() {
        return parentPane;
    }

    //set current pane
    public void setParentPane(Pane parentPane) {
        this.parentPane = parentPane;
    }

    public SignUpForm(AuthApp app) {
        Label nameLabel = new Label("Name");
        TextField nameTextField = new TextField();

        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField();

        Label phoneLabel = new Label("Phone");
        TextField phoneTextField = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordTextField = new PasswordField();

        Label confirmLabel = new Label("Confirm");
        PasswordField confirmTextField = new PasswordField();

        Label countryLabel = new Label("Country");
        ComboBox<String> countryComboBox = new ComboBox<>();
        countryComboBox.getItems().addAll(countries);
        countryComboBox.setPromptText("Select Country");

        Label genderLabel = new Label("Gender");
        radioGroup = new ToggleGroup();
        RadioButton femaleRadioButton = new RadioButton("Female");
        RadioButton maleRadioButton = new RadioButton("Male");
        femaleRadioButton.setToggleGroup(radioGroup);
        maleRadioButton.setToggleGroup(radioGroup);

        HBox radioButtonsHBox = new HBox(10, femaleRadioButton, maleRadioButton); //adding radio buttons to HBox to let them take one column instead of two

        Label languageLabel = new Label("Language");
        arabicCheckBox = new CheckBox("Arabic");
        englishCheckBox = new CheckBox("English");
        HBox checkBoxesHBox = new HBox(10, arabicCheckBox, englishCheckBox); //adding check boxes to HBox to let them take one column instead of two

        Label aboutYouLabel = new Label("About You");
        TextArea aboutYouTextArea = new TextArea("Tell us more about yourself.");

        Button alreadyLoggedIn = new Button("Already logged in?");
        Button signUpButton = new Button("Sign Up");
        signUpButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); //allowing signUpButton to grow in size

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.addRow(0, nameLabel, nameTextField);
        gridPane.addRow(1, emailLabel, emailTextField);
        gridPane.addRow(2, phoneLabel, phoneTextField);
        gridPane.addRow(3, passwordLabel, passwordTextField);
        gridPane.addRow(4, confirmLabel, confirmTextField);
        gridPane.addRow(5, countryLabel, countryComboBox);
        gridPane.addRow(6, genderLabel, radioButtonsHBox);
        gridPane.addRow(7, languageLabel, checkBoxesHBox);
        gridPane.addRow(8, aboutYouLabel, aboutYouTextArea);
        gridPane.addRow(9, alreadyLoggedIn, signUpButton);
        gridPane.add(errorLabel, 1, 10);

        alreadyLoggedIn.setOnAction((ActionEvent event) -> {
            app.changeScene("Sign In");
        });

        signUpButton.setOnAction((ActionEvent event) -> {
            this.signUp(nameTextField.getText(), emailTextField.getText(), phoneTextField.getText(), passwordTextField.getText(), confirmTextField.getText(), countryComboBox.getValue());
        });

        setParentPane(gridPane);
    }

    private void signUp(String name, String email, String phone, String password, String confirm, String country) {
        String error = "";

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            error = "Please fill out all fields.";
        } else if (country == null) {
            error = "Please select a country";
        } else if (name.matches(".*\\d.*")) { // name contains digits
            error = "Name cannot contain digits";
        } else if (!email.contains("@") || !email.contains(".")) {
            error = "invalid email";
        } else if (phone.length() != 8) {
            error = "Phone number must be 8 digits long.";
        } else if (!password.equals(confirm)) {
            error = "Passwords do not match";
        } else {
            File file = new File("files/users.txt"); //this is on macOS => windows

            try {

                if (file.createNewFile()) {
                    System.out.println("File successfully created");
                    PrintWriter pw = new PrintWriter(file);
                    pw.print(name);
                    pw.print("\t");
                    pw.print(email);
                    pw.print("\t");
                    pw.print(phone);
                    pw.print("\t");
                    pw.print(password);
                    pw.print("\t");
                    pw.print(country);
                    pw.print("\n");
                    pw.close();
                } else {
                    System.out.println("File already exits");

                    //in order to append to the end of the file, we need to use FileWriter
                    //Here true is to append the content to file
                    FileWriter fileWriter = new FileWriter(file, true);

//                    BufferedWriter writer give better performance
//                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                    PrintWriter provides more flexibility
//                    PrintWriter printWriter = new PrintWriter(file);
                    fileWriter.write(name);
                    fileWriter.write("\t");
                    fileWriter.write(email);
                    fileWriter.write("\t");
                    fileWriter.write(phone);
                    fileWriter.write("\t");
                    fileWriter.write(password);
                    fileWriter.write("\t");
                    fileWriter.write(country);
                    fileWriter.write("\n");

                    //Closing BufferedWriter Stream
                    fileWriter.close();
                }

                //Note that we can use fileWriter anyway disregarding of if condition
                Alert success = new Alert(Alert.AlertType.CONFIRMATION);
                success.show();

            } catch (IOException ex) {
                System.err.println(ex);
                error = "Error writing to file";
            }

        }
        errorLabel.setText(error);
    }

    //not used: just to demonstrated how to get selected radio button
    public String getSelectedGender() {
        //need to cast to RadioButton to access its functions, such as getText(), because getSelectedToggle returns object of type ToggleButton which is the parent of RadioButton
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        if (selectedRadioButton == null) {
            return "Nothing selected";
        } else {
            return selectedRadioButton.getText();
        }
    }

    //not used: just to demonstrate how to get selected check boxes
    public String[] getCheckedLanguages(CheckBox... checkboxes) { //takes any amount of checkboxes
        String[] languages = new String[checkboxes.length];
        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].isSelected()) {
                languages[i] = checkboxes[i].getText();
            }
        }
        return languages;
    }
}
