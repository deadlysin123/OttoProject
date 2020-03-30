package Main.Employee;

import Main.DBconnection;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Employee implements Initializable
{

    @FXML
    private Label alertLabel;
    @FXML
    private TableView<EmployeeTable> empTable;
    @FXML
    private TableColumn<EmployeeTable,String> empIDCol;
    @FXML
    private TableColumn<EmployeeTable,String> empFNameCol;
    @FXML
    private TableColumn<EmployeeTable,String> empLNameCol;
    @FXML
    private TableColumn<EmployeeTable,String> empPassCol;
    @FXML
    private TableColumn<EmployeeTable,String> empStreetCol;
    @FXML
    private TableColumn<EmployeeTable,String> empCityCol;
    @FXML
    private TableColumn<EmployeeTable,String> empStateCol;
    @FXML
    private TableColumn<EmployeeTable,String> empZipCol;
    @FXML
    private TableColumn empPhoneCol;


    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField empFName;
    @FXML
    private JFXTextField empLName;
    @FXML
    private JFXTextField empPass;
    @FXML
    private JFXTextField empStreet;
    @FXML
    private JFXTextField empCity;
    @FXML
    private JFXTextField empState;
    @FXML
    private JFXTextField empZip;
    @FXML
    private JFXTextField Phone;



    ObservableList<EmployeeTable> empData = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //print selected item from table into text fields
        empTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String fName = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpFNameCol();
                empFName.setText(fName);

                String lName = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpLNameCol();
                empLName.setText(lName);

                String pass = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpPassCol();
                empPass.setText(pass);

                String street = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpStreetCol();
                empStreet.setText(street);

                String city = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpCityCol();
                empCity.setText(city);

                String state = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpStateCol();
                empState.setText(state);

                String zip = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpZipCol();
                empZip.setText(zip);

                String phone = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpPhoneCol();
                Phone.setText(phone);
            }
        });
        
    }




    // load Employee data from database and display them on the table
    public void loadInfo(ActionEvent actionEvent) throws IOException {

        //Clear current elements in the list to prevent duplicates
        empData.clear();

        //set the appropriate values to the table columns
        empIDCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empIDCol"));
        empFNameCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empFNameCol"));
        empLNameCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empLNameCol"));
        empPassCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empPassCol"));
        empStreetCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empStreetCol"));
        empCityCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empCityCol"));
        empStateCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empStateCol"));
        empZipCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empZipCol"));
        empPhoneCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("empPhoneCol"));

        try {
            DBconnection conn = new DBconnection(); //very important, do not copy paste this connection portion, will get errors, type it out
            Connection conn1 = conn.DBcon();

            //Select everything from the Employee table
            String sql = "SELECT * FROM Employee";
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            //add all the elements from the columns in the database into the empData list
            while (resultSet.next()) {
                empData.add(new EmployeeTable(
                        resultSet.getString("EmpID"),
                        resultSet.getString("EmpFName"),
                        resultSet.getString("EmpLName"),
                        resultSet.getString("EmpPass"),
                        resultSet.getString("EmpStreet"),
                        resultSet.getString("EmpCity"),
                        resultSet.getString("EmpState"),
                        resultSet.getString("EmpZip"),
                        resultSet.getString("Phone")
                ));
            }

            //set the list onto the Customer Table
            empTable.setItems(empData);


            //close the connection
            statement.close();
            conn1.close();


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void empAddAction(ActionEvent actionEvent) throws SQLException {

            //establish connection to DB
            DBconnection conn = new DBconnection();
            Connection conn1 = conn.DBcon();
            String sql = "INSERT INTO Employee (EmpFname, EmpLName, EmpPass, EmpStreet, EmpCity, EmpState, EmpZip, Phone) VALUES (?,?,?,?,?,?,?,?)";

            String fName = empFName.getText();
            String lName = empLName.getText();
            String pass = empPass.getText();
            String street = empStreet.getText();
            String city = empCity.getText();
            String state = empState.getText();
            String zip = empZip.getText();
            String phone = Phone.getText();

            PreparedStatement statement = conn1.prepareStatement(sql);
            statement.setString(1,fName);
            statement.setString(2,lName);
            statement.setString(3,pass);
            statement.setString(4,street);
            statement.setString(5,city);
            statement.setString(6,state);
            statement.setString(7,zip);
            statement.setString(8,phone);
            statement.executeUpdate();

            //print alert message to screen
            alertLabel.setText("Sucessfully Added!");

            statement.close();
            conn1.close();

            String empty = "";
        empFName.setText(empty);
        empLName.setText(empty);
        empPass.setText(empty);
        empStreet.setText(empty);
        empCity.setText(empty);
        empState.setText(empty);
        empZip.setText(empty);
        Phone.setText(empty);

        }



    public void empSubmitAction(ActionEvent actionEvent) throws SQLException {

        DBconnection conn = new DBconnection();
        Connection conn1 = conn.DBcon();
        String sql = "UPDATE Employee SET EmpFName=?, EmpLName=?, EmpPass=?, EmpStreet=?, EmpCity=?, EmpState=?, EmpZip=?, Phone=? WHERE EmpID=?";

        //Pulls ID from tableview
        String id = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpIDCol();

        //sets JFXtextfields to local strings
        String fName = empFName.getText();
        String lName = empLName.getText();
        String pass = empPass.getText();
        String street = empStreet.getText();
        String city = empCity.getText();
        String state = empState.getText();
        String zip = empZip.getText();
        String phone = Phone.getText();

        //fills in SQL statement with local strings
        PreparedStatement statement = conn1.prepareStatement(sql);
        statement.setString(1, fName);
        statement.setString(2, lName);
        statement.setString(3,pass);
        statement.setString(4, street);
        statement.setString(5, city);
        statement.setString(6, state);
        statement.setString(7, zip);
        statement.setString(8, phone);
        statement.setString(9, id);
        statement.executeUpdate();

        //print alert message to screen
        alertLabel.setText("Sucessfully Editted!");

        statement.close();
        conn1.close();

        String empty = "";
        empFName.setText(empty);
        empLName.setText(empty);
        empPass.setText(empty);
        empStreet.setText(empty);
        empCity.setText(empty);
        empState.setText(empty);
        empZip.setText(empty);
        Phone.setText(empty);
    }


    // load employee who aren't employed at the moment
    public void loadPastEmp(ActionEvent actionEvent) {
        //Clear current elements in the list to prevent duplicates
        empData.clear();

        //set the appropriate values to the table columns
        empIDCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empIDCol"));
        empFNameCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empFNameCol"));
        empLNameCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empLNameCol"));
        empPassCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empPassCol"));
        empStreetCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empStreetCol"));
        empCityCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empCityCol"));
        empStateCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empStateCol"));
        empZipCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable, String>("empZipCol"));
        empPhoneCol.setCellValueFactory(new PropertyValueFactory<EmployeeTable,String>("empPhoneCol"));

        try {
            DBconnection conn = new DBconnection(); //very important, do not copy paste this connection portion, will get errors, type it out
            Connection conn1 = conn.DBcon();

            //Select everything from the archive table
            String sql = "SELECT * FROM Archive";
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            //add all the elements from the columns in the database into the empData list
            while (resultSet.next()) {
                empData.add(new EmployeeTable(
                        resultSet.getString("EmpID"),
                        resultSet.getString("EmpFName"),
                        resultSet.getString("EmpLName"),
                        resultSet.getString("EmpPass"),
                        resultSet.getString("EmpStreet"),
                        resultSet.getString("EmpCity"),
                        resultSet.getString("EmpState"),
                        resultSet.getString("EmpZip"),
                        resultSet.getString("Phone")
                ));
            }

            //set the list onto the Customer Table
            empTable.setItems(empData);

            //close the connection
            statement.close();
            conn1.close();


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void archiveEmployee(ActionEvent actionEvent) throws SQLException {

        DBconnection conn = new DBconnection();
        Connection conn1 = conn.DBcon();
        //if giving trouble, I used different statements and closed them seprately
        String sql = "INSERT INTO Archive (EmpFname, EmpLName, EmpPass, EmpStreet, EmpCity, EmpState, EmpZip, Phone) VALUES (?,?,?,?,?,?,?,?)";

        String fName = empFName.getText();
        String lName = empLName.getText();
        String pass = empPass.getText();
        String street = empStreet.getText();
        String city = empCity.getText();
        String state = empState.getText();
        String zip = empZip.getText();
        String phone = Phone.getText();

        PreparedStatement statement = conn1.prepareStatement(sql);
        statement.setString(1,fName);
        statement.setString(2,lName);
        statement.setString(3,pass);
        statement.setString(4,street);
        statement.setString(5,city);
        statement.setString(6,state);
        statement.setString(7, zip);
        statement.setString(8,phone);

        //print alert message to screen
        alertLabel.setText("Sucessfully Moved!");

        statement.executeUpdate();
        statement.close();

        String id = empTable.getItems().get(empTable.getSelectionModel().getSelectedIndex()).getEmpIDCol();

        String sql2 = "DELETE FROM Employee WHERE EmpID=?";
        PreparedStatement s = conn1.prepareStatement(sql2);
        s.setString(1,id);
        s.executeUpdate();
        s.close();

        conn1.close();
    }




    //Scene Transition buttons
    public void goMain(ActionEvent actionEvent) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//MainPage/MainPage.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void goCustomer(ActionEvent actionEvent) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Customer/Customer.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void goOrder(ActionEvent actionEvent) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Order/Order.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }
}
