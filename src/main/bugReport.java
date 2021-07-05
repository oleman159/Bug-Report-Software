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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.swing.*;

import static main.BugReporterMainController.bugreporterReportID;
import static main.CommentMainController.pageName;
import static main.DeveloperMainController.developerReportID;
import static main.MainUiController.userType2;
import static main.ReviewerMainController.reviewerReportID;
import static main.TriagerMainController.generateReportID;
import static main.TriagerMainController.triagerReportID;
import static main.User.getConnection;

/**
 *
 * @author Nabeel
 */
public class bugReport {
    
    protected int bugReportID;
    protected String bugTitle;
    protected String bugDescription;
    protected String dateReported;
    protected String bugStatus;
    protected String assignee;
    protected String reporter;
    protected String triager;
    protected ComboBox comboxBox; //assign/update button
    protected Button button; //more button
    
    private int commentID, accountID;
    private String username, comment;

    @FXML
    private Label generateTitle, text, text2;

    @FXML
    private ComboBox<String> comboBox;

    private String userInput;

    @FXML
    private DatePicker dateWeek;
    
    public bugReport()
    {

    }
    
    public bugReport(int id, String title, String desc, String assgn){
        this.bugReportID=id;
        this.bugTitle=title;
        this.bugDescription=desc;
        this.assignee=assgn;
    }
    
    //developerBugs side
    public bugReport(int id, String title, String desc, String assgn, String status){
        this.bugReportID=id;
        this.bugTitle=title;
        this.bugDescription=desc;
        this.assignee=assgn;
        this.bugStatus=status;

    }
    
    //triagerAssign side
    public bugReport(int bugReportID, String bugTitle, String bugDescription)    
    {
        setBugReportID(bugReportID);
        setBugTitle(bugTitle);
        setBugDescription(bugDescription);
        setButton(new Button("More"));
        setComboxBox(new ComboBox());     
    }
    
