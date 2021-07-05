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

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

import static main.BugReporterMainController.bugreporterReportID;
import static main.CommentMainController.pageName;
import static main.DeveloperMainController.developerReportID;
import static main.MainUiController.Username;
import static main.ReviewerMainController.reviewerReportID;
import static main.TriagerMainController.triagerReportID;

/**
 *
 * @author seanw
 */
public class User extends bugReport{
    
    public int accountID;
    private String username;
    private String password;
    public String userType;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    Connection con;
    PreparedStatement pst,type, type2;
    ResultSet rs;
    ResultSet accType, accType2;
    
    public static String userType3, userType2;
    
    public static int accountID2;
    public static String Username;
    
    public static int bugReportID;
    
    @FXML
    private Label titleLabel, statusLabel, reporterLabel, assigneeLabel, triagerLabel, userLabel, dateLabel, bugidLabel;
    
    public User()
    {
    }   
    
    public User(String username, String password)
    {
        try
        { 
            Connection DBConnection = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");
            
            String sql = "select * from user where username=? and password=?";
            PreparedStatement Pstatement = DBConnection.prepareStatement(sql);
            Pstatement.setString(1, username);
            Pstatement.setString(2, password);
            ResultSet result = Pstatement.executeQuery();
            if (result.next())
            {
                setAccountID(result.getInt(1));
                setAccountID2(result.getInt(1));
                setUserType(result.getString(4));
                setUserType3(result.getString(4));
                DBConnection.close();
            }
            else
            {
                DBConnection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUser(username);
        setPassword(password);
    }
    
    public void setAccountID (int n1) 
    {
        this.accountID = n1;
    }
    
    public int getAccountID()
    {
        return accountID;
    }
    
    public void setAccountID2 (int n1) 
    {
        this.accountID2 = n1;
    }
    
    public int getAccountID2()
    {
        return accountID2;
    }
    
     private void setUser (String str1) 
    {
        this.username = str1;
    }
    
    public String getUser()
    {
        return username;
    }
    
    private void setPassword (String str1)
    {
        this.password = str1;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    private void setUserType (String str1)
    {
        this.userType = str1;
    }
    
    public String getUserType()
    {
        return userType;
    }
    
    private void setUserType3 (String str1)
    {
        this.userType = str1;
    }
    
    public String getUserType3()
    {
        return userType;
    }
    
    public static Connection getConnection() throws SQLException{
        Connection connection= DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?autoReconnect=true&useSSL=false","admin","FYP12345");

        return connection;
    }
    
    void login(ActionEvent event, JFXTextField username, JFXPasswordField pass_hide) throws IOException {

        String usernameStr = username.getText();
        System.out.println("Username: " + usernameStr);
        String passStr = pass_hide.getText();
        System.out.println("Password: " + passStr);
        
        if(usernameStr.equals(""))
        {
            JOptionPane.showMessageDialog( null,"Username Blank");
            System.out.println("Username not valid");
        }
        else if (passStr.equals(""))
        {
            JOptionPane.showMessageDialog( null,"Password Blank");
            System.out.println("Password not valid");
        }
        else
        {
            User user = new User(usernameStr,passStr);
            System.out.println(user.getAccountID());
            System.out.println(user.getUserType());
            
            accountID = user.getAccountID();
            Username = usernameStr;
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");

                pst =con.prepareStatement("select * from user where username=? and password=?");
                pst.setString(1,usernameStr);
                pst.setString(2,passStr);

                rs = pst.executeQuery();

                if(rs.next()){

                    type =con.prepareStatement("select userType from user where username=? and password=?");
                    type.setString(1,usernameStr);
                    type.setString(2,passStr);
                    accType=type.executeQuery();
                    
                    type2 =con.prepareStatement("select username from user where username=? and password=?");
                    type2.setString(1,usernameStr);
                    type2.setString(2,passStr);
                    accType2=type2.executeQuery();

                    

                    while(accType.next() && accType2.next()){ //while userType and username
                       userType = accType.getString("userType"); //get userType of acc
                       userType2 = accType2.getString("username"); //get username of acc 
                       userType3 = accType.getString("userType");
                       
                        switch (userType) {
                            case "br":
                                Parent BRfxml = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                                Stage BRstage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                                BRfxml.setOnMousePressed((MouseEvent event1) -> {
                                    xOffset = event1.getSceneX();
                                    yOffset = event1.getSceneY();
                                });

                                BRfxml.setOnMouseDragged((MouseEvent event1) -> {
                                    BRstage.setX(event1.getScreenX() - xOffset);
                                    BRstage.setY(event1.getScreenY() - yOffset);
                                });

                                Scene BRfxmlRegister = new Scene(BRfxml);
                                BRstage.setScene(BRfxmlRegister);
                                BRstage.centerOnScreen();
                                BRstage.show();
                                break;

                            case "dev":
                                Parent Devfxml = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                                Stage Devstage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                                Devfxml.setOnMousePressed((MouseEvent event1) -> {
                                    xOffset = event1.getSceneX();
                                    yOffset = event1.getSceneY();
                                });

                                Devfxml.setOnMouseDragged((MouseEvent event1) -> {
                                    Devstage.setX(event1.getScreenX() - xOffset);
                                    Devstage.setY(event1.getScreenY() - yOffset);
                                });

                                Scene DevfxmlRegister = new Scene(Devfxml);
                                Devstage.setScene(DevfxmlRegister);
                                Devstage.centerOnScreen();
                                Devstage.show();
                                break;

                            case "tri":
                                Parent Trifxml = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                                Stage Tristage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                                Trifxml.setOnMousePressed((MouseEvent event1) -> {
                                    xOffset = event1.getSceneX();
                                    yOffset = event1.getSceneY();
                                });

                                Trifxml.setOnMouseDragged((MouseEvent event1) -> {
                                    Tristage.setX(event1.getScreenX() - xOffset);
                                    Tristage.setY(event1.getScreenY() - yOffset);
                                });

                                Scene TrifxmlRegister = new Scene(Trifxml);
                                Tristage.setScene(TrifxmlRegister);
                                Tristage.centerOnScreen();
                                Tristage.show();
                                break;

                            case "rev":
                                Parent Revfxml = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
                                Stage Revstage = (Stage) ((Node)event.getSource()).getScene().getWindow();

                                Revfxml.setOnMousePressed((MouseEvent event1) -> {
                                    xOffset = event1.getSceneX();
                                    yOffset = event1.getSceneY();
                                });

                                Revfxml.setOnMouseDragged((MouseEvent event1) -> {
                                    Revstage.setX(event1.getScreenX() - xOffset);
                                    Revstage.setY(event1.getScreenY() - yOffset);
                                });

                                Scene RevfxmlRegister = new Scene(Revfxml);
                                Revstage.setScene(RevfxmlRegister);
                                Revstage.centerOnScreen();
                                Revstage.show();
                                break;

                            default:
                                JOptionPane.showMessageDialog(null, "Error: Missing Error code. Please contact administrator for assistance.");
                                break;
                        }
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null,"Login Failed");
                    username.setText("");
                    pass_hide.setText("");
                    username.requestFocus();
                }

            } catch (ClassNotFoundException | SQLException ex){
                Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE,null,ex);
            }
    
        }
        
    }
    
    public void action(MouseEvent event, SplitMenuButton myMenuBar,String userType) throws IOException {
        System.out.println(userType);
        try {
          if (userType.equals("br"))
            {
                Parent fxml2 = FXMLLoader.load(getClass().getResource("BugReporterMain.fxml"));
                Scene window3 = new Scene(fxml2);
                Stage parentStage = (Stage) myMenuBar.getScene().getWindow();
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
            } 
            else if(userType.equals("dev"))
            {
                Parent fxml2 = FXMLLoader.load(getClass().getResource("DeveloperMain.fxml"));
                Scene window3 = new Scene(fxml2);
                Stage parentStage = (Stage) myMenuBar.getScene().getWindow();
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
            }
            else if(userType.equals("tri"))
            {
                Parent fxml2 = FXMLLoader.load(getClass().getResource("TriagerMain.fxml"));
                Scene window3 = new Scene(fxml2);
                Stage parentStage = (Stage) myMenuBar.getScene().getWindow();
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
            }
            else if(userType.equals("rev"))
            {
                Parent fxml2 = FXMLLoader.load(getClass().getResource("ReviewerMain.fxml"));
                Scene window3 = new Scene(fxml2);
                Stage parentStage = (Stage) myMenuBar.getScene().getWindow();
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
            }  
          
        } catch (IOException ex) {
            Logger.getLogger(CommentMainController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
    }
    
    public void logout(SplitMenuButton myMenuBar) throws IOException
    { 
        ButtonType okButton = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.initStyle(StageStyle.UTILITY);;
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        alert.getButtonTypes().setAll(okButton, cancelButton);
        Optional <ButtonType> action = alert.showAndWait();

        if(action.get() == okButton) 
        {

            Parent fxml2 = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
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
        else if (action.get() == cancelButton) 
        {
            System.out.println("Back to page");
        } 
    }

    public void setValue(TableColumn bugID, TableColumn title, TableColumn desc, TableColumn assignee,ObservableList ObserveList,TableView myTable){
        bugID.setCellValueFactory(new PropertyValueFactory<>("bugReportID"));
        title.setCellValueFactory(new PropertyValueFactory<>("bugTitle"));
        desc.setCellValueFactory(new PropertyValueFactory<>("bugDescription"));
        assignee.setCellValueFactory(new PropertyValueFactory<>("assignee"));

        try {
            myTable.setItems(ObserveList);
        }
        catch (NullPointerException ex ){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    void Settings(SplitMenuButton myMenuBar) throws IOException {

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
    
    void MainPage(MouseEvent event) throws IOException {
      
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
    void viewComments(String userType3,ArrayList<bugReport> BS, TextArea descLabel, ArrayList<bugReport> CS,TableView myTable,ObservableList<bugReport> list2){
        if (userType3.equals("br"))
        {
            bugReportID = bugreporterReportID;
        } 
        else if(userType3.equals("dev"))
        {
            bugReportID = developerReportID;
        }
        else if(userType3.equals("tri"))
        {
            bugReportID = triagerReportID;
        }
        else if(userType3.equals("rev"))
        {
            bugReportID = reviewerReportID;
        }  
        
        try 
        {                
            Connection DBConnection = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");

            String sql = "SELECT * FROM bugReport WHERE bugReportID =?";
            PreparedStatement Pstatement = DBConnection.prepareStatement(sql);
            Pstatement.setInt(1, bugReportID);
            ResultSet result = Pstatement.executeQuery();
            
            while(result.next())
            {
                
                BS.add(new bugReport(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7), result.getString(8)));
                
            }
            DBConnection.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CommentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < BS.size(); i++)
        {
            String bugTitle = BS.get(i).getBugTitle();
            String bugDesc = BS.get(i).getBugDescription();
            String bugDateReported = BS.get(i).getDateReported();
            String bugStatus = BS.get(i).getBugStatus();
            String bugAssignee = BS.get(i).getAssignee();  
            String bugReporter = BS.get(i).getReporter(); 
            String bugTriager = BS.get(i).getTriager(); 
            
            System.out.println(bugReportID + "," + bugTitle + "," + bugDesc + "," + bugDateReported + "," + bugStatus + "," + bugAssignee + "," + bugReporter + "," + bugTriager);
            
            bugidLabel.setText(String.valueOf(bugReportID));
            titleLabel.setText(bugTitle);
            descLabel.setText(bugDesc);
            dateLabel.setText(bugDateReported);
            assigneeLabel.setText(bugAssignee);
            statusLabel.setText(bugStatus);
            reporterLabel.setText(bugReporter);
            triagerLabel.setText(bugTriager);
        }
              
        //comment section (functions)
        TableColumn username = new TableColumn("User");
        username.setStyle( "-fx-alignment: CENTER-LEFT;");
        username.setMaxWidth(370);
     
        
        TableColumn comment = new TableColumn("Comments");
        comment.setStyle( "-fx-alignment: CENTER-LEFT;");
        
        TableColumn commentID = new TableColumn("Comment ID");
        commentID.setStyle( "-fx-alignment: CENTER;");
        commentID.setVisible(false);
        
         TableColumn accountID = new TableColumn("Account ID");
        accountID.setStyle( "-fx-alignment: CENTER;"); 
        accountID.setVisible(false);

        try 
        {                
            Connection DBConnection = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");

            String sql = "SELECT * FROM comment WHERE bugReportID =?";
            PreparedStatement Pstatement = DBConnection.prepareStatement(sql);
            Pstatement.setInt(1, bugReportID);
            ResultSet result = Pstatement.executeQuery();

            while(result.next())
            {
                CS.add(new bugReport(result.getInt(1), result.getInt(3), result.getString(4), result.getString(5)));                
            }

            DBConnection.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(CommentMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (bugReport brObj : CS)
        {
            list2.add(new bugReport(brObj.getCommentID(), brObj.getAccountID(), brObj.getUsername() + " : ", brObj.getComment()));          
        }
        
        myTable.getColumns().addAll(username, comment, commentID, accountID);
        
        username.setCellValueFactory(new PropertyValueFactory<bugReport,String>("username"));
        comment.setCellValueFactory(new PropertyValueFactory<bugReport,String>("comment"));
        commentID.setCellValueFactory(new PropertyValueFactory<bugReport,String>("CommentID"));
        accountID.setCellValueFactory(new PropertyValueFactory<bugReport,String>("AccountID"));
        
        myTable.setItems(list2);
    }
    
    void addComment(MouseEvent event,TextArea commentLabel,int bugReportID, int accountID2) throws IOException {
        
        String getComment = commentLabel.getText();
        System.out.println(getComment);
        
        if(getComment.equals(""))
        {   
            System.out.println("Error: Blank input");
            //Show error if the websiteURL is not valid.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(null);
            alert.initStyle(StageStyle.UTILITY);
            alert.setHeaderText("Account not added. Error: Blank input.");
            alert.showAndWait();
            System.out.println("Account not added. Error: Blank input.");
        }
        else
        {     
            
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle(null);
             alert.initStyle(StageStyle.UTILITY);;
             alert.setHeaderText(null);
             alert.setContentText("Are you sure you want to submit?");
             
             //alert.showAndWait(); 
             Optional <ButtonType> action = alert.showAndWait();
                   
                if(action.get() == ButtonType.OK)
                {                 
                    
                     try {
                        Connection DBConnection = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");

                        String sql = "INSERT INTO comment(bugReportID,accountID,username,comment) value(?,?,?,?)";

                        PreparedStatement Pstatement = DBConnection.prepareStatement(sql);;
                        Pstatement.setInt(1, bugReportID);
                        Pstatement.setInt(2, accountID2);
                        Pstatement.setString(3, MainUiController.Username);
                        Pstatement.setString(4, getComment);
                        Pstatement.execute(); 
                        System.out.println("New Comment Added!");
                        DBConnection.close();
                    } 
                    catch (SQLException ex) {
                        Logger.getLogger(CommentAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }   

                }

                else
                {
                    System.out.println("Submit action was cancelled");

                }   
        }

    }
   
}
