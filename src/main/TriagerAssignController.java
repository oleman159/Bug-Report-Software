package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static main.MainUiController.userType3;
import static main.MainUiController.userType2;
import static main.Settings.pageNameSett;

/**
 * FXML Controller class
 *
 * @author Nabeel
 */
public class TriagerAssignController extends TriagerAssign implements Initializable {
    
    @FXML
    private SplitMenuButton myMenuBar;
    
    @FXML
    private TableView myTable;
     
    @FXML
    private TextField titleFilter;

    @FXML
    private TextField assigneeFilter;

    @FXML
    private TextField keywordFilter;
    
    BoxBlur blur = new BoxBlur(3, 3, 3);
    
    final ObservableList<bugReport> list = FXCollections.observableArrayList();
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Creating column headers for our tableview
        TableColumn bugID = new TableColumn("Bug ID");
        bugID.setStyle( "-fx-alignment: CENTER;");

        TableColumn title = new TableColumn("Title");
        title.setStyle( "-fx-alignment: CENTER;");

        TableColumn description = new TableColumn("Description");
        description.setStyle( "-fx-alignment: CENTER;");
        description.setMinWidth(100);

        TableColumn <bugReport,bugReport> more = new TableColumn();
        more.setStyle( "-fx-alignment: CENTER;");
        more.setMinWidth(50);
        more.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));


        //assign button
        TableColumn <bugReport, bugReport> assign = new TableColumn();
        assign.setStyle( "-fx-alignment: CENTER;");
        assign.setMinWidth(50);
        assign.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        //invalid or duplicate button
        TableColumn <bugReport,bugReport> checkDuplicate = new TableColumn();
        checkDuplicate.setStyle( "-fx-alignment: CENTER;");
        checkDuplicate.setMinWidth(50);
        checkDuplicate.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        myMenuBar.setText(userType2); //assign username to splitmenubutton
        //get values from bugreport
        viewBugRptTri(myTable,list,userType, bugID,title,description,more,assign,checkDuplicate);
        //set values to table
        setValue( bugID, title, description, myTable, more, assign, checkDuplicate,  list);
    }    
    
    @FXML
    void goToBugsReview(MouseEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("TriagerReview.fxml"));
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
    
    //assigneeFilter.setVisible(false);
    @FXML
    void searchAssgnTri(ActionEvent event) {
        String AFilter = assigneeFilter.getText();
        searchATri(AFilter,list);
    }

    @FXML
    void searchKeyTri(ActionEvent event) {
        String KFilter = keywordFilter.getText();
        searchKTri(KFilter,list);  
    }

    @FXML
    void searchTitleTri(ActionEvent event) {
        String TFilter = titleFilter.getText();
        searchTTri(TFilter,list); 
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
        pageNameSett = "TriAss";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
}
