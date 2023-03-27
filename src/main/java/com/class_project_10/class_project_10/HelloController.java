package com.class_project_10.class_project_10;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnLock;

    @FXML
    private Button btnOpen;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblCharacters;

    @FXML
    private MenuItem menuExit;

    @FXML
    private MenuItem menuOpen;

    @FXML
    private MenuItem menuSave;

    @FXML
    private TextArea txtEditor;

    FileChooser dialogbox = new FileChooser();

    @FXML
    void getCharacters(KeyEvent event) {
    lblCharacters.setText("Characters Typed:"+ txtEditor.getText().length()+" character(s)");
    }

    @FXML
    void onEdit(ActionEvent event) {
txtEditor.setEditable(true);
    }

    @FXML
    void onExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onLock(ActionEvent event) {
txtEditor.setEditable(false);
    }

    @FXML
    void onOpen(ActionEvent event) {
        //SET FILTERS FOR CERTAIN FILE TYPES AND FOLDERS TO SHOW
        dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File(*.txt)", "*.txt"));

       //SHOW THE OPEN FILE DIALOG BOX
       File selectedFile = dialogbox.showOpenDialog(new Stage());

       //CHECK IF USER SELECTED FILE
        if (selectedFile == null) {
            //DO NOTHING, THE USER DOESN'T WANT OT OPEN A FILE
        }else{
            //CLEAR TEXT EDITOR
            txtEditor.setText("");
            //DISPLAY CONTENT OF TEXT FILE IN EDITOR
            Scanner KeyIn = null;
            try {
                KeyIn = new Scanner(selectedFile);
            } catch (FileNotFoundException e) {
                e.getMessage();
            }
            while(KeyIn.hasNextLine()){
            //    txtEditor.setText(KeyIn.nextLine());
                txtEditor.appendText(KeyIn.nextLine()+ "\n");
            }
            KeyIn.close();
            lblCharacters.setText("Characters Typed:"+ txtEditor.getText().length()+" character(s)");

        }
    }

    @FXML
    void onSave(ActionEvent event) {
    dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
        File selectedFile = dialogbox.showSaveDialog(new Stage());

        if (selectedFile == null) {
            //DO NOTHING, THE USER DOESN'T WANT OT OPEN A FILE

        }else {
            PrintWriter output = null;
            try {
                output = new PrintWriter(selectedFile);
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

            String text = txtEditor.getText();
            output.println(text);
            output.close();

            //CLEAR CONTENT
            txtEditor.setText("");

            lblCharacters.setText("Characters Typed:"+ txtEditor.getText().length()+" character(s)");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //SET DEFAULT DIRECTORY FOR OPEN/SAVE DIALOG BOX
        dialogbox.setInitialDirectory(new File("C:\\Users\\bjarm\\Desktop\\Java\\Class Project 10\\Save Default"));
    }

}