    public bugReport(int n1)
    {
        try 
        {                
            Connection DBConnection = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");

            String sql = "SELECT * FROM bugReport WHERE bugReportID =?";
            PreparedStatement Pstatement = DBConnection.prepareStatement(sql);
            Pstatement.setInt(1, n1);
            ResultSet result = Pstatement.executeQuery();

            while(result.next())
            {
                setBugTitle(result.getString(2));
                setBugDescription(result.getString(3));
                setDateReported(result.getString(4));
                setBugStatus(result.getString(5));
                setAssignee(result.getString(6));
                setReporter(result.getString(7));
                setTriager(result.getString(8));
            }

            DBConnection.close();
        }
        catch (SQLException ex) {
            Logger.getLogger(bugReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        setBugReportID(n1);
    }
    
    public bugReport(int n1, String str1, String str2, String str3, String str4, String str5, String str6, String str7)
    {
        setBugReportID(n1);
        setBugTitle(str1);
        setBugDescription(str2);
        setDateReported(str3);
        setBugStatus(str4);
        setAssignee(str5);
        setReporter(str6);
        setTriager(str7);
    } 

    public int getBugReportID() {
        return bugReportID;
    } 

    public void setBugReportID(int bugReportID) {
        this.bugReportID = bugReportID;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getBugDescription() {
        return bugDescription;
    }

    public void setBugDescription(String bugDescription) {
        this.bugDescription = bugDescription;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getTriager() {
        return triager;
    }

    public void setTriager(String triager) {
        this.triager = triager;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public bugReport(int commentID, int accountID, String username, String comment) //for comment section
    {
        setCommentID(commentID);
        setAccountID(accountID);
        setUsername(username);
        setComment(comment);
    }
    
    //added by Nuh 28/10/20
    public Button getButton()
    {
        return button;       
    }
    
    private void setButton(Button button)
    {
        this.button = button;       
    }
    
    public ComboBox getComboxBox()
    {
        return comboxBox;       
    }
    
    private void setComboxBox(ComboBox comboxBox)
    {
        this.comboxBox = comboxBox;       
    }

    private double xOffset = 0;
    private double yOffset = 0;
    BoxBlur blur = new BoxBlur(3, 3, 3);

    public void addnewBug(MouseEvent event, Button addBtn) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("BugReporterMain.fxml"));
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


        Parent fxml = FXMLLoader.load(getClass().getResource("BugReporterAdd.fxml"));
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initOwner(addBtn.getScene().getWindow());
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
        Parent fxml5 = FXMLLoader.load(getClass().getResource("BugReporterMain.fxml"));
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
    }

    PreparedStatement pst;
    Connection con;
    public void submitNewBug(String titleStr,String descStr){

        System.out.println("Title: " + titleStr);
        System.out.println("Description: " + descStr);

        if(titleStr.equals(""))
        {
            JOptionPane.showMessageDialog( null,"Bug title Blank");
            System.out.println("Bug title not valid");
        }
        else if (descStr.equals(""))
        {
            JOptionPane.showMessageDialog( null,"Description is Blank");
            System.out.println("Description not valid");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(null);
            alert.initStyle(StageStyle.UTILITY);;
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to submit?");

            //alert.showAndWait();
            Optional<ButtonType> action = alert.showAndWait();

            if(action.get() == ButtonType.OK)
            {

                try{
                    Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");

                    pst =con.prepareStatement("insert into bugReport(bugTitle,bugDescription,dateReported,bugStatus, reporter ) values (?,?,?,?,?)");
                    pst.setString(1,titleStr);
                    pst.setString(2,descStr);
                    pst.setString(3,getDate());
                    pst.setString(4, "new");
                    pst.setString(5, userType2);

                    pst.execute();

                    System.out.println("Query executed");
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }

            }

            else
            {
                System.out.println("Submit action was cancelled");

            }
        }
    }

    public String getDate(){
        LocalDate myDateObj = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = myDateObj.format(myFormatObj);

        return formattedDate;

    }

    public void generateReport(){
        generateTitle.getStyleClass().add("generateReportTitle");
        text.getStyleClass().add("generateReportLabel");
        text2.getStyleClass().add("generateReportLabel");

        if (generateReportID.equals("No. of Bugs reported in the Month"))
        {
            dateWeek.setVisible(false);
            generateTitle.setText("No. of Bugs reported in the Month");

            ObservableList<String> generateMonths = FXCollections.observableArrayList();
            generateMonths.add("January");
            generateMonths.add("February");
            generateMonths.add("March");
            generateMonths.add("April");
            generateMonths.add("May");
            generateMonths.add("June");
            generateMonths.add("July");
            generateMonths.add("August");
            generateMonths.add("September");
            generateMonths.add("October");
            generateMonths.add("November");
            generateMonths.add("December");

            comboBox.getStyleClass().add("comboboxGenerate");
            comboBox.setItems(generateMonths);
            comboBox.setValue("Months");


            comboBox.setOnAction (

                    event -> {

                        for (int i = 0; i < generateMonths.size(); i++) {

                            if (comboBox.getValue().equals(generateMonths.get(i)))
                            {
                                // System.out.println(comboBox.getValue());
                                userInput = comboBox.getValue();
                                System.out.println(userInput);

                                int reportCount=0;

                                switch(userInput){

                                    case "January":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-01-01\" and \"2020-01-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "February":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-02-01\" and \"2020-02-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "March":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-03-01\" and \"2020-03-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "April":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-04-01\" and \"2020-04-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "May":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-05-01\" and \"2020-05-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "June":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-06-01\" and \"2020-06-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "July":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-07-01\" and \"2020-07-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "August":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-08-01\" and \"2020-08-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "September":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-09-01\" and \"2020-09-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "October":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-10-01\" and \"2020-10-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "November":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-11-01\" and \"2020-11-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    case "December":
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported between \"2020-12-01\" and \"2020-12-31\"");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                reportCount++;
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }
                                        break;
                                    default:
                                        break;
                                }

                                //get from database

                                text.setText("Number of Bugs reported in the month of " + userInput +": " + reportCount);

                            }

                        }
                    }

            );



        }
        else if (generateReportID.equals("No. of Bugs resolved in the Week"))
        {
            generateTitle.setText("No. of Bugs resolved in the week starting");

            comboBox.setVisible(false);

            dateWeek.setOnAction(
                    event ->{
                        System.out.println(dateWeek.getValue());

                        LocalDate current = dateWeek.valueProperty().get();
                        int count= 0;
                        java.sql.Date sqlDate = java.sql.Date.valueOf(dateWeek.getValue());
                        try{
                            Connection con = getConnection();
                            PreparedStatement pst =con.prepareStatement("SELECT dateReported FROM bugReport WHERE dateReported BETWEEN ? AND (SELECT DATE_ADD(?, INTERVAL 7 DAY))");
                            pst.setDate(1,sqlDate);
                            pst.setDate(2,sqlDate);
                            ResultSet rs = pst.executeQuery();
                            while (rs.next()) {
                                count++;
                            }

                        }catch(SQLException ex){
                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                        }
                        text.setText("Number of Bugs resolved in week starting " + dateWeek.getValue() + ": " + count);
                    });


        }
        else if (generateReportID.equals("Best Performed Bug Reporter"))
        {
            generateTitle.setText("Best Performed Bug Reporter");
            comboBox.setVisible(false);
            dateWeek.setVisible(false);
            String currentDate = getDate().substring(3,5);
            String topBR="";
            System.out.println("Month: "+ currentDate);
            try{
                Connection con = getConnection();
                PreparedStatement pst =con.prepareStatement("SELECT reporter FROM bugReport WHERE (assignee IS NOT NULL) AND reporter IS NOT NULL and SUBSTRING(dateReported, LOCATE(\"-\",dateReported)+1,2) like ? group by reporter order by count(*)desc limit 1;");
                pst.setString(1,currentDate+"%");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    topBR = rs.getString("reporter");
                    System.out.println("Month best: "+ topBR);
                }

            }catch(SQLException ex){
                Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
            }


            //get from database

            text2.setText("Best Performed Bug Reporter this month: "+ topBR);



        }
        else if (generateReportID.equals("Best Performed Developer"))
        {
            generateTitle.setText("Best Performed Developer");
            dateWeek.setVisible(false);

            ObservableList<String> generateMonths = FXCollections.observableArrayList();
            generateMonths.add("January");
            generateMonths.add("February");
            generateMonths.add("March");
            generateMonths.add("April");
            generateMonths.add("May");
            generateMonths.add("June");
            generateMonths.add("July");
            generateMonths.add("August");
            generateMonths.add("September");
            generateMonths.add("October");
            generateMonths.add("November");
            generateMonths.add("December");

            comboBox.getStyleClass().add("comboboxGenerate");
            comboBox.setItems(generateMonths);
            comboBox.setValue("Months");

            String currentDate = getDate().substring(3,5);


            System.out.println("Month: "+ currentDate);


            comboBox.setOnAction (

                    event -> {

                        for (int i = 0; i < generateMonths.size(); i++) {

                            if (comboBox.getValue().equals(generateMonths.get(i)))
                            {
                                // System.out.println(comboBox.getValue());
                                userInput = comboBox.getValue();
                                System.out.println(userInput);

                                int reportCount=0;

                                switch(userInput){

                                    case "January":
                                        String topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"01%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "February":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"02%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "March":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"03%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "April":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"04%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "May":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"05%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "June":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"06%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "July":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"07%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "August":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"08%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "September":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"09%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "October":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"10%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "November":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"11%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    case "December":
                                        topDev="";
                                        try{
                                            Connection con = getConnection();
                                            PreparedStatement pst =con.prepareStatement("SELECT assignee from bugReport where bugStatus = \"Closed\" and SUBSTRING(dateClosed, LOCATE(\"-\",dateClosed)+1,2) like ? group by assignee order by count(*)desc limit 1;");
                                            pst.setString(1,"12%");
                                            ResultSet rs = pst.executeQuery();
                                            while (rs.next()) {
                                                topDev = rs.getString("assignee");
                                                System.out.println("Month best: "+ topDev);
                                            }

                                        }catch(SQLException ex){
                                            Logger.getLogger(GenerateReportController.class.getName()).log(Level.SEVERE,null,ex);
                                        }

                                        text2.setText("Best Performed Developer is: "+ topDev);
                                        break;
                                    default:
                                        break;
                                }


                            }

                        }
                    }

            );


        }
    }

