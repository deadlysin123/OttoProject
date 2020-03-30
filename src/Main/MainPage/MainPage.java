package Main.MainPage;

import Main.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


public class MainPage
{

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton newCust;

    @FXML
    private JFXButton newOrder;

    @FXML
    private JFXButton invoice;

    @FXML
    private JFXButton employee;


    public void custWindow() throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Customer/Customer.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void orderWindow() throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Order/Order.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void invoiceWindow() throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Invoice/Invoice.fxml"));
        Stage newstage = new Stage();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();
    }

    public void employeeWindow() throws IOException {
        Parent nextWindow = FXMLLoader.load(getClass().getResource("..//Employee/Employee.fxml"));
        Stage newstage = (Stage) root.getScene().getWindow();
        Scene newscene = new Scene(nextWindow);
        newstage.setScene(newscene);
        newstage.show();

    }

}
