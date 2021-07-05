/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;


public class CommentAddController extends User implements Initializable {
  
    @FXML
    private TextArea commentLabel;

    @FXML
    private Button cancelBtn;
    
    User user = new User();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        // TODO
        //Test correct values taken from other pages
        System.out.println(user.getAccountID2());
        System.out.println(MainUiController.Username);
        System.out.println(user.bugReportID);
        
    }    

    @FXML
    void addSubmit(MouseEvent event) throws IOException {
        addComment(event,commentLabel,user.bugReportID,user.getAccountID2());
    }

    @FXML
    void cancel(MouseEvent event) {
        cancelBtn.getScene().getWindow().hide(); 
    }
}
