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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stmodel.AddImageToPdf;
import stmodel.AppConfig;
import stmodel.Converters;
import stmodel.NodePrinter;
import stmodel.SendMail;

/**
 * FXML Controller class
 *
 * @author
 */
public class ViewReceiptController implements Initializable {
    final static Logger logger = Logger.getLogger(ViewReceiptController.class.getName());
    
    @FXML
    private Label lblReceiptId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblAddress1;
    @FXML
    private Label lblEmailHistory;
    @FXML
    private Label lblPhoneEmail;
    @FXML
    private Label lblPaymentMethod;
    @FXML
    private Label lblPaymentDet;
    @FXML
    private Label lblServiceDesc;
    @FXML
    private Label lblDonationAmount;
    @FXML
    private ImageView imgHeaderTemple;
    @FXML
    private Button btnPrint;
    @FXML
    private Button btnEmail;
    @FXML
    private AnchorPane apRoot;
    @FXML
    private VBox vBoxRoot;
    
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logger.info("inside initialize");
        vBoxRoot.getStylesheets().add("/style/MainStyle.css");
        //setReceiptDetails(rd, dd);
        //viewReceiptDetails();
    }    
    private ReceiptDetails rd = null;
    private DevoteeDetails dd = null;
    
    public void setReceiptDetails(ReceiptDetails rd){
        this.rd = rd;
    }
    public void setDevoteeDetails(DevoteeDetails dd){
        this.dd = dd;
    }
    public String getNameAddrComplete(String firstName, String lastName, String address, String city, String state, String country, String zip, String phone, String email){
        
        String nameAddr = firstName;
        if(!lastName.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + " "+lastName;
            else
                nameAddr = nameAddr + lastName;
        }
        if(!address.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+"\n"+address;
            else
                nameAddr = nameAddr + "\n"+address;
        }
        if(!city.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+city;
            else
                nameAddr = nameAddr + city;
        }
        if(!state.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+state;
            else
                nameAddr = nameAddr + state;
        }
            
        if(!country.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+country;
            else
                nameAddr = nameAddr + country;
        }
        
        if(!zip.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+zip;
            else
                nameAddr = nameAddr + zip;
        }
        
        if(!phone.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+"\n"+phone;
            else
                nameAddr = nameAddr + "\n"+phone;
        } 
        if(!email.equals("")){
            if(! nameAddr.equals(""))
                nameAddr = nameAddr + ", "+"\n"+email;
            else
                nameAddr = nameAddr + "\n"+email;
        }
        nameAddr=nameAddr.replaceAll("(\n)\\1+","\n");
        
        return nameAddr;
    }
    public void viewReceiptDetails(){
        
       // imgHeaderTemple.setImage(new Image("/image/appreceiptheader.jpg",450, 100, false, false));
       // imgHeaderTemple.smoothProperty();
        //imgHeaderTemple.setImage(new Image("/image/appreceiptheader.jpg",100,100,false,false));
        //imgHeaderTemple.setPreserveRatio(true);
        //System.out.println("image size:"+imgHeaderTemple.getFitWidth()+" "+imgHeaderTemple.getFitHeight());
        //imgHeaderTemple.setFitWidth(0);
//        imgHeaderTemple.setImage(new Image("/image/logo.jpg"));
        logger.info("controller.stmain1.ViewReceiptController.viewReceiptDetails()");
        logger.info("dd.phone" +dd.getPhone()+dd.getAddress()+rd.getServiceDate()+rd.getService());
        logger.info("dd.phone" +dd.getAddress());
        
        lblReceiptId.setText("Receipt# "+(rd.getReceiptId())+""+"\r\n"+Converters.replaceNullDate(rd.getServiceDate()));
        //lblDate.setText(Converters.replaceNullDate(rd.getServiceDate()));
            
        String nameAddr = getNameAddrComplete(Converters.replaceNull(dd.getFirstName()), Converters.replaceNull(dd.getLastName()), Converters.replaceNull(dd.getAddress()), Converters.replaceNull(dd.getCity()), Converters.replaceNull(dd.getStateDet()), Converters.replaceNull(dd.getCountryDet()), Converters.replaceNull(dd.getZip()), Converters.replaceNull(dd.getPhone()), Converters.replaceNull(dd.getEmail()));
    
        lblName.setText(nameAddr);
        //lblAddress1.setText(Converters.replaceNull((dd.getCity()))+", "+Converters.replaceNull((dd.getStateDet()))+", "+Converters.replaceNull((dd.getCountryDet()))+", "+Converters.replaceNull((dd.getZip())));
        //lblPhoneEmail.setText(("Phone: "+Converters.replaceNull((dd.getPhone()))+" Email: " +Converters.replaceNull((dd.getEmail()))));
       
        //lblAddress.setText(Converters.replaceNull((dd.getAddress())));
        lblDonationAmount.setText(Converters.replaceNull(("$"+ rd.getDonation()+ " ")));
        lblServiceDesc.setText(Converters.replaceNull((rd.getService())));
        lblPaymentMethod.setText(Converters.replaceNull((rd.getPayment_type())));
        lblPaymentDet.setText(Converters.replaceNull((rd.getPayment_det())));

        if(Converters.replaceNull(dd.getEmail()).equals(""))
            btnEmail.setVisible(false);
    }
    
    
     @FXML
    private void btnEmailOnAction(ActionEvent event) throws IOException {
      
        Stage oldStage = (Stage) btnEmail.getScene().getWindow();
         WritableImage snapshot = apRoot.snapshot(null, null);
         AddImageToPdf aip  = new AddImageToPdf();
         aip.addImage(snapshot);
         logger.info("controller.stmain1.ViewReceiptController.btnEmailOnAction() -- PDF CREATED SUCCESSFULLY");
         logger.info("controller.stmain1.ViewReceiptController.btnEmailOnAction() -- "+dd.getEmail());
       //  if(!dd.getEmail().equals(null))
       String fromName = AppConfig.getProperties().getProperty("email.from.name");
       String fromAddr = AppConfig.getProperties().getProperty("email.from");
       String receiptSub = AppConfig.getProperties().getProperty("receipt.email.subject");
       String emailUser = AppConfig.getProperties().getProperty("email.username");
       String emailPwd = AppConfig.getProperties().getProperty("email.pwd");
       String receiptBody = AppConfig.getProperties().getProperty("receipt.email.body");
       
         DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
       Date dateobj = new Date();
       
             if(SendMail.composeEmail(fromName, fromAddr, Converters.replaceNull(dd.getEmail()), receiptSub,receiptBody, "Receipt.pdf","temp1/Receipt.pdf", emailUser, emailPwd))
                 lblEmailHistory.setText( df.format(dateobj)+" -- Email Sent Successfully");
             else
                 lblEmailHistory.setText(df.format(dateobj)+" -- Email Not Sent");
        lblEmailHistory.setVisible(true);
    }
    
    @FXML
    private void btnPrintOnAction(ActionEvent event) {
       
        Stage oldStage = (Stage) btnPrint.getScene().getWindow();
        
 
        //NodePrinter.print(bp);
        NodePrinter.print(apRoot);
        //oldStage.close();
        
    }
}
