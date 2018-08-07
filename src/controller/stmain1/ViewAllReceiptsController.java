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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stmodel.ReceiptDetailsCRUD;

/**
 * FXML Controller class
 *
 * @author
 */
public class ViewAllReceiptsController implements Initializable {

    @FXML
    private AnchorPane acReceiptMainContent;

    final static Logger logger = Logger.getLogger(ViewAllReceiptsController.class.getName());
    @FXML
    private TableView<ReceiptDetails> tblAllReceipts;
    @FXML
    private TableColumn<Object, Object> tblReceiptNo;
    @FXML
    private TableColumn<Object, Object> tblReceiptDate;
    @FXML
    private TableColumn<Object, Object> tblDevoteeName;
    @FXML
    private TableColumn<Object, Object> tblDevoteePhone;
    @FXML
    private TableColumn<Object, Object> tblServiceDesc;
    @FXML
    private TableColumn<Object, Object> tblDonation;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewAllReceipts();
    }
    private ReceiptDetails rd = null;
    private DevoteeDetails dd = null;

    public void setReceiptDetails(ReceiptDetails rd) {
        this.rd = rd;
    }

    public void setDevoteeDetails(DevoteeDetails dd) {
        this.dd = dd;
    }

    void viewAllReceipts() {

        ReceiptDetailsCRUD rdCRUD = new ReceiptDetailsCRUD();
        rd = rdCRUD.getAllReceiptDetails();

        tblAllReceipts.setItems(rd.getReceiptDetails());
        tblReceiptNo.setCellValueFactory(new PropertyValueFactory<>("receiptId"));
        tblReceiptDate.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));
        tblDevoteeName.setCellValueFactory(new PropertyValueFactory<>("devoteeName"));
        tblDevoteePhone.setCellValueFactory(new PropertyValueFactory<>("devoteePhone"));
        tblServiceDesc.setCellValueFactory(new PropertyValueFactory<>("service"));
        tblDonation.setCellValueFactory(new PropertyValueFactory<>("donation"));

    }

    @FXML
    private void tblReceiptOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectedView();
        } else {
            logger.info("CLICK");
        }
    }

    private void selectedView() {

        try {
            ReceiptDetails rd = tblAllReceipts.getSelectionModel().getSelectedItem();
            DevoteeDetails dd = rd.getDevoteeDet();
            if (!(rd == null)) {
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.load(getClass().getResource("/view/stmain1/ViewReceipPrint.fxml").openStream());

                ViewReceiptController viewRC = fXMLLoader.getController();
                viewRC.setReceiptDetails(rd);
                viewRC.setDevoteeDetails(dd);
                viewRC.viewReceiptDetails();
                VBox acpane = fXMLLoader.getRoot();

                Scene adminPanelScene = new Scene(acpane);
                Stage adminPanelStage = new Stage();

                adminPanelStage.setScene(adminPanelScene);
                adminPanelStage.setTitle("VIEW RECEIPT");

                // Stage stage = (Stage) btnSave.getScene().getWindow();
                // stage.close();
                logger.info("CREATE RECEIPT SCREEN IS CLOSED AFTER SUCCESSFULLY SAVING RECEIPT AND DEVOTEE DETAILS TO DATABASE");

                adminPanelStage.setResizable(false);
                adminPanelStage.show();
                logger.info("VIEW RECEIPT SCREEN IS DISPLAYED NOW");
            }

        } catch (IOException ex) {
            Logger.getLogger(ViewAllReceiptsController.class.getName()).log(Level.FATAL, null, ex);
        }
    }
}
