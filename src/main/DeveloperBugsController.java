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


public class DeveloperBugsController extends DeveloperBugs implements Initializable {
    
    @FXML
    private TextField titleFilter;
    
    @FXML
    private SplitMenuButton myMenuBar;

    @FXML
    private TableView<bugReport> myTable;
    
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

        TableColumn assignee = new TableColumn("Assignee");
        assignee.setStyle( "-fx-alignment: CENTER;");

        TableColumn title = new TableColumn("Title");
        title.setStyle( "-fx-alignment: CENTER;");

        TableColumn desc = new TableColumn("Description");
        desc.setStyle( "-fx-alignment: CENTER;");
        desc.setMinWidth(100);
        
        myMenuBar.setText(userType2); //assign username to splitmenubutton

        //get value from bug reporter
        ObserveList= viewBugRptDev(myTable,ObserveList,userType,bugID, desc,title,assignee);

        //set to myTable
        setTable(bugID, desc,title,assignee,ObserveList,myTable);
    }    
    
    @FXML
    void searchAssgnDev(ActionEvent event) {
        String AFilter = assigneeFilter.getText();
        searchADev(AFilter,ObserveList);  
    }

    @FXML
    void searchKeyDev(ActionEvent event) {
        String KFilter = keywordFilter.getText();
        searchKDev(KFilter,ObserveList);
    }

    @FXML
    void searchTitleDev(ActionEvent event) {
        String TFilter = titleFilter.getText();
        searchTDev(TFilter,ObserveList);  
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
    void close(MouseEvent event) {       
       Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       stage.close();      
    }
    
    
    @FXML
    void gotoSettings(ActionEvent event) throws IOException { 
        pageNameSett = "DevBugs";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
}
