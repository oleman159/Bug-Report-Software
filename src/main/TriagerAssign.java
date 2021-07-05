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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import static main.CommentMainController.pageName;
import static main.MainUiController.userType2;
import static main.TriagerMainController.triagerReportID;
import static main.User.getConnection;

public class TriagerAssign extends User{
    private double xOffset = 0;
    private double yOffset = 0;


    public void setValue(TableColumn bugID,TableColumn title,TableColumn description,TableView myTable,TableColumn more,TableColumn assign,TableColumn checkDuplicate, ObservableList list){
        bugID.setCellValueFactory(new PropertyValueFactory<>("bugReportID"));
        title.setCellValueFactory(new PropertyValueFactory<>("bugTitle"));
        description.setCellValueFactory(new PropertyValueFactory<>("bugDescription"));

        myTable.getColumns().addAll(bugID, title, description, more, assign, checkDuplicate);
        myTable.setItems(list);
    }
    
    public void searchATri(String AFilter, ObservableList<bugReport> list){
        if (AFilter.isEmpty()){
            try{
            Connection con = getConnection();
            list.clear();
            
            PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee is null");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));
            }

        }catch(SQLException ex){
            Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
        }
        }else{
            try{
                Connection con = getConnection();
                list.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee is null, assginee = ?");
                pst.setString(1,AFilter);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));
                }

            }catch(SQLException ex){
                Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    public void searchKTri(String KFilter, ObservableList<bugReport> list){
        if (KFilter.isEmpty()){
            try{
            Connection con = getConnection();
            list.clear();
            
            PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee is null");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));
            }

        }catch(SQLException ex){
            Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
        }
        }else{
            try{
                Connection con = getConnection();
                list.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee is null AND (bugDescription LIKE ? OR bugTitle LIKE ?)");
                pst.setString(1,"%"+KFilter+"%");
                pst.setString(2, "%"+KFilter+"%");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));
                }

            }catch(SQLException ex){
                Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    }
    
    public void searchTTri(String TFilter, ObservableList<bugReport> list) {
        if (TFilter.isEmpty()){
            try{
            Connection con = getConnection();
            list.clear();
            
            PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee is null");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));
            }

        }catch(SQLException ex){
            Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
        }
        }else{
            try{
                Connection con = getConnection();
                list.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee is null AND bugTitle = ?");
                pst.setString(1,TFilter);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));
                }

            }catch(SQLException ex){
                Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
            }
        } 
    }
    
    
}
