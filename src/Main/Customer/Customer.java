package Main.Customer;

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

public class Customer implements Initializable {

    @FXML
    private Label alertLabel;
    @FXML
    private AnchorPane root;


    @FXML
    private JFXTextField custFName;
    @FXML
    private JFXTextField custLName;
    @FXML
    private JFXTextField custStreet;
    @FXML
    private JFXTextField custCity;
    @FXML
    private JFXTextField custState;
    @FXML
    private JFXTextField custZip;
    @FXML
    private JFXTextField custPhone;


    @FXML
    private TableView<CustomerTable> custTable;
    @FXML
    private TableColumn<CustomerTable,String> custIDCol;
    @FXML
    private TableColumn<CustomerTable,String> custFNameCol;
    @FXML
    private TableColumn<CustomerTable,String> custLNameCol;
    @FXML
    private TableColumn<CustomerTable,String> custAddressCol;
    @FXML
    private TableColumn<CustomerTable,String> custPhoneCol;
    @FXML
    private TableColumn<CustomerTable,String> custCityCol;
    @FXML
    private TableColumn<CustomerTable,String>custStateCol;
    @FXML
    private TableColumn custZipCol;


    ObservableList<CustomerTable> custData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        custTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //Pulls the data from the tableview and populates the JFXtextfields
                String fName = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustFNameCol();
                custFName.setText(fName);

                String lName = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustLNameCol();
                custLName.setText(lName);

                String street = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustAddressCol();
                custStreet.setText(street);

                String city = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustCityCol();
                custCity.setText(city);

                String state = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustStateCol();
                custState.setText(state);

                String zip = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustZipCol();
                custZip.setText(zip);

                String phone = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustPhoneCol();
                custPhone.setText(phone);
            }
        });

    }


    //Load Customer Information from Database and display them on the Customer Table
    public void loadInfo(ActionEvent actionEvent)
    {
        //Clear current elements in the list to prevent duplicates
        custData.clear();

        //set the appropriate values to the table columns
        custIDCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custIDCol"));
        custFNameCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custFNameCol"));
        custLNameCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custLNameCol"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custAddressCol"));
        custCityCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custCityCol"));
        custStateCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custStateCol"));
        custZipCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custZipCol"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<CustomerTable,String>("custPhoneCol"));

        try {

            //establish connection to DB
            DBconnection conn = new DBconnection();
            Connection conn1 = conn.DBcon();

            //Select everything from the Customer table
            String sql = "SELECT * FROM Customer";
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            //add all the elements from the columns in the database into the custData list
            while (resultSet.next())
            {
                custData.add(new CustomerTable(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustFName"),
                        resultSet.getString("CustLName"),
                        resultSet.getString("CustStreet"),
                        resultSet.getString("CustCity"),
                        resultSet.getString("CustState"),
                        resultSet.getString("CustZip"),
                        resultSet.getString("CustPhone")
                ));
            }

            //set the list onto the Customer Table
            custTable.setItems(custData);

            //close the connection
            statement.close();
            conn1.close();

        } catch (Exception e) {
            System.err.println("Error" + e);
        }

    }


    //Add customer to the database
    public void custAddAction(ActionEvent actionEvent) {

        try {

            //establish connection to DB
            DBconnection conn = new DBconnection();
            Connection conn1 = conn.DBcon();
            String sql = "INSERT INTO Customer (CustFName, CustLName, CustStreet, CustCity, CustState, CustZip, CustPhone) VALUES (?,?,?,?,?,?,?)";

            //Sets jfxtextfields to local strings
            String fName = custFName.getText();
            String lName = custLName.getText();
            String street = custStreet.getText();
            String city = custCity.getText();
            String state = custState.getText();
            String zip = custZip.getText();
            String phone = custPhone.getText();

            //fills in the "?" with the strings above
            PreparedStatement statement = conn1.prepareStatement(sql);
            statement.setString(1, fName);
            statement.setString(2, lName);
            statement.setString(3, street);
            statement.setString(4, city);
            statement.setString(5, state);
            statement.setString(6, zip);
            statement.setString(7, phone);
            statement.executeUpdate();

            //print alert message to screen
            alertLabel.setText("Sucessfully Added!");

            //close connection
            statement.close();
            conn1.close();

            String empty = "";
            custFName.setText(empty);
            custLName.setText(empty);
            custStreet.setText(empty);
            custCity.setText(empty);
            custState.setText(empty);
            custZip.setText(empty);
            custPhone.setText(empty);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Submit Customer informating changes to the database
    public void custSubmitAction(ActionEvent actionEvent) throws SQLException {

        DBconnection conn = new DBconnection();
        Connection conn1 = conn.DBcon();
        String sql = "UPDATE Customer SET CustFName=?, CustLName=?, CustStreet=?, CustCity=?, CustState=?, CustZip=?, CustPhone=? WHERE CustID=?";

        //Pulls ID from tableview
        String id = custTable.getItems().get(custTable.getSelectionModel().getSelectedIndex()).getCustIDCol();

        //Sets jfxtextfields to local strings
        String fName = custFName.getText();
        String lName = custLName.getText();
        String street = custStreet.getText();
        String city = custCity.getText();
        String state = custState.getText();
        String zip = custZip.getText();
        String phone = custPhone.getText();

        //fills in the "?" with the strings above
        PreparedStatement statement = conn1.prepareStatement(sql);
        statement.setString(1, fName);
        statement.setString(2, lName);
        statement.setString(3, street);
        statement.setString(4, city);
        statement.setString(5, state);
        statement.setString(6, zip);
        statement.setString(7, phone);
        statement.setString(8, id);
        statement.executeUpdate();

        //print alert message to screen
        alertLabel.setText("Sucessfully Saved!");
        alertLabel.setVisible(true);
        //close connections
        statement.close();
        conn1.close();

        String empty = "";
        custFName.setText(empty);
        custLName.setText(empty);
        custStreet.setText(empty);
        custCity.setText(empty);
        custState.setText(empty);
        custZip.setText(empty);
        custPhone.setText(empty);

    }




    //Scene Transition buttons
    public void goMain(ActionEvent actionEvent) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//MainPage/MainPage.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void goEmployee(ActionEvent actionEvent) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Employee/Employee.fxml"));
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