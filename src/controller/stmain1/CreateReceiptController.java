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
import bean.ReceiptDetails;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stmodel.Converters;
import stmodel.FieldValidator;
import stmodel.ReceiptDetailsCRUD;

/**
 * FXML Controller class
 *
 * @author
 */
public class CreateReceiptController implements Initializable {

    final static Logger logger = Logger.getLogger(CreateReceiptController.class.getName());

    @FXML
    private Label tfDate;

    @FXML
    private Label tfError;
    @FXML
    private Label tfDevoteeID;
    @FXML
    private Label tfFirstName;
    @FXML
    private Label tfLastName;
    @FXML
    private Label tfPhone;
    @FXML
    private Label tfEmail;
    @FXML
    private Label tfAddress;
    @FXML
    private Label tfCity;
    @FXML
    private Label tfState;
    @FXML
    private Label tfCountry;
    @FXML
    private Label tfZip;
    @FXML
    private TextField tfDonation;
    @FXML
    private TextField tfService;
    @FXML
    private ComboBox payType;
    @FXML
    private TextField tfPayDet;

    @FXML
    private Button btnSave;
    @FXML
    public Button btnClrDonation;
    @FXML
    public Button btnClrService;
    @FXML
    public Button btnClrPayDet;
    @FXML
    public VBox acpane;

    DevoteeDetails dd = new DevoteeDetails();
    ReceiptDetails rd = new ReceiptDetails();

    ReceiptDetails srd = new ReceiptDetails();
    DevoteeDetails sdd = new DevoteeDetails();

    public void setDd(DevoteeDetails dd) {
        this.dd = dd;
    }

    public DevoteeDetails getDd() {
        return dd;
    }

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
//        tfBtnSaveClicked.setText("");

        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        //logger.info("dateeeee: ("+sqlDate+"),("+sqlDate.toString()+")");
        tfDate.setText(sqlDate.toString());
        ////////////////tfFirstName.setText(dd.get);
        logger.info("TFDATE: " + tfDate.getText());
        //tfCountry.setText("US");
        //tfState.setText("CA");
        tfService.setText("ARCHANA");
        //logger.info("TFCOUNTRY: "+tfCountry.getText());
        payType.getItems().add("CHECK");
        payType.getItems().add("CARD");
        payType.getItems().add("CASH");

        cTf.clearTextFieldByButton(tfDonation, btnClrDonation);
        cTf.clearTextFieldByButton(tfService, btnClrService);
        cTf.clearTextFieldByButton(tfPayDet, btnClrPayDet);

        BooleanBinding binding = tfFirstName.textProperty().isEmpty().or(tfDonation.textProperty().isEmpty());

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
        logger.info("controller.stmain1.CreateReceiptController.btnSaveOnAction()");
        String errorDetail = fv.doValidation(Converters.replaceNull(tfService.getText()), Converters.replaceNull(tfDonation.getText()), Converters.replaceNull(tfPayDet.getText()));
        tfError.setText(errorDetail);
        logger.info("tferror errordetail: " + errorDetail);
        BooleanBinding binding = tfError.textProperty().isEmpty();
        btnSave.disableProperty().bind(binding);

