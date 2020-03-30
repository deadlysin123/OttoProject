package Main.Login;


import Main.DBconnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXButton loginButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private JFXSnackbar notification;
    @FXML
    private AnchorPane root;


    //create a connection and statement objects
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // log in to Main Page
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    login();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    //login method
    @FXML
    private void login() throws SQLException {
        String userLogin = usernameField.getText();
        String pwLogin = passwordField.getText();
        notification = new JFXSnackbar(root);
        var label =  new Label("Username or Password is incorrect, Please try again!");


        // database query for user login information from the table Employee
        con = DBconnection.DBcon();
        String loginSQL = "SELECT * FROM Employee WHERE EmpFName = ? and EmpPass = ?";
        try {
            preparedStatement = con.prepareStatement(loginSQL);
            preparedStatement.setString(1, userLogin);
            preparedStatement.setString(2, pwLogin);
            resultSet = preparedStatement.executeQuery();

            // input validation
            if (!resultSet.next()) {

                // if inputs are incorrect display this error pop up
                notification.enqueue(new JFXSnackbar.SnackbarEvent(label));
            } else {
                System.out.println("Success");

                // load Main Page
                Parent nextwindow = FXMLLoader.load(getClass().getResource("..//MainPage/MainPage.fxml"));
                Scene newscene = new Scene(nextwindow);
                Stage newstage = (Stage) root.getScene().getWindow();
                newstage.setScene(newscene);
                newstage.show();
            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
            resultSet.close();
            con.close();
        }
    }


}

