/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static main.CommentMainController.pageName;
import static main.ReviewerMainController.reviewerReportID;
import static main.User.getConnection;

public class ReviewerBugs extends User{
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    private String updateChoice;

    public void setValues(TableView myTable,ObservableList ObserveList, TableColumn bugID,TableColumn description,TableColumn title,TableColumn assignee,TableColumn bug){
        bugID.setCellValueFactory(new PropertyValueFactory<>("bugReportID"));
        title.setCellValueFactory(new PropertyValueFactory<>("bugTitle"));
        description.setCellValueFactory(new PropertyValueFactory<>("bugDescription"));
        bug.setCellValueFactory(new PropertyValueFactory<>("bugStatus"));
        assignee.setCellValueFactory(new PropertyValueFactory<>("assignee"));




        try {
            myTable.setItems(ObserveList);
        }
        catch (NullPointerException ex ){
            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void searchARev(String AFilter, ObservableList<bugReport> ObserveList){
        if (AFilter.isEmpty()){
            try{
            Connection con = getConnection();
            ObserveList.clear();
            
            PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugStatus = 'Completed'  ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));
            }

        }catch(SQLException ex){
            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
        }
        }else{
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee = ? AND bugStatus = 'Completed' ");
                pst.setString(1,AFilter);

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));
                }

            }catch(SQLException ex){
                Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    public void searchKRev(String KFilter, ObservableList<bugReport> ObserveList){
        if (KFilter.isEmpty()){
            try{
            Connection con = getConnection();
            ObserveList.clear();
            
            PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugStatus = 'Completed' ");
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));
            }

        }catch(SQLException ex){
            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
        }
        }else{
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugDescription LIKE ? AND bugStatus = 'Completed' ");
                pst.setString(1,"%"+KFilter+"%");

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));
                }

            }catch(SQLException ex){
                Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    public void searchTRev(String TFilter, ObservableList<bugReport> ObserveList) {
        if (TFilter.isEmpty()){
            try{
            Connection con = getConnection();
            ObserveList.clear();
            
            PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugStatus = 'Completed' ");
            
           
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));
            }

        }catch(SQLException ex){
            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
        }
        }else{
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugTitle = ? AND bugStatus = 'Completed'");
                pst.setString(1,TFilter);

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));
                }

            }catch(SQLException ex){
                Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
}
