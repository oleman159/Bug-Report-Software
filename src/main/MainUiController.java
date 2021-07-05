/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUiController extends User implements Initializable  {


    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField pass_hide;

    @FXML
    private FontAwesomeIconView icon2;

    @FXML
    private JFXTextField pass_show;

    @FXML
    private FontAwesomeIconView icon1;
    
    //public static int accountID;
    
    

     @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.icon1.setVisible(false);
        this.pass_show.setVisible(false);
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
    void gotoLogin(ActionEvent event) throws IOException{
        login(event,username,pass_hide);
    }


    @FXML
    void hidePassword(MouseEvent event) {
        
        
        icon1.setVisible(false);
        icon2.setVisible(true);
        pass_hide.setText(pass_show.getText());
        pass_hide.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(" Text Changed to  " + newValue + ")\n");
            pass_hide.setText(newValue);
        });
        pass_show.setVisible(false);
        pass_hide.setVisible(true);
        
    }
    
    @FXML
    void showPassword(MouseEvent event) {
        icon1.setVisible(true);
        icon2.setVisible(false);
        pass_show.setText(pass_hide.getText());
        pass_show.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(" Text Changed to  " + newValue + ")\n");
            pass_hide.setText(newValue);
        });
        pass_show.setVisible(false);
        pass_hide.setVisible(true);
        pass_show.setVisible(true);
        pass_hide.setVisible(false); 
    }

}
