/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

import java.time.LocalDate;  
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import static main.MainUiController.userType2;

public class BugReporterAddController extends bugReport {
    @FXML
    private TextField idTitle;

    @FXML
    private TextArea description;

    @FXML
    private Button submitBtn;

    @FXML
    private Button cancelBtn;
  
    /**
     * Initializes the controller class.
     */
    @FXML
    void initialize(){
        

    }

    PreparedStatement pst;
    Connection con;


    @FXML
    void addSubmit(ActionEvent event) {
        String titleStr = idTitle.getText();
        String descStr = description.getText();

        submitNewBug(titleStr,descStr);
        
        submitBtn.getScene().getWindow().hide();
        
    }
    
    @FXML
    void cancel(MouseEvent event) {
        
         cancelBtn.getScene().getWindow().hide(); 
         
         
    }
    
}
