package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.MainUiController.userType2;
import static main.Settings.pageNameSett;

/**
 * FXML Controller class
 *
 * @author Nabeel
 */

public class BugReporterMainController extends User implements Initializable  {

    /**
     * Initializes the controller class.
     */
        
    private double xOffset = 0;
    private double yOffset = 0;
    
    public static int bugreporterReportID;
    
    @FXML
    private SplitMenuButton myMenuBar;

    @FXML
    private Button addBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myMenuBar.setText(userType2); //assign username to splitmenubutton
    }    

    
    BoxBlur blur = new BoxBlur(3, 3, 3);
    bugReport bR = new bugReport();

    @FXML
    void addBugs(MouseEvent event) throws IOException {
        bR.addnewBug(event, addBtn);
    }

    
    @FXML
    void close(MouseEvent event) {       
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       stage.close();      
    }
    
    @FXML
    private void maximise(MouseEvent event) {
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       stage.setFullScreenExitHint(" ");
       stage.setFullScreen(true);
    }
    
    @FXML
    private void minimise(MouseEvent event) {
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       stage.setIconified(true);
    }
    
    @FXML
    void gotoSettings(ActionEvent event) throws IOException { 
        pageNameSett = "BugRep";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
}