    private String updateChoice;
    public ObservableList viewBugRptDev(TableView myTable, ObservableList<bugReport> ObserveList,String x, TableColumn bugID,TableColumn desc,TableColumn title, TableColumn assignee){

        //add options for combobox
        ObservableList<String> option = FXCollections.observableArrayList();
        option.add("Completed");
        option.add("In Progress");
        option.add("Not Completed");


        //more
        TableColumn <bugReport,bugReport> more = new TableColumn();
        more.setStyle( "-fx-alignment: CENTER;");
        more.setMinWidth(50);
        more.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        more.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private final Button moreButton = new Button();

            //@Override
            protected void updateItem(bugReport Developer, boolean empty) {
                super.updateItem(Developer, empty);

                if (Developer == null) {
                    setGraphic(null);
                    return;
                }
                else
                {

                    moreButton.getStyleClass().add("moreButton");
                    setGraphic(moreButton);
                    moreButton.setText("More");
                    moreButton.setOnAction(
                            event -> {

                                for (int i = 0; i < ObserveList.size(); i++)
                                {
                                    if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Developer).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        developerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                        System.out.println("Bug ID " + developerReportID + ".");


                                        try {

                                            Parent fxml2 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                                            pageName = "DevBugs";
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
                                        } catch (IOException ex) {
                                            Logger.getLogger(DeveloperBugsController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }
                            }
                    );
                }
            }
        });

