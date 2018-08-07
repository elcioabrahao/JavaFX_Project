/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.stmain1;

import bean.DevoteeDetails;
import bean.ReceiptDetails;
import dataBase.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stmodel.Converters;

/**
 * FXML Controller class
 *
 * @author
 */
public class ViewDevoteeController implements Initializable {

    final static Logger logger = Logger.getLogger(ViewDevoteeController.class.getName());

    @FXML
    private Label lblFullName;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblState;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblZip;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCreateReceipt;

    @FXML
    private AnchorPane apRoot;

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setReceiptDetails(rd, dd);
        //viewReceiptDetails();
    }
    private DevoteeDetails dd = null;

    public void setDevoteeDetails(DevoteeDetails dd) {
        this.dd = dd;
    }

    public void viewDevoteeDetails() {

        logger.info("dd.phone" + dd.getPhone() + dd.getAddress());
        logger.info("dd.phone" + dd.getAddress());

        //lblDate.setText(Converters.replaceNullDate(rd.getServiceDate()));
        lblFullName.setText(Converters.replaceNull(dd.getFirstName() + " " + dd.getLastName()));
        lblAddress.setText(Converters.replaceNull((dd.getAddress())));
        lblCity.setText(Converters.replaceNull((dd.getCity())));
        lblState.setText(Converters.replaceNull((dd.getStateDet())));
        lblCountry.setText(Converters.replaceNull((dd.getCountryDet())));
        lblZip.setText(Converters.replaceNull((dd.getZip())));
        lblPhone.setText(Converters.replaceNull((dd.getPhone())));
        lblEmail.setText(Converters.replaceNull((dd.getEmail())));
    }

    @FXML
    private void btnCreateReceiptOnAction(ActionEvent event) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/stmain1/CreateReceiptFXML.fxml").openStream());

        CreateReceiptController crc = fXMLLoader.getController();
        crc.setDevoteeDetailstoForm(dd);
        crc.btnClrDonation.getStylesheets().add("/style/btnOnText.css");
        crc.btnClrPayDet.getStylesheets().add("/style/btnOnText.css");
        crc.btnClrService.getStylesheets().add("/style/btnOnText.css");

        Parent parent = fXMLLoader.getRoot();
        parent.getStylesheets().add("/style/MainStyle.css");
        Scene createReceiptScene = new Scene(parent);
        Stage createReceiptStage = new Stage();
        //createReceiptStage.setMaximized(true);
        createReceiptStage.setTitle("CREATE RECEIPT");
        createReceiptStage.setScene(createReceiptScene);
        createReceiptStage.setResizable(false);
        createReceiptStage.show();
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {

        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/stmain1/UpdateDevotee.fxml").openStream());

            UpdateDevoteeController updateDC = fXMLLoader.getController();
            logger.info("controller.stmain1.ViewDevoteeController.btnUpdateOnAction()");
            logger.info("dd.getDevoteeId(): " + dd.getDevoteeId());

            updateDC.setDevoteeDetails(dd);

            AnchorPane acpane = fXMLLoader.getRoot();
            acpane.getStylesheets().add("/style/MainStyle.css");
            updateDC.btnClrFirstName.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrLastName.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrPhone.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrEmail.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrAddress.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrState.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrCity.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrCountry.getStylesheets().add("/style/btnOnText.css");
            updateDC.btnClrZip.getStylesheets().add("/style/btnOnText.css");

            Scene adminPanelScene = new Scene(acpane);
            Stage adminPanelStage = new Stage();

            adminPanelStage.setScene(adminPanelScene);
            adminPanelStage.setTitle("Update DEVOTEE");

            Stage stage = (Stage) btnUpdate.getScene().getWindow();
            stage.close();
            // logger.info("ADD DEVOTEE SCREEN IS CLOSED AFTER SUCCESSFULLY SAVING DEVOTEE DETAILS TO DATABASE");

            adminPanelStage.setResizable(false);
            adminPanelStage.show();
            // logger.info("VIEW DEVOTEE SCREEN IS DISPLAYED NOW");
        } catch (IOException ex) {
            Logger.getLogger(ViewDevoteeController.class.getName()).log(Level.FATAL, null, ex);
        }

    }
}
