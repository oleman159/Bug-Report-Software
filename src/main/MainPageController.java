/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class MainPageController extends User implements Initializable {

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
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    public static int developerReportID;
    
    BoxBlur blur = new BoxBlur(3, 3, 3);
    
    ObservableList<bugReport> ObserveList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        myMenuBar.setText(userType2); //assign username to splitmenubutton

        //Creating column headers for our tableview
        TableColumn bugID = new TableColumn("Bug ID");
        bugID.setStyle( "-fx-alignment: CENTER;");

        TableColumn title = new TableColumn("Title");
        title.setStyle( "-fx-alignment: CENTER;");

        TableColumn desc = new TableColumn("Description");
        desc.setStyle( "-fx-alignment: CENTER;");
        desc.setMinWidth(100);

        TableColumn assignee = new TableColumn("Assignee");
        assignee.setStyle( "-fx-alignment: CENTER;");

        //more
        TableColumn <bugReport,bugReport> more = new TableColumn();
        more.setStyle( "-fx-alignment: CENTER;");
        more.setMinWidth(50);
        more.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        ObserveList= viewBugRpt(myTable,ObserveList,userType3, bugID,  title,  desc,  assignee,more);
        setValue( bugID,  title,  desc,  assignee, ObserveList, myTable);
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
        pageNameSett = "Main";
        Settings(myMenuBar);   
    }  
    
    @FXML
    void searchAssgn(ActionEvent event) {
        String AFilter = assigneeFilter.getText();
        ObserveList=searchA(AFilter,ObserveList);
    }

    @FXML
    void searchKey(ActionEvent event) {
        String KFilter = keywordFilter.getText();
        ObserveList= searchK(KFilter,ObserveList);
    }

    @FXML
    void searchTitle(ActionEvent event) {
        String TFilter = titleFilter.getText();
        ObserveList= searchT(TFilter,ObserveList);
    }
    
    @FXML
    void gotoAction(MouseEvent event) throws IOException {
        action(event,myMenuBar,userType3);
     
    }
    
}
