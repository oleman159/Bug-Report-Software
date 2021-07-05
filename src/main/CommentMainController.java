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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.User.userType3;
import static main.BugReporterMainController.bugreporterReportID;
import static main.MainUiController.userType2;
import static main.DeveloperMainController.developerReportID;
import static main.ReviewerMainController.reviewerReportID;
import static main.Settings.pageNameSett;
import static main.TriagerMainController.triagerReportID;

/**
 * FXML Controller class
 *
 * @author Nabeel
 */
public class CommentMainController extends User implements Initializable {

    @FXML
    private SplitMenuButton myMenuBar;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    public static int bugReportID;
    
    public static String pageName;
    
    @FXML
    private TextArea descLabel;

    
      
    BoxBlur blur = new BoxBlur(3, 3, 3);
    
    @FXML
    private TableView myTable;

    
    final ObservableList<bugReport> list2 = FXCollections.observableArrayList();
        
    ArrayList<bugReport> BS = new ArrayList<bugReport>(); //under bug report section
    ArrayList<bugReport> CS = new ArrayList<bugReport>(); //under comment section
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(User.userType3);
        
        myMenuBar.setText(userType2); //assign username to splitmenubutton
        viewComments(userType3,BS, descLabel,CS,myTable,list2);
         
    }    
    
    @FXML
    void goBack(MouseEvent event) throws IOException{
        
        if(pageName.equals("DevBugs")){
            pageNameSett = "DevBugs";
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
        
        else if(pageName.equals("RevBugs")){
            pageNameSett = "RevBugs";
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
        
        else if(pageName.equals("TriAss")){
            pageNameSett = "TriAss";
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
        
        else if(pageName.equals("TriRev")){
            pageNameSett = "TriRev";
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
        
        else if(pageName.equals("Main")){
            pageNameSett = "Main";
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
    void addComments(MouseEvent event) {

         try {
                                
                Parent fxml2 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                Scene window3 = new Scene(fxml2); 
                Stage parentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                parentStage.setScene(window3);
                parentStage.show(); 

                fxml2.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                     public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                     }
                    });

                    fxml2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                          parentStage.setX(event.getScreenX() - xOffset);
                          parentStage.setY(event.getScreenY() - yOffset);
                        }
                    }); 

                Parent fxml = FXMLLoader.load(getClass().getResource("CommentAdd.fxml"));
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene window = new Scene(fxml); 
                window.setFill(null);
                stage.setScene(window);

                ChangeListener<Number> widthListener = (observable, oldValue, newValue) -> {
                double stageWidth = newValue.doubleValue();
                stage.setX(parentStage.getX() + parentStage.getWidth() / 2 - stageWidth / 2);
                 };

                ChangeListener<Number> heightListener = (observable, oldValue, newValue) -> {
                        double stageHeight = newValue.doubleValue();
                        stage.setY(parentStage.getY() + parentStage.getHeight() / 2 - stageHeight / 2);   
                };

                stage.widthProperty().addListener(widthListener);
                stage.heightProperty().addListener(heightListener);

                //Once the window is visible, remove the listeners
                stage.setOnShown(e -> {
                    stage.widthProperty().removeListener(widthListener);
                    stage.heightProperty().removeListener(heightListener);
                });

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

                fxml2.setEffect(blur);  
                // showAndWait will block execution until the window closes...    
                stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
                if(!stage.isFocused())
                    Platform.runLater(() -> stage.close());
                }); 

                // showAndWait will block execution until the window closes...
                stage.showAndWait();

                 //window3 = new Scene(fxml2); 
                Parent fxml5 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                Scene window4 = new Scene(fxml5); 
                parentStage.setScene(window4);
                parentStage.show(); 

                fxml5.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });

                fxml5.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        parentStage.setX(event.getScreenX() - xOffset);
                        parentStage.setY(event.getScreenY() - yOffset);
                    }
                });                              
            } catch (Exception e) {
               e.printStackTrace();
              }
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

        Parent fxml2 = FXMLLoader.load(getClass().getResource("Settings.fxml"));
            Scene window3 = new Scene(fxml2); 
            Stage parentStage = (Stage) myMenuBar.getScene().getWindow();
            parentStage.setScene(window3);
            parentStage.centerOnScreen();
            parentStage.show(); 

            fxml2.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                 public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                 }
                });

                fxml2.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                      parentStage.setX(event.getScreenX() - xOffset);
                      parentStage.setY(event.getScreenY() - yOffset);
                    }
                });
    }
    
    
}
