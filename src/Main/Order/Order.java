package Main.Order;

import Main.DBconnection;
import Main.Order.ProductTable;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Order implements Initializable
{


    @FXML
    private JFXComboBox productBox;
    @FXML
    private JFXComboBox custIDbox;
    @FXML
    private JFXComboBox empIDbox;
    @FXML
    private DatePicker Date;
    @FXML
    private Label alertLabel;


    //Combobox item lists
    ObservableList custChoice = FXCollections.observableArrayList();
    ObservableList empChoice = FXCollections.observableArrayList();
    ObservableList prodChoice = FXCollections.observableArrayList();


    @FXML
    private AnchorPane root;


    //Customer Information Table
    @FXML
    private TableView<CustOrderTable> custTable;
    @FXML
    private TableColumn<CustOrderTable,String> custIDCol;
    @FXML
    private TableColumn<CustOrderTable,String> custFName;
    @FXML
    private TableColumn<CustOrderTable,String> custLName;

    ObservableList<CustOrderTable> custData = FXCollections.observableArrayList();

    //Employee Information Table
    @FXML
    private TableView<EmpOrderTable> empTable;
    @FXML
    private TableColumn<EmpOrderTable,String> empIDCol;
    @FXML
    private TableColumn<EmpOrderTable,String> empFName;
    @FXML
    private TableColumn<EmpOrderTable,String> empLName;

    ObservableList<EmpOrderTable> empData = FXCollections.observableArrayList();

    //Product Table
    @FXML
    private TableView prodTable;
    @FXML
    private TableColumn prodIDCol;
    @FXML
    private TableColumn prodNameCol;
    @FXML
    private TableColumn prodPriceCol;

    ObservableList<ProductTable> prodData = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    //Load Customer information using foreign key
    public void loadCust(ActionEvent actionEvent) {

        //Clear current elements in the list to prevent duplicates
        custData.clear();
        custChoice.clear();

        //set the appropriate values to the table columns
        custIDCol.setCellValueFactory(new PropertyValueFactory<CustOrderTable,String>("custIDCol"));
        custFName.setCellValueFactory(new PropertyValueFactory<CustOrderTable,String>("custFName"));
        custLName.setCellValueFactory(new PropertyValueFactory<CustOrderTable,String>("custLName"));

        try {
            DBconnection conn = new DBconnection();
            Connection conn1 = conn.DBcon();

            //Select everything from the Customer table
            String sql = "SELECT * FROM Customer";
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            //add all the elements from the columns in the database into the custData list and combobox list
            while (resultSet.next())
            {
                custData.add(new CustOrderTable(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustFName"),
                        resultSet.getString("CustLName")
                ));

                custChoice.add(resultSet.getString("CustID"));
            }

            //set the list onto the Customer Table
            custTable.setItems(custData);

            //set Customer ID into combo box
            custIDbox.setItems(custChoice);

            //close the connection
            statement.close();
            conn1.close();


        } catch (Exception e) {
            System.err.println("error" + e);
        }

    }

    //load Employee information from Employee Table
    public void loadEmp(ActionEvent actionEvent) {

        //Clear current elements in the list to prevent duplicates
        empData.clear();
        empChoice.clear();

        //set the appropriate values to the table columns
        empIDCol.setCellValueFactory(new PropertyValueFactory<EmpOrderTable,String>("empIDCol"));
        empFName.setCellValueFactory(new PropertyValueFactory<EmpOrderTable,String>("empFName"));
        empLName.setCellValueFactory(new PropertyValueFactory<EmpOrderTable,String>("empLName"));

        try {
            DBconnection conn = new DBconnection();
            Connection conn1 = conn.DBcon();

            //Select everything from the Employee table
            String sql = "SELECT * FROM Employee";
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            //add all the elements from the columns in the database into the empData list
            while (resultSet.next())
            {
                empData.add(new EmpOrderTable(
                        resultSet.getString("EmpID"),
                        resultSet.getString("EmpFName"),
                        resultSet.getString("EmpLName")
                ));

                empChoice.add(resultSet.getString("EmpID"));
            }

            //set the list onto the Employee Table
            empTable.setItems(empData);

            //set Employee ID in combobox
            empIDbox.setItems(empChoice);

            //close the connection
            statement.close();
            conn1.close();


        } catch (Exception e) {
            System.err.println("error" + e);
        }


    }


    //Load product information
    public void loadProduct(ActionEvent actionEvent) throws SQLException {

        //clear elements upon calling the method again
        prodData.clear();;
        prodChoice.clear();


        //set the appropriate values to the table columns
        prodIDCol.setCellValueFactory(new PropertyValueFactory<ProductTable,String>("prodIDCol"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<ProductTable,String>("prodNameCol"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<ProductTable,String>("prodPriceCol"));

        //establish DB connection
        DBconnection conn = new DBconnection();
        Connection conn1 = conn.DBcon();

        //Select everything from the Product table
        String sql = "SELECT * FROM Product";
        Statement statement = conn1.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);


        //add all the elements from the columns in the database into the prodChoice list
        while (resultSet.next())
        {
            prodData.add(new ProductTable(
                    resultSet.getString("ProductID"),
                    resultSet.getString("ProductName"),
                    resultSet.getString("ProductPrice")
            ));

            prodChoice.add(resultSet.getString("productID"));
        }

        //set the list onto the product Table
        prodTable.setItems(prodData);

        //set product ID in combobox
        productBox.setItems(prodChoice);

        //close the connection
        statement.close();
        conn1.close();


    }

    //Create Order for customer
    public void createOrder(ActionEvent actionEvent) throws SQLException {

        DBconnection conn = new DBconnection();
        Connection conn1 = conn.DBcon();

        String makeOrder = "INSERT INTO Orders (OrderDate, CustID, EmpID, ProductID, Price) VALUES (?,?,?,?,?)";
        Statement st = conn1.createStatement();
        ResultSet rs = st.executeQuery("SELECT ProductPrice FROM Product WHERE ProductID = 1");

        //obtain the values from the customer and employee selection as well as date
        String custIDvalues = custIDbox.getSelectionModel().getSelectedItem().toString();
        String empIDvalues = empIDbox.getSelectionModel().getSelectedItem().toString();
        String prodValues = productBox.getSelectionModel().getSelectedItem().toString();

        //create a statement which add the values into the database
        PreparedStatement statement = conn1.prepareStatement(makeOrder);
        statement.setString(1,Date.getEditor().getText());
        statement.setString(2,custIDvalues);
        statement.setString(3,empIDvalues);
        statement.setString(4,prodValues);


        if(rs.next())
        {
            String price = rs.getString("ProductPrice");
            statement.setString(5,price);
        }

        //save the changes to the database
        statement.executeUpdate();


        //print alert message to screen
        alertLabel.setText("Sucessfully created the Order!");

        //close connections
        st.close();
        rs.close();
        statement.close();
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

    public void goEmployee(ActionEvent actionEvent) throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Employee/Employee.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void showInvoice(ActionEvent actionEvent) throws IOException {

        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Invoice/Invoice.fxml"));
        Stage newstage = new Stage();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }
}
