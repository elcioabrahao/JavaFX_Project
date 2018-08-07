/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.stmain1;

import custom.CustomTf;

import java.net.URL;
import java.util.ResourceBundle;

import bean.DevoteeDetails;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import stmodel.Converters;
import stmodel.DevoteeDetailsCRUD;
import stmodel.FieldValidator;

/**
 * FXML Controller class
 *
 * @author
 */
public class UpdateDevoteeController implements Initializable {

    final static Logger logger = Logger.getLogger(UpdateDevoteeController.class.getName());
    @FXML
    private Label tfError;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfState;
    @FXML
    private TextField tfCountry;
    @FXML
    private TextField tfZip;
    @FXML
    private Button btnSave;
    @FXML
    public Button btnClrDate;
    @FXML
    public Button btnClrFirstName;
    @FXML
    public Button btnClrLastName;
    @FXML
    public Button btnClrPhone;
    @FXML
    public Button btnClrEmail;
    @FXML
    public Button btnClrAddress;
    @FXML
    public Button btnClrCity;
    @FXML
    public Button btnClrState;
    @FXML
    public Button btnClrCountry;
    @FXML
    public Button btnClrZip;
    @FXML
    public AnchorPane acpane;

    DevoteeDetails dd = new DevoteeDetails();
    DevoteeDetails sdd = new DevoteeDetails();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomTf cTf = new CustomTf();
        tfError.setText("");

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        logger.info("dateeeee: (" + sqlDate + "),(" + sqlDate.toString() + ")");
        tfCountry.setText("US");
        tfState.setText("CA");

        logger.info("TFCOUNTRY: " + tfCountry.getText());
        cTf.clearTextFieldByButton(tfFirstName, btnClrFirstName);
        cTf.clearTextFieldByButton(tfLastName, btnClrLastName);
        cTf.clearTextFieldByButton(tfPhone, btnClrPhone);
        cTf.clearTextFieldByButton(tfEmail, btnClrEmail);
        cTf.clearTextFieldByButton(tfCity, btnClrCity);
        cTf.clearTextFieldByButton(tfAddress, btnClrAddress);
        cTf.clearTextFieldByButton(tfState, btnClrState);
        cTf.clearTextFieldByButton(tfCountry, btnClrCountry);
        cTf.clearTextFieldByButton(tfZip, btnClrZip);

        BooleanBinding binding = tfFirstName.textProperty().isEmpty();

        /*
        BooleanBinding binding = tfFirstName.textProperty().isEmpty()
                .or(tfFullName.textProperty().isEmpty()
                        .or(tfPhone.textProperty().isEmpty())
                        .or(tfPassword.textProperty().isEmpty()));
         */
        btnSave.disableProperty().bind(binding);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {

        FieldValidator fv = new FieldValidator();
        String errorDetail = fv.doDevValidation(tfFirstName.getText(), tfLastName.getText(), tfPhone.getText(), tfEmail.getText(), tfAddress.getText(), tfCity.getText(), tfState.getText(), tfCountry.getText());
        tfError.setText(errorDetail);
        BooleanBinding binding = tfError.textProperty().isEmpty();
        btnSave.disableProperty().bind(binding);

        if (tfError.getText().equalsIgnoreCase("")) {

            boolean success = false;
            long time = System.currentTimeMillis();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
            logger.info("Time in milliseconds :" + timestamp);
            //dd.setDateCreated(timestamp);
            dd.setDateModified(timestamp);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            dd.setFirstName(tfFirstName.getText().toUpperCase());
            dd.setLastName(tfLastName.getText().toUpperCase());
            dd.setPhone(tfPhone.getText().toUpperCase());
            dd.setEmail(tfEmail.getText().toUpperCase());
            dd.setCity(tfCity.getText().toUpperCase());
            dd.setStateDet(tfState.getText().toUpperCase());
            dd.setCountryDet(tfCountry.getText().toUpperCase());
            dd.setAddress(tfAddress.getText().toUpperCase());
            dd.setZip(tfZip.getText().toUpperCase());
            DevoteeDetailsCRUD ddc = new DevoteeDetailsCRUD();
            success = ddc.updateDevoteeDet(dd).isSuccessSave();

            if (success) {
                try {
                    logger.info("controller.stmain1.AddDevoteeController.btnSaveOnAction() ---- SUCCESSFULLY SAVED DEVOTEE DETAILS");

                    FXMLLoader fXMLLoader = new FXMLLoader();
                    fXMLLoader.load(getClass().getResource("/view/stmain1/ViewDevotee.fxml").openStream());

                    ViewDevoteeController viewDC = fXMLLoader.getController();

                    viewDC.setDevoteeDetails(dd);
                    viewDC.viewDevoteeDetails();

                    acpane = fXMLLoader.getRoot();
                    acpane.getStylesheets().add("/style/MainStyle.css");

                    Scene adminPanelScene = new Scene(acpane);
                    Stage adminPanelStage = new Stage();

                    adminPanelStage.setScene(adminPanelScene);
                    adminPanelStage.setTitle("VIEW DEVOTEE");

                    Stage stage = (Stage) btnSave.getScene().getWindow();
                    stage.close();
                    logger.info("ADD DEVOTEE SCREEN IS CLOSED AFTER SUCCESSFULLY SAVING DEVOTEE DETAILS TO DATABASE");
                    adminPanelStage.setResizable(false);

                    adminPanelStage.show();
                    logger.info("VIEW DEVOTEE SCREEN IS DISPLAYED NOW");
                } catch (IOException e) {
                    Logger.getLogger(UpdateDevoteeController.class.getName()).log(Level.FATAL, null, e);
                }catch (Exception e1) {
                    Logger.getLogger(UpdateDevoteeController.class.getName()).log(Level.FATAL, null, e1);
                }
            }
        }

    }

    void setDevoteeDetails(DevoteeDetails dd) {
        this.dd = dd;
        tfFirstName.setText(Converters.replaceNull(dd.getFirstName()));
        tfLastName.setText(Converters.replaceNull(dd.getLastName()));
        tfPhone.setText(Converters.replaceNull(dd.getPhone()));
        tfEmail.setText(Converters.replaceNull(dd.getEmail()));
        tfAddress.setText(Converters.replaceNull(dd.getAddress()));
        tfCity.setText(Converters.replaceNull(dd.getCity()));
        tfState.setText(Converters.replaceNull(dd.getStateDet()));
        tfCountry.setText(Converters.replaceNull(dd.getCountryDet()));
        tfZip.setText(Converters.replaceNull(dd.getZip()));

    }

}