        if (tfError.getText().equalsIgnoreCase("")) {

            boolean success = false;
            long time = System.currentTimeMillis();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
            logger.info("Time in milliseconds :" + timestamp);
            rd.setDateCreated(timestamp);
            rd.setDateModified(timestamp);
            //dd.setDateCreated(timestamp);
            // dd.setDateModified(timestamp);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startDate = null;

            try {
                startDate = df.parse(tfDate.getText());
            } catch (ParseException ex) {
                Logger.getLogger(CreateReceiptController.class.getName()).log(Level.FATAL, null, ex);
            }
            java.sql.Date sqlServiceDate = new java.sql.Date(startDate.getTime());
            rd.setServiceDate(sqlServiceDate);
            dd.setDevoteeId(Integer.parseInt(tfDevoteeID.getText()));
            dd.setFirstName(tfFirstName.getText().toUpperCase());
            dd.setLastName(tfLastName.getText().toUpperCase());
            dd.setPhone(tfPhone.getText().toUpperCase());
            dd.setEmail(tfEmail.getText().toUpperCase());
            dd.setCity(tfCity.getText().toUpperCase());
            dd.setStateDet(tfState.getText().toUpperCase());
            dd.setCountryDet(tfCountry.getText().toUpperCase());
            dd.setAddress(tfAddress.getText().toUpperCase());
            dd.setZip(tfZip.getText().toUpperCase());

            rd.setDonation(Integer.parseInt(tfDonation.getText().toUpperCase()));
            rd.setService(tfService.getText().toUpperCase());

            String payTypeStr = "CASH";
            if (!payType.getSelectionModel().isEmpty()) {
                payTypeStr = payType.getSelectionModel().getSelectedItem().toString();
            } else if (!payType.getPromptText().isEmpty()) {
                payTypeStr = payType.getPromptText();
            }
            if (payTypeStr.equalsIgnoreCase("CASH")) {
                rd.setPayment_type("CASH");
                rd.setPayment_det(tfPayDet.getText());
            } else if (payTypeStr.equalsIgnoreCase("CHECK")) {
                rd.setPayment_type("CHEQUE");
                rd.setPayment_det(tfPayDet.getText());
            } else {
                rd.setPayment_type("CARD");
                rd.setPayment_det(tfPayDet.getText());
            }

            ReceiptDetailsCRUD rdc = new ReceiptDetailsCRUD();
            success = rdc.saveReceipt(rd, dd).isSuccessSave();
            if (success) {
                try {
                    logger.info("controller.stmain1.CreateReceiptController.btnSaveOnAction() ----- SUCESSFULLY SAVED DATA");

                    FXMLLoader fXMLLoader = new FXMLLoader();
                    fXMLLoader.load(getClass().getResource("/view/stmain1/ViewReceipPrint.fxml").openStream());

                    ViewReceiptController viewRC = fXMLLoader.getController();
                    viewRC.setReceiptDetails(rd);
                    viewRC.setDevoteeDetails(dd);
                    viewRC.viewReceiptDetails();

                    acpane = fXMLLoader.getRoot();

                    Scene adminPanelScene = new Scene(acpane);
                    Stage adminPanelStage = new Stage();

                    adminPanelStage.setScene(adminPanelScene);
                    adminPanelStage.setTitle("VIEW RECEIPT");

                    Stage stage = (Stage) btnSave.getScene().getWindow();
                    stage.close();
                    logger.info("CREATE RECEIPT SCREEN IS CLOSED AFTER SUCCESSFULLY SAVING RECEIPT AND DEVOTEE DETAILS TO DATABASE");
                    adminPanelStage.setResizable(false);
                    adminPanelStage.show();
                    logger.info("VIEW RECEIPT SCREEN IS DISPLAYED NOW");
                } catch (IOException e) {
                    Logger.getLogger(CreateReceiptController.class.getName()).log(Level.FATAL, null, e);
                } catch (Exception e1){
                    Logger.getLogger(CreateReceiptController.class.getName()).log(Level.FATAL, null, e1);
                }

            }
        }

    }

    public void setDevoteeDetailstoForm(DevoteeDetails dd) {
        tfDevoteeID.setText(dd.getDevoteeId() + "");
        tfFirstName.setText(Converters.replaceNull(dd.getFirstName()));
        tfLastName.setText(Converters.replaceNull(dd.getLastName()));
        tfPhone.setText(Converters.replaceNull(dd.getPhone()));
        tfEmail.setText(Converters.replaceNull(dd.getEmail()));
        tfAddress.setText(Converters.replaceNull(dd.getAddress()));
        tfCity.setText(Converters.replaceNull(dd.getCity()));
        tfState.setText(Converters.replaceNull(dd.getStateDet()));
        tfCountry.setText(Converters.replaceNull(dd.getCountryDet()));
        tfZip.setText(Converters.replaceNull(dd.getZip()));
        //tfService.setText("ARCHANA");
    }
}
