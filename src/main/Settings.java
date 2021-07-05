/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.MainUiController.userType2;


/**
 * FXML Controller class
 *
 * @author Nabeel
 */
public class Settings extends User implements Initializable {
    
    public static String pageNameSett;
    
    @FXML
    private SplitMenuButton myMenuBar;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    BoxBlur blur = new BoxBlur(3, 3, 3);
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myMenuBar.setText(userType2); 
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
    void goBack2(MouseEvent event) throws IOException {
        
        if(pageNameSett.equals("BugRep")){
            Parent fxml = FXMLLoader.load(getClass().getResource("BugReporterMain.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }

        else if(pageNameSett.equals("DevBugs")){
            Parent fxml = FXMLLoader.load(getClass().getResource("DeveloperBugs.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("DevMain")){
            Parent fxml = FXMLLoader.load(getClass().getResource("DeveloperMain.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("RevMain")){
            Parent fxml = FXMLLoader.load(getClass().getResource("ReviewerMain.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("RevBugs")){
            Parent fxml = FXMLLoader.load(getClass().getResource("ReviewerBugs.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("TriMain")){
            Parent fxml = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("TriAss")){
            Parent fxml = FXMLLoader.load(getClass().getResource("TriagerAssign.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("TriRev")){
            Parent fxml = FXMLLoader.load(getClass().getResource("TriagerReview.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show();
        
        }
        
        else if(pageNameSett.equals("Main")){

            Parent fxml = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

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

            Scene fxmlRegister = new Scene(fxml);
            stage.setScene(fxmlRegister);
            stage.show(); 
        }
    }
    
    @FXML
    void gotoLogout(MouseEvent event) throws IOException {
        
        logout(myMenuBar);
    } 
}
