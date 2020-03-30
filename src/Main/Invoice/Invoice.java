package Main.Invoice;


import Main.DBconnection;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Invoice implements Initializable
{
    @FXML
    private JFXButton closeBUtton;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView invoiceTable;
    @FXML
    private TableColumn invIDCol;
    @FXML
    private TableColumn invDateCol;
    @FXML
    private TableColumn invCustIDCol;
    @FXML
    private TableColumn invEmpIDCol;
    @FXML
    private TableColumn invProdIDCol;
    @FXML
    private TableColumn invProdPriceCol;


    ObservableList invoiceData = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Clear current elements in the list to prevent duplicates
        invoiceData.clear();


        //Set values into table columns
        invIDCol.setCellValueFactory(new PropertyValueFactory<InvoiceTable,String>("invIDCol"));
        invDateCol.setCellValueFactory(new PropertyValueFactory<InvoiceTable,String>("invDateCol"));
        invCustIDCol.setCellValueFactory(new PropertyValueFactory<InvoiceTable,String>("invCustIDCol"));
        invEmpIDCol.setCellValueFactory(new PropertyValueFactory<InvoiceTable,String>("invEmpIDCol"));
        invProdIDCol.setCellValueFactory(new PropertyValueFactory<InvoiceTable,String>("invProdIDCol"));
        invProdPriceCol.setCellValueFactory(new PropertyValueFactory<InvoiceTable,String>("invProdPriceCol"));


        try {
            //Establish DB connection
            DBconnection conn = new DBconnection();
            Connection conn1 = conn.DBcon();

            //Select everything from the Customer table
            String sql = "SELECT * FROM Orders";
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            //add all the elements from the columns in the database into the invoiceData list
            while (resultSet.next())
            {
                invoiceData.add(new InvoiceTable(
                        resultSet.getString("OrderID"),
                        resultSet.getString("OrderDate"),
                        resultSet.getString("CustID"),
                        resultSet.getString("EmpID"),
                        resultSet.getString("ProductID"),
                        resultSet.getString("Price")
                ));

            }

            //set the list onto the Customer Table
            invoiceTable.setItems(invoiceData);

            //close the connection
            statement.close();
            conn1.close();

        } catch (SQLException e) {
            System.err.println("error" + e);
        }


    }

    public void closeScene(ActionEvent actionEvent) {
        Stage stage = (Stage) closeBUtton.getScene().getWindow();
        stage.close();
    }
}

