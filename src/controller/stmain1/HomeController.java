/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.stmain1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import custom.CustomTf;
import custom.CustomPf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import stmodel.AppConfig;
import stmodel.LoggerMain;

/**
 * FXML Controller class
 *
 * @author
 */
public class HomeController implements Initializable {

    @FXML
    private TextField tfUserName;
    @FXML
    private Button btnUserNameTfClear;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private PasswordField pfUserPassword;

    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();


    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane apDesignPane;
    private StackPane spMainContent;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        cTF.clearTextFieldByButton(tfUserName, btnUserNameTfClear);
        cPF.clearPassFieldByButton(pfUserPassword, btnPassFieldClear);
        BooleanBinding boolenBinding = tfUserName.textProperty().isEmpty()
                .or(pfUserPassword.textProperty().isEmpty());

        btnLogin.disableProperty().bind(boolenBinding);
         */

        LoggerMain.initialize();
        AppConfig.initialize();
    }

    @FXML
    private void hlViewAllReceipt(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/stmain1/ViewAllReceipts.fxml"));
        
        loader.load();
        Parent parent = loader.getRoot();
        parent.getStylesheets().add("/style/MainStyle.css");
        Scene viewReceiptScene = new Scene(parent);
        Stage viewReceiptStage = new Stage();
        //createReceiptStage.setMaximized(true);
        viewReceiptStage.setTitle("VIEW RECENT RECEIPTS");
        viewReceiptStage.setScene(viewReceiptScene);
        viewReceiptStage.setResizable(false);
        viewReceiptStage.show();

    }

    @FXML
    private void hlAddDevotee(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/stmain1/AddDevotee.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        parent.getStylesheets().add("/style/MainStyle.css");
        Scene addDevScene = new Scene(parent);
        Stage addDevStage = new Stage();
        //createReceiptStage.setMaximized(true);
        addDevStage.setTitle("ADD DEVOTEE");
        addDevStage.setScene(addDevScene);
        addDevStage.setResizable(false);
        addDevStage.show();
    }

    @FXML
    private void h1ViewAllDevotees(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/stmain1/ViewAllDevotees.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        parent.getStylesheets().add("/style/MainStyle.css");
        Scene viewAllDevScene = new Scene(parent);
        Stage viewAllDevStage = new Stage();
        //createReceiptStage.setMaximized(true);
        viewAllDevStage.setTitle("VIEW ALL DEVOTEES");
        viewAllDevStage.setScene(viewAllDevScene);
        viewAllDevStage.setResizable(false);
        viewAllDevStage.show();

    }
    
}
