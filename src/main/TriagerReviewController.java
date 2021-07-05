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
public class TriagerReviewController extends TriagerReview implements Initializable {

        
    
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private SplitMenuButton myMenuBar;

    @FXML
    private TextField titleFilter;

    @FXML
    private TextField assigneeFilter;

    @FXML
    private TextField keywordFilter;
    
    BoxBlur blur = new BoxBlur(3, 3, 3);
    
    @FXML
    private TableView myTable;
    
    final ObservableList<bugReport> list = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        myMenuBar.setText(userType2); //assign username to splitmenubutton
        //Creating column headers for our tableview
        TableColumn bugID = new TableColumn("Bug ID");
        bugID.setStyle( "-fx-alignment: CENTER;");

        TableColumn title = new TableColumn("Title");
        title.setStyle( "-fx-alignment: CENTER;");

        TableColumn description = new TableColumn("Description");
        description.setStyle( "-fx-alignment: CENTER;");
        description.setMinWidth(100);

        TableColumn assignee = new TableColumn("Fixed By");
        assignee.setStyle( "-fx-alignment: CENTER;");
        assignee.setMinWidth(100);

        //more
        TableColumn <bugReport,bugReport> more = new TableColumn();
        more.setStyle( "-fx-alignment: CENTER;");
        more.setMinWidth(50);
        more.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        //close
        TableColumn <bugReport,bugReport> close = new TableColumn();
        close.setStyle( "-fx-alignment: CENTER;");
        close.setMinWidth(50);
        close.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        //get values from bug report
        viewBugRptTriR(myTable,list,userType,bugID,title,description, assignee, more, close);
        //set values to table
        setValue(bugID,title,description, assignee, more, close, myTable, list);
         
    }
    
    
    @FXML
    void goToBugsAssign(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("TriagerAssign.fxml"));
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
    void searchAssgnTriR(ActionEvent event) {
        String AFilter = assigneeFilter.getText();
        searchATriR(AFilter,list);
    }

    @FXML
    void searchKeyTriR(ActionEvent event) {
        String KFilter = keywordFilter.getText();
        searchKTriR(KFilter,list);  
    }

    @FXML
    void searchTitleTriR(ActionEvent event) {
        String TFilter = titleFilter.getText();
        searchTTriR(TFilter,list); 
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
        pageNameSett = "TriRev";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
    
    
}
