/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.stmain1;

import bean.DevoteeDetails;
import bean.ReceiptDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stmodel.DevoteeDetailsCRUD;

/**
 * FXML Controller class
 *
 * @author 
 */
public class ViewAllDevoteesController implements Initializable {

    final static Logger logger = Logger.getLogger(ViewAllDevoteesController.class.getName());
    @FXML
    private AnchorPane acReceiptMainContent;

    @FXML
    private TextField tfSearchbyName;
    @FXML
    private TextField tfSearchbyPhone;
    @FXML
    private TextField tfSearchbyEmail;
    @FXML
    private Button btnCreateReceipt;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;

    @FXML
    private TableView<DevoteeDetails> tblAllDevotee;
    @FXML
    private TableColumn<Object, Object> tblDevName;
    @FXML
    private TableColumn<Object, Object> tblDevPhone;
    @FXML
    private TableColumn<Object, Object> tblDevEmail;
    @FXML
    private TableColumn<Object, Object> tblDevAddr;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAllDevotees();
    }
    private ReceiptDetails rd = null;
    private DevoteeDetails dd = null;

    public void setReceiptDetails(ReceiptDetails rd) {
        this.rd = rd;
    }

    public void setDevoteeDetails(DevoteeDetails dd) {
        this.dd = dd;
    }

    void viewAllDevotees() {

        DevoteeDetailsCRUD ddCRUD = new DevoteeDetailsCRUD();
        dd = ddCRUD.getAllDevoteeDetails();

        tblAllDevotee.setItems(dd.getDevoteeDetails());
        logger.info("jjjjjjjjjjjjjjj" + tblAllDevotee.getItems());
        tblDevName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblDevPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblDevEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblDevAddr.setCellValueFactory(new PropertyValueFactory<>("addrComplete"));
    }

    @FXML
    private void tblDevoteeOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            //selectedView();
        } else {
            logger.info("CLICK");
        }
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tfSearchbyName.clear();
        tfSearchbyPhone.clear();
        tfSearchbyEmail.clear();

        dd.getDevoteeDetails().clear();
        viewAllDevotees();
    }

    private void selectedView() {

        DevoteeDetails dd = tblAllDevotee.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/stmain1/AddDevotee.fxml"));
            loader.load();
            Parent parent = loader.getRoot();
            Scene addDevScene = new Scene(parent);
            Stage addDevStage = new Stage();
            addDevStage.setTitle("ADD DEVOTEE");
            addDevStage.setScene(addDevScene);
            addDevStage.setResizable(false);
            addDevStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewAllDevoteesController.class.getName()).log(Level.FATAL, null, ex);
        }
    }

    @FXML
    private void btnCreateReceiptOnAction(ActionEvent event) throws IOException {
        logger.info("controller.stmain1.ViewAllDevoteesController.btnCreateReceiptOnAction()");
        DevoteeDetails dd1 = tblAllDevotee.getSelectionModel().getSelectedItem();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/stmain1/CreateReceiptFXML.fxml").openStream());

        CreateReceiptController crc = fXMLLoader.getController();
        crc.setDevoteeDetailstoForm(dd1);
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
            DevoteeDetails dd1 = tblAllDevotee.getSelectionModel().getSelectedItem();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/stmain1/UpdateDevotee.fxml").openStream());

            UpdateDevoteeController updateDC = fXMLLoader.getController();
            logger.info("controller.stmain1.ViewDevoteeController.btnUpdateOnAction()");
            logger.info("dd.getDevoteeId(): " + dd1.getDevoteeId());

            updateDC.setDevoteeDetails(dd1);

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

    @FXML
    private void tfSearchbyNameOnKeyReleased(Event event) {
        DevoteeDetailsCRUD ddc = new DevoteeDetailsCRUD();
        String name = tfSearchbyName.getText().trim();
        ddc.searchViewByName(name, dd);
        SortedList<DevoteeDetails> sortList = new SortedList<>(dd.getDevoteeDetails());
        tblAllDevotee.setItems(dd.getDevoteeDetails());
        logger.info("jjjjjjjjjjjjjjj" + tblAllDevotee.getItems());
        tblDevName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblDevPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblDevEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblDevAddr.setCellValueFactory(new PropertyValueFactory<>("addrComplete"));
        tfSearchbyPhone.clear();
        tfSearchbyEmail.clear();
    }

    @FXML
    private void tfSearchbyPhoneOnKeyReleased(Event event) {
        DevoteeDetailsCRUD ddc = new DevoteeDetailsCRUD();
        String phone = tfSearchbyPhone.getText().trim();
        ddc.searchViewByPhone(phone, dd);
        SortedList<DevoteeDetails> sortList = new SortedList<>(dd.getDevoteeDetails());
        tblAllDevotee.setItems(dd.getDevoteeDetails());
        logger.info("jjjjjjjjjjjjjjj" + tblAllDevotee.getItems());
        tblDevName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblDevPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblDevEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblDevAddr.setCellValueFactory(new PropertyValueFactory<>("addrComplete"));
        tfSearchbyName.clear();
        tfSearchbyEmail.clear();
    }

    @FXML
    private void tfSearchbyEmailOnKeyReleased(Event event) {
        DevoteeDetailsCRUD ddc = new DevoteeDetailsCRUD();
        String email = tfSearchbyEmail.getText().trim();
        ddc.searchViewByEmail(email, dd);
        SortedList<DevoteeDetails> sortList = new SortedList<>(dd.getDevoteeDetails());
        tblAllDevotee.setItems(dd.getDevoteeDetails());
        logger.info("jjjjjjjjjjjjjjj" + tblAllDevotee.getItems());
        tblDevName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblDevPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tblDevEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblDevAddr.setCellValueFactory(new PropertyValueFactory<>("addrComplete"));
        tfSearchbyName.clear();
        tfSearchbyPhone.clear();
    }
}