        //assign button
        TableColumn <bugReport,bugReport> updateStatus = new TableColumn();
        updateStatus.setStyle( "-fx-alignment: CENTER;");
        updateStatus.setMinWidth(50);
        updateStatus.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateStatus.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private ComboBox updateCombo = new ComboBox();
            String status;
            @Override
            protected void updateItem(bugReport Developer, boolean empty) {
                super.updateItem(Developer, empty);
                if (!empty) {
                    setGraphic(updateCombo);
                    updateCombo.setEditable(false);
                    for (int i = 0; i < ObserveList.size(); i++)
                    {
                        if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Developer).toString())) //compare bugID in list with bugID col in tableview
                        {

                            status = ObserveList.get(i).getBugStatus();
                        }
                    }
                    updateCombo.setItems(option);
                    updateCombo.setOnAction(null); //to prevent onAction event when setValue to combo box
                    updateCombo.setValue(status);
                    updateCombo.setOnAction(

                            event -> {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle(null);
                                alert.initStyle(StageStyle.UTILITY);;
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to update status to " + updateCombo.getValue().toString() + "?");

                                Optional <ButtonType> action = alert.showAndWait();


                                for (int i = 0; i < ObserveList.size(); i++)
                                {
                                    if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Developer).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        developerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                        System.out.println("Bug ID " + developerReportID + ".");

                                    }
                                }

                                if(action.get() == ButtonType.OK)
                                {
                                    try{
                                        Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");
                                        int rs = con.createStatement().executeUpdate("UPDATE bugReport SET bugStatus = ('"+updateCombo.getValue().toString()+"') WHERE bugReportID =('"+developerReportID+"')");

                                    }catch(SQLException ex){
                                        Logger.getLogger(DeveloperBugsController.class.getName()).log(Level.SEVERE,null,ex);
                                    }

                                    updateChoice = updateCombo.getValue().toString(); //get selected item
                                    System.out.println("Updated status to " + updateChoice + "...");

                                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                    alert2.getButtonTypes().setAll(okButton);
                                    alert2.setTitle(null);
                                    alert2.initStyle(StageStyle.UTILITY);;
                                    alert2.setHeaderText(null);
                                    alert2.setContentText("Status updated to " + updateChoice);

                                    Optional <ButtonType> action2 = alert2.showAndWait();

                                    if(action2.get() == okButton)
                                    {
                                        System.out.println("Terminate alert dialog box");
                                    }

                                }

                                else
                                {
                                    try {
                                        System.out.println("Status update was cancelled...");

                                        Parent fxml = FXMLLoader.load(getClass().getResource("DeveloperBugs.fxml"));
                                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                        Scene fxmlRegister = new Scene(fxml);
                                        stage.setScene(fxmlRegister);

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

                                        stage.show();

                                    } catch (IOException ex) {
                                        Logger.getLogger(DeveloperBugsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }


                            }
                    );
                }
                else
                {
                    setGraphic(null);
                }


            }

        });

        assignee.setVisible(false);

        myTable.getColumns().addAll(bugID, title, desc, more, assignee, updateStatus);

        try{
            Connection con = getConnection();
            ResultSet rs = con.createStatement().executeQuery
                    ("SELECT * FROM bugReport WHERE assignee =('"+userType2+"') AND (bugStatus = 'Completed' OR bugStatus = 'In Progress' OR bugStatus = 'Not Completed')");
            while (rs.next()) {
                ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));

            }

        }catch(SQLException ex){
            Logger.getLogger(DeveloperBugsController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return ObserveList;
    }

    public ObservableList viewBugRptRev(TableView myTable, ObservableList<bugReport> ObserveList,String x,TableColumn bugID,TableColumn description,TableColumn title,TableColumn assignee, TableColumn bug ){

        //add options for combobox
        ObservableList<String> option = FXCollections.observableArrayList();
        option.add("Reviewed");
        option.add("Not Completed");

        //more button
        TableColumn <bugReport,bugReport> more = new TableColumn();
        more.setStyle( "-fx-alignment: CENTER;");
        more.setMinWidth(50);
        more.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        more.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private final Button moreButton = new Button();

            @Override
            protected void updateItem(bugReport Reviewer, boolean empty) {
                super.updateItem(Reviewer, empty);

                if (Reviewer == null) {
                    setGraphic(null);
                    return;
                }
                else
                {

                    moreButton.getStyleClass().add("moreButton");
                    setGraphic(moreButton);
                    moreButton.setText("More");
                    moreButton.setOnAction(
                            event ->
                            {

                                for (int i = 0; i < ObserveList.size(); i++)
                                {
                                    if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Reviewer).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        reviewerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                        System.out.println("Bug ID " + reviewerReportID + ".");


                                        try {

                                            Parent fxml2 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                                            pageName = "RevBugs";
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
                                        } catch (IOException ex) {
                                            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }

                            }
                    );
                }
            }
        });


        //updateStatus
        TableColumn <bugReport,bugReport> updateStatus = new TableColumn();
        updateStatus.setStyle( "-fx-alignment: CENTER;");
        updateStatus.setMinWidth(50);
        updateStatus.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        updateStatus.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private ComboBox updateCombo = new ComboBox();
            String status;
            @Override
            protected void updateItem(bugReport Reviewer, boolean empty) {
                super.updateItem(Reviewer, empty);
                if (!empty) {
                    setGraphic(updateCombo);
                    updateCombo.setEditable(false);
                    for (int i = 0; i < ObserveList.size(); i++)
                    {
                        if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Reviewer).toString())) //compare bugID in list with bugID col in tableview
                        {

                            status = ObserveList.get(i).getBugStatus();
                        }
                    }
                    updateCombo.setItems(option);
                    updateCombo.setOnAction(null); //to prevent onAction event when setValue to combo box
                    updateCombo.setValue(status);
                    updateCombo.setOnAction(

                            event -> {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle(null);
                                alert.initStyle(StageStyle.UTILITY);;
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure to update the status to " + updateCombo.getValue().toString() + "?");
                                Optional <ButtonType> action = alert.showAndWait();

                                for (int i = 0; i < ObserveList.size(); i++)
                                {
                                    if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Reviewer).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        reviewerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                        System.out.println("Bug ID " + reviewerReportID + ".");

                                    }
                                }

                                if(action.get() == ButtonType.OK)
                                {
                                    try{
                                        Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");
                                        int rs = con.createStatement().executeUpdate("UPDATE bugReport SET bugStatus = ('"+updateCombo.getValue().toString()+"') WHERE bugReportID =('"+reviewerReportID+"')");

                                    }catch(SQLException ex){
                                        Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
                                    }

                                    updateChoice = updateCombo.getValue().toString(); //get selected item
                                    System.out.println("Updated status to " + updateChoice + "...");

                                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                    alert2.getButtonTypes().setAll(okButton);
                                    alert2.setTitle(null);
                                    alert2.initStyle(StageStyle.UTILITY);;
                                    alert2.setHeaderText(null);
                                    alert2.setContentText("Status updated to " + updateChoice);

                                    Optional <ButtonType> action2 = alert2.showAndWait();

                                    if(action2.get() == okButton)
                                    {
                                        System.out.println("Terminate alert dialog box");
                                    }
                                }
                                else
                                {
                                    System.out.println("Status was not updated...");
                                    {
                                        try {
                                            System.out.println("Status update was cancelled...");

                                            Parent fxml = FXMLLoader.load(getClass().getResource("ReviewerBugs.fxml"));
                                            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                            Scene fxmlRegister = new Scene(fxml);
                                            stage.setScene(fxmlRegister);

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

                                            stage.show();

                                        } catch (IOException ex) {
                                            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            });


                }
                else
                {
                    setGraphic(null);
                }
            }

        });

        myTable.getColumns().addAll(bugID, title, description, more, assignee, updateStatus);

        try{
            Connection con = getConnection();
            ResultSet rs = con.createStatement().executeQuery
                    ("SELECT * FROM bugReport WHERE bugStatus = 'Completed' ");
            while (rs.next()) {
                ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee"), rs.getString("bugStatus")));

            }

        }catch(SQLException ex){
            Logger.getLogger(ReviewerBugsController.class.getName()).log(Level.SEVERE,null,ex);
        }

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

        return ObserveList;
    }

    private String assigneeChoice;
    ObservableList<String> reportStatus = FXCollections.observableArrayList();
    ObservableList<String> option = FXCollections.observableArrayList();
    ObservableList<String> expertiseList = FXCollections.observableArrayList();

    public ObservableList viewBugRptTri(TableView myTable, ObservableList<bugReport> list,String x,TableColumn bugID,TableColumn title,TableColumn description,TableColumn more,TableColumn assign,TableColumn checkDuplicate){

        reportStatus.add("Invalid");
        reportStatus.add("Duplicate");

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");
            String sql = "SELECT username FROM user WHERE userType = ?";
            PreparedStatement Pstatement = con.prepareStatement(sql);
            Pstatement.setString(1, "dev");
            ResultSet result = Pstatement.executeQuery();
            while (result.next())
            {
                option.add(result.getString("username"));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
        }

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");
            String sql = "SELECT * FROM user WHERE userType = ?";
            PreparedStatement Pstatement = con.prepareStatement(sql);
            Pstatement.setString(1, "dev");
            ResultSet result = Pstatement.executeQuery();
            while (result.next())
            {
                expertiseList.add(result.getString(6));
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
        }


        //more button
        more.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private final Button moreButton = new Button();
            @Override
            protected void updateItem(bugReport Triager, boolean empty) {
                super.updateItem(Triager, empty);

                if (Triager == null) {
                    setGraphic(null);
                    return;
                }
                else
                {

                    moreButton.getStyleClass().add("moreButton");
                    setGraphic(moreButton);
                    moreButton.setText("More");
                    moreButton.setOnAction(
                            event -> {

                                for (int i = 0; i < list.size(); i++)
                                {
                                    if (list.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Triager).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        triagerReportID =(list.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                        System.out.println("Bug ID " + triagerReportID + ".");


                                        try {

                                            Parent fxml2 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                                            pageName = "TriAss";
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
                                        } catch (IOException ex) {
                                            Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }
                            }
                    );
                }
            }
        });



        assign.setCellFactory(param -> new TableCell<bugReport, bugReport>() {

            final ComboBox assignCombo = new ComboBox();

            @Override
            protected void updateItem(bugReport Triager, boolean empty) {
                super.updateItem(Triager, empty);
                if (!empty) {
                    setGraphic(assignCombo);
                    assignCombo.setEditable(false);
                    assignCombo.setValue("Assign");
                    assignCombo.setItems(option);

                    //code to assign tooltip to each items in combobox
                    assignCombo.setCellFactory(
                            new Callback<ListView<String>, ListCell<String>>() {
                                @Override public ListCell<String> call(ListView<String> param) {
                                    final ListCell<String> cell = new ListCell<String>() {
                                        {
                                            super.setPrefWidth(100);
                                        }
                                        @Override public void updateItem(String item,
                                                                         boolean empty) {
                                            super.updateItem(item, empty);
                                            if (item != null)
                                            {
                                                setText(item);
                                                Tooltip tt1 = new Tooltip();

                                                for(int i= 0; i < option.size(); i++)
                                                {
                                                    if(option.get(i).equals(item))
                                                        tt1 = new Tooltip(expertiseList.get(i));
                                                    setTooltip(tt1);
                                                }
                                            }
                                            else {
                                                setText(null);
                                            }
                                        }
                                    };
                                    return cell;
                                }
                            });


                    assignCombo.setOnAction(
                            event -> {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle(null);
                                alert.initStyle(StageStyle.UTILITY);;
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to assign to this " + assignCombo.getValue().toString() + "?");
                                Optional <ButtonType> action = alert.showAndWait();

                                if(action.get() == ButtonType.OK)
                                {

                                    assigneeChoice = assignCombo.getValue().toString();
                                    //System.out.println("Bug ID was assigned to the " + assigneeChoice + "...");
                                    //System.out.println(assigneeChoice);

                                    for (int i = 0; i < list.size(); i++)
                                    {
                                        if (list.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Triager).toString())) //compare bugID in list with bugID col in tableview
                                        {
                                            String tmp = String.valueOf(list.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            try{
                                                Connection con = getConnection();
                                                PreparedStatement pst =con.prepareStatement("UPDATE bugReport SET assignee = ?, bugStatus = ?,  triager =? WHERE bugReportID = ?");
                                                pst.setString(1,assigneeChoice);
                                                pst.setString(2,"In Progress");
                                                pst.setString(3, userType2);
                                                pst.setString(4, tmp);

                                                pst.execute();

                                            }catch(SQLException ex){
                                                Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
                                            }

                                            System.out.println("Bug ID " + tmp + " was assigned to " + assigneeChoice + ".");
                                        }
                                        //String tmp = String.valueOf(list.get(i).getBugID());
                                        //System.out.println(tmp);

                                    }
                                }
                                else
                                {
                                    System.out.println("Developer was not assign to the BUG ID...");

                                    try {

                                        System.out.println("Status update was cancelled...");

                                        Parent fxml = FXMLLoader.load(getClass().getResource("TriagerAssign.fxml"));
                                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                        Scene fxmlRegister = new Scene(fxml);
                                        stage.setScene(fxmlRegister);

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

                                        stage.show();

                                    } catch (IOException ex) {
                                        Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                    );

                }
                else
                {
                    setGraphic(null);
                }
            }

        });


        checkDuplicate.setCellFactory(param -> new TableCell<bugReport, bugReport>() {

            final ComboBox checkCombo = new ComboBox();

            @Override
            protected void updateItem(bugReport Triager, boolean empty) {
                super.updateItem(Triager, empty);
                if (!empty) {
                    setGraphic(checkCombo);
                    checkCombo.setEditable(false);
                    checkCombo.setValue("Status");
                    checkCombo.setItems(reportStatus);

                    checkCombo.setOnAction(
                            event -> {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle(null);
                                alert.initStyle(StageStyle.UTILITY);;
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to mark this report as " + checkCombo.getValue().toString() + "?");
                                Optional <ButtonType> action = alert.showAndWait();

                                if(action.get() == ButtonType.OK)
                                {

                                    assigneeChoice = checkCombo.getValue().toString();
                                    //System.out.println("Bug ID was assigned to the " + assigneeChoice + "...");
                                    //System.out.println(assigneeChoice);

                                    for (int i = 0; i < list.size(); i++)
                                    {
                                        if (list.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Triager).toString())) //compare bugID in list with bugID col in tableview
                                        {
                                            String tmp = String.valueOf(list.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            try{
                                                Connection con = getConnection();
                                                PreparedStatement pst =con.prepareStatement("UPDATE bugReport SET bugStatus = ?, triager =? WHERE bugReportID = ?");
                                                pst.setString(1,assigneeChoice);
                                                pst.setString(2,userType2);
                                                pst.setString(3, tmp);

                                                pst.execute();

                                            }catch(SQLException ex){
                                                Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE,null,ex);
                                            }

                                            System.out.println("Bug ID " + tmp + " was marked as " + assigneeChoice + ".");
                                        }
                                        //String tmp = String.valueOf(list.get(i).getBugID());
                                        //System.out.println(tmp);

                                    }
                                }
                                else
                                {
                                    System.out.println("Developer was not assign to the BUG ID...");

                                    try {

                                        System.out.println("Status update was cancelled...");

                                        Parent fxml = FXMLLoader.load(getClass().getResource("TriagerAssign.fxml"));
                                        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                                        Scene fxmlRegister = new Scene(fxml);
                                        stage.setScene(fxmlRegister);

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

                                        stage.show();

                                    } catch (IOException ex) {
                                        Logger.getLogger(TriagerAssignController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                    );

                }
                else
                {
                    setGraphic(null);
                }
            }

        });

        try{
            Connection con = getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM bugReport WHERE assignee is NULL AND bugStatus != 'Invalid' AND bugStatus != 'duplicate'");
            while (rs.next()) {
                list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription")));

            }

        }catch(SQLException ex){
            Logger.getLogger(TriagerMainController.class.getName()).log(Level.SEVERE,null,ex);
        }

        return list;

    }

    public ObservableList searchA(String AFilter, ObservableList<bugReport> ObserveList)
    {
        if (AFilter.isEmpty()){
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));
                }

            }catch(SQLException ex){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
            }
        }else{
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE assignee = ?");
                pst.setString(1,AFilter);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));
                }

            }catch(SQLException ex){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        return ObserveList;
    }

    public ObservableList searchK(String KFilter, ObservableList<bugReport> ObserveList)
    {
        if (KFilter.isEmpty()){
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));
                }

            }catch(SQLException ex){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
            }
        }else{
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugDescription LIKE ? OR bugTitle LIKE ?");
                pst.setString(1,"%"+KFilter+"%");
                pst.setString(2,"%"+KFilter+"%");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));
                }

            }catch(SQLException ex){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        return ObserveList;
    }

    public ObservableList searchT(String TFilter, ObservableList<bugReport> ObserveList)
    {
        if (TFilter.isEmpty()){
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));
                }

            }catch(SQLException ex){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
            }
        }else{
            try{
                Connection con = getConnection();
                ObserveList.clear();

                PreparedStatement pst =con.prepareStatement("SELECT * FROM bugReport WHERE bugTitle = ?");
                pst.setString(1,TFilter);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));
                }

            }catch(SQLException ex){
                Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
        return ObserveList;
    }

    public ObservableList viewBugRptTriR(TableView myTable, ObservableList<bugReport> list,String x,TableColumn bugID,TableColumn title, TableColumn description,TableColumn assignee,TableColumn more,TableColumn close){

        more.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private final Button moreButton = new Button();

            @Override
            protected void updateItem(bugReport Triager, boolean empty) {
                super.updateItem(Triager, empty);

                if (Triager == null) {
                    setGraphic(null);
                    return;
                }
                else
                {

                    moreButton.getStyleClass().add("moreButton");
                    setGraphic(moreButton);
                    moreButton.setText("More");
                    moreButton.setOnAction(
                            event -> {

                                for (int i = 0; i < list.size(); i++)
                                {
                                    if (list.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Triager).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        triagerReportID =(list.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                        System.out.println("Bug ID " + triagerReportID + ".");


                                        try {

                                            Parent fxml2 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                                            pageName = "TriRev";
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
                                        } catch (IOException ex) {
                                            Logger.getLogger(TriagerReviewController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }
                            }
                    );
                }
            }
        });

        close.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private final Button closeButton = new Button();

            @Override
            protected void updateItem(bugReport Triager, boolean empty) {
                super.updateItem(Triager, empty);

                if (Triager == null) {
                    setGraphic(null);
                    return;
                }
                else
                {

                    closeButton.getStyleClass().add("closeButton");
                    setGraphic(closeButton);
                    closeButton.setOnAction(
                            event -> {

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle(null);
                                alert.initStyle(StageStyle.UTILITY);;
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to close this Bug Report?");
                                Optional <ButtonType> action = alert.showAndWait();

                                if(action.get() == ButtonType.OK)
                                {
                                    for (int i = 0; i < list.size(); i++)
                                    {
                                        if (list.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(Triager).toString())) //compare bugID in list with bugID col in tableview
                                        {
                                            String tmp = String.valueOf(list.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            try{
                                                Connection con = getConnection();
                                                PreparedStatement pst =con.prepareStatement("UPDATE bugReport SET bugStatus = ?, triager =?, dateClosed=? WHERE bugReportID = ?");
                                                pst.setString(1,"closed");
                                                pst.setString(2,userType2);
                                                pst.setString(3,getDate() );
                                                pst.setString(4, tmp);

                                                pst.execute();

                                            }catch(SQLException ex){
                                                Logger.getLogger(TriagerReviewController.class.getName()).log(Level.SEVERE,null,ex);
                                            }

                                            System.out.println("Bug Report was closed...");
                                            
                                            list.clear();

                                            try{
                                                Connection con = getConnection();
                                                //edit query below to set condition on what should be displayed on tableView
                                                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM bugReport WHERE bugStatus = 'Reviewed' AND triager ='" + userType2 + "'");
                                                while (rs.next()) {
                                                    list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"),rs.getString("assignee")));
                                                }
                                            }catch(SQLException ex){
                                                Logger.getLogger(TriagerReviewController.class.getName()).log(Level.SEVERE,null,ex);
                                            }
                                            myTable.setItems(list);
                                        }
                                        //String tmp = String.valueOf(list.get(i).getBugID());
                                        //System.out.println(tmp);

                                    }

                                }
                                else
                                {
                                    System.out.println("Bug Report was not updated...");
                                }

                            }
                    );
                }
            }
        });

        try{
            Connection con = getConnection();
            //edit query below to set condition on what should be displayed on tableView
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM bugReport WHERE bugStatus = 'Reviewed' AND triager ='" + userType2 + "'");
            while (rs.next()) {
                list.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"),rs.getString("assignee")));

            }

        }catch(SQLException ex){
            Logger.getLogger(TriagerReviewController.class.getName()).log(Level.SEVERE,null,ex);
        }
        return list;
    }

    public ObservableList viewBugRpt(TableView myTable, ObservableList<bugReport> ObserveList, String x,TableColumn bugID, TableColumn title, TableColumn desc, TableColumn assignee,TableColumn more){


        more.setCellFactory(param -> new TableCell<bugReport, bugReport>() {
            private final Button moreButton = new Button();

            @Override
            protected void updateItem(bugReport bugReport, boolean empty) {
                super.updateItem(bugReport, empty);

                if (bugReport == null) {
                    setGraphic(null);
                    return;
                }
                else
                {

                    moreButton.getStyleClass().add("moreButton");
                    setGraphic(moreButton);
                    moreButton.setText("More");
                    moreButton.setOnAction(
                            event -> {

                                for (int i = 0; i < ObserveList.size(); i++)
                                {
                                    if (ObserveList.get(i).getBugReportID() == Integer.parseInt(bugID.getCellData(bugReport).toString())) //compare bugID in list with bugID col in tableview
                                    {
                                        if(x.equals("dev")){
                                            developerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            System.out.println("Bug ID " + developerReportID + ".");
                                        }

                                        else if(x.equals("rev")){
                                            reviewerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            System.out.println("Bug ID " + reviewerReportID + ".");
                                        }

                                        else if(x.equals("br")){
                                            bugreporterReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            System.out.println("Bug ID " + bugreporterReportID + ".");
                                        }

                                        else if(x.equals("tri")){
                                            triagerReportID =(ObserveList.get(i).getBugReportID()); //extract bugID data for that respective row to assign to developer to work on that bug id(report)
                                            System.out.println("Bug ID " + triagerReportID + ".");
                                        }

                                        try {

                                            Parent fxml2 = FXMLLoader.load(getClass().getResource("CommentMain.fxml"));
                                            pageName = "Main";
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
                                        } catch (IOException ex) {
                                            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }


                            }
                    );
                }
            }
        });


        myTable.getColumns().addAll(bugID, title, desc, more, assignee);

        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://securepassword1.cgwclabfwvie.ap-southeast-1.rds.amazonaws.com:3306/CSIT314?useSSL=false", "admin" , "FYP12345");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM bugReport");
            while (rs.next()) {
                ObserveList.add(new bugReport(rs.getInt("bugReportID"), rs.getString("bugTitle"), rs.getString("bugDescription"), rs.getString("assignee")));

            }

        }catch(SQLException ex){
            Logger.getLogger(User.class.getName()).log(Level.SEVERE,null,ex);
        }

        return ObserveList;
    }
    
}


