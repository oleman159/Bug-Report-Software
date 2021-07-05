package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static main.MainUiController.userType2;
import static main.Settings.pageNameSett;

/**
 * FXML Controller class
 *
 * @author Nabeel
 */
public class ReviewerMainController extends User implements Initializable {

        
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private SplitMenuButton myMenuBar;

    
    BoxBlur blur = new BoxBlur(3, 3, 3);
    
    
    public static int reviewerReportID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         myMenuBar.setText(userType2); //assign username to splitmenubutton
    }
    
       @FXML
    void goToReviewBugs(MouseEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("ReviewerBugs.fxml"));
        Scene window3 = new Scene(fxml); 
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        stage.setScene(window3);
        stage.show(); 
        
        fxml.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
             public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
             }
        });

        fxml.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
             public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
             }
        });

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
        pageNameSett = "RevMain";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
    

}
