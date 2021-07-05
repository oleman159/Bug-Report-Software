package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 *
 * @author Nabeel
 */
public class main extends Application {
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
        
        stage.initStyle(StageStyle.TRANSPARENT); 
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Result result = JUnitCore.runClasses(userTest.class);
       
        System.out.println("Test Results: " + result.wasSuccessful());
       
        for(Failure failure : result.getFailures())
        {
            System.out.println("Failure : " + failure.toString() );
        }
        
        launch(args);
    }
    
}
