package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.MainUiController.userType3;
import static main.MainUiController.userType2;
import static main.Settings.pageNameSett;



/**
 * FXML Controller class
 *
 * @author Nabeel
 */
public class TriagerMainController extends User implements Initializable {
    
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private SplitMenuButton myMenuBar;
    
    @FXML
    private ComboBox<String> generateCombo; 
    
    BoxBlur blur = new BoxBlur(3, 3, 3);

    
    public static int triagerReportID;   
    
    public static String generateReportID;   
    
            /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        myMenuBar.setText(userType2); //assign username to splitmenubutton
        
        ObservableList<String> generateOptions = FXCollections.observableArrayList();
        generateOptions.add("No. of Bugs reported in the Month");
        generateOptions.add("No. of Bugs resolved in the Week");
        generateOptions.add("Best Performed Bug Reporter");
        generateOptions.add("Best Performed Developer");
        
        generateCombo.getStyleClass().add("comboboxGenerate");   
        generateCombo.setItems(generateOptions);
        generateCombo.setValue("Generate Report");
        generateCombo.setOnAction(

            event -> {
                        
                if (generateCombo.getValue().equals(generateOptions.get(0)))
                {
                    try {
                        generateReportID = generateCombo.getValue();
                        System.out.println(generateReportID);
                        
                        Parent fxml2 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                        
                        
                        Parent fxml = FXMLLoader.load(getClass().getResource("GenerateReport.fxml"));
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                       // stage.initOwner(addBtn.getScene().getWindow());
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
                        
                        stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
                            if(!stage.isFocused())
                                Platform.runLater(() -> stage.close());
                        });
                        
                        
                        // showAndWait will block execution until the window closes...
                        stage.showAndWait();
                        
                        //window3 = new Scene(fxml2); 
                        Parent fxml5 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                    } catch (IOException ex) {
                        Logger.getLogger(TriagerMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (generateCombo.getValue().equals(generateOptions.get(1)))
                {
                     generateReportID = generateCombo.getValue();
                     System.out.println(generateReportID);
                     
                    try {
                        generateReportID = generateCombo.getValue();
                        System.out.println(generateReportID);
                        
                        Parent fxml2 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                        
                        
                        Parent fxml = FXMLLoader.load(getClass().getResource("GenerateReport.fxml"));
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                       // stage.initOwner(addBtn.getScene().getWindow());
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
                        
                        stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
                            if(!stage.isFocused())
                                Platform.runLater(() -> stage.close());
                        });
                        
                        
                        // showAndWait will block execution until the window closes...
                        stage.showAndWait();
                        
                        //window3 = new Scene(fxml2); 
                        Parent fxml5 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                    } catch (IOException ex) {
                        Logger.getLogger(TriagerMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (generateCombo.getValue().equals(generateOptions.get(2)))
                {
                     generateReportID = generateCombo.getValue();
                     System.out.println(generateReportID);
                     
                                         try {
                        generateReportID = generateCombo.getValue();
                        System.out.println(generateReportID);
                        
                        Parent fxml2 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                        
                        
                        Parent fxml = FXMLLoader.load(getClass().getResource("GenerateReport.fxml"));
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                       // stage.initOwner(addBtn.getScene().getWindow());
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
                        
                        stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
                            if(!stage.isFocused())
                                Platform.runLater(() -> stage.close());
                        });
                        
                        
                        // showAndWait will block execution until the window closes...
                        stage.showAndWait();
                        
                        //window3 = new Scene(fxml2); 
                        Parent fxml5 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                    } catch (IOException ex) {
                        Logger.getLogger(TriagerMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (generateCombo.getValue().equals(generateOptions.get(3)))
                {
                     generateReportID = generateCombo.getValue();
                     System.out.println(generateReportID);
                     
                    try {
                        generateReportID = generateCombo.getValue();
                        System.out.println(generateReportID);
                        
                        Parent fxml2 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                        
                        
                        Parent fxml = FXMLLoader.load(getClass().getResource("GenerateReport.fxml"));
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                       // stage.initOwner(addBtn.getScene().getWindow());
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
                        
                        stage.focusedProperty().addListener((ov, onHidden, onShown) -> {
                            if(!stage.isFocused())
                                Platform.runLater(() -> stage.close());
                        });
                        
                        
                        // showAndWait will block execution until the window closes...
                        stage.showAndWait();
                        
                        //window3 = new Scene(fxml2); 
                        Parent fxml5 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
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
                    } catch (IOException ex) {
                        Logger.getLogger(TriagerMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } 
        ); 
    }

    @FXML
    void goToAssignBugs(MouseEvent event) throws IOException {
        
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
    void goToReviewBugs(MouseEvent event) throws IOException {
        
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
        pageNameSett = "TriMain";
        Settings(myMenuBar);   
    }
    
    @FXML
    void gotoMain(MouseEvent event) throws IOException { 
        MainPage(event);   
    }
}
