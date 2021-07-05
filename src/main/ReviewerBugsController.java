/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class ReviewerBugsController extends ReviewerBugs implements Initializable {

    @FXML
    private SplitMenuButton myMenuBar;
    
    @FXML
    private TableView<bugReport> myTable;

    @FXML
    private TextField titleFilter;

    @FXML
    private TextField assigneeFilter;

    @FXML
    private TextField keywordFilter;

    
    BoxBlur blur = new BoxBlur(3, 3, 3);
       
    
    ObservableList<bugReport> ObserveList = FXCollections.observableArrayList();
    
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

            TableColumn assignee = new TableColumn("Fixed By");
            assignee.setStyle( "-fx-alignment: CENTER;");
            assignee.setMinWidth(100);

            TableColumn bug = new TableColumn("Bug Status");
            bug.setStyle( "-fx-alignment: CENTER;");
            bug.setMinWidth(100);

         myMenuBar.setText(userType2); //assign username to splitmenubutton
         //get values from bug report
         viewBugRptRev(myTable,ObserveList,userType,bugID, description,title,assignee, bug);

         //set values
         setValues(myTable,ObserveList,bugID, description,title,assignee, bug);
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
    void searchAssgnRev(ActionEvent event) {
        String AFilter = assigneeFilter.getText();
        searchARev(AFilter,ObserveList); 
    }

    @FXML
    void searchKeyRev(ActionEvent event) {
        String KFilter = keywordFilter.getText();
        searchKRev(KFilter,ObserveList); 
    }

    @FXML
    void searchTitleRev(ActionEvent event) {
        String TFilter = titleFilter.getText();
        searchTRev(TFilter,ObserveList); 
    }
    
    @FXML
    void gotoSettings(ActionEvent event) throws IOException {
        pageNameSett = "RevBugs";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
    
    
    
}