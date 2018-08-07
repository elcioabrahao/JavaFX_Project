/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

import bean.DevoteeDetails;
import bean.ReceiptDetails;
import dataBase.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import sql.ReceiptDetailsSql;

/**
 *
 * @author PrasanthKumar
 */
public class ReceiptDetailsCRUD {
final static Logger logger = Logger.getLogger(ReceiptDetailsCRUD.class.getName());
    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public ReceiptDetails saveReceipt(ReceiptDetails rd, DevoteeDetails dd) {

        try {

            //DevoteeDetailsCRUD ddc = new DevoteeDetailsCRUD();
            //int dev_id = ddc.checkDevExists(dd);
            //dd.setDevoteeId(dev_id);
            //ddc.saveDevoteeDet(dd);
            int receiptID = this.getLastReceiptID()+1;
            rd.setReceiptId(receiptID);
            con = dbCon.geConnection();
            pst = con.prepareStatement(ReceiptDetailsSql.INSERT_RECEIPT_DETAILS);
            pst.setInt(1, receiptID);
            pst.setInt(2, dd.getDevoteeId());
            pst.setDate(3, (Date) rd.getServiceDate());
            pst.setInt(4, rd.getDonation());
            pst.setString(5, rd.getService());
            pst.setString(6, rd.getPayment_type());
            pst.setString(7, rd.getPayment_det());
            pst.setTimestamp(8, (Timestamp) rd.getDateCreated());
            pst.setTimestamp(9, (Timestamp) rd.getDateModified());
            //pst.setString(11, LocalDate.now().toString());

            pst.executeUpdate();
            pst.close();
            con.close();
            rd.setSuccessSave(true);
            return rd;

        } catch (SQLException ex) {
            Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, ex);
            rd.setSuccessSave(false);
            return rd;
        } catch (Exception e) {
            Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
            rd.setSuccessSave(false);
            return rd;
        }

    }

    public ReceiptDetails getAllReceiptDetails() {
                    ReceiptDetails rd = new ReceiptDetails();

        //DevoteeDetails dd = new DevoteeDetails();
        try {
            con = dbCon.geConnection();
            pst = con.prepareStatement(ReceiptDetailsSql.SELECT_lAST100_RECEIPT_DETAILS);
            rs = pst.executeQuery();
            while (rs.next()) {
                ReceiptDetails rd1 = new ReceiptDetails();
                DevoteeDetailsCRUD ddc = new DevoteeDetailsCRUD();
                DevoteeDetails dd1;
                
                rd1.setReceiptId(rs.getInt(1));
                rd1.setDevoteeId(rs.getInt(2));
                dd1 = ddc.getDevoteeDetails(rs.getInt(2));
                rd1.setServiceDate(rs.getDate(3));
                rd1.setDonation(rs.getInt(4));
                rd1.setService(rs.getString(5));
                rd1.setPayment_type(rs.getString(6));
                rd1.setPayment_det(rs.getString(7));
                rd1.setDevoteeDetail(dd1);
                rd1.setDevoteeName(Converters.replaceNull(dd1.getFirstName()));
                rd1.setDevoteePhone(Converters.replaceNull(dd1.getPhone()));
                rd1.setDevoteeEmail(Converters.replaceNull(dd1.getEmail()));
                rd.setReceiptDetails(rd1);
                
            }
            pst.close();
            con.close();

        } catch (SQLException e) {
            Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }

        return rd;

    }
    public ReceiptDetails getReceiptDetails(int receipt_id) {
        ReceiptDetails rd = new ReceiptDetails();
        try {
            con = dbCon.geConnection();
            pst = con.prepareStatement(ReceiptDetailsSql.SELECT_RECEIPT_DETAILS);
            pst.setInt(1, receipt_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                rd.setReceiptId(rs.getInt(1));
                rd.setDevoteeId(rs.getInt(2));
                rd.setServiceDate(rs.getDate(3));
                rd.setDonation(rs.getInt(4));
                rd.setService(rs.getString(5));
                rd.setPayment_type(rs.getString(6));
                rd.setPayment_det(rs.getString(7));

            }
            pst.close();
            con.close();

        } catch (SQLException e) {
            Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }

        return rd;

    }
    public int getLastReceiptID() {
        int lastReceiptID = -1;
        try {
            con = dbCon.geConnection();
            logger.info("stmodel.receipdetailscrud.getlastreceiptid()");
            pst = con.prepareStatement(ReceiptDetailsSql.RECEIPT_DETAILS_LAST_ID);
            rs = pst.executeQuery();
            while (rs.next()) {
                lastReceiptID = rs.getInt(1);
            }
            pst.close();
            con.close();

        } catch (SQLException e) {
            Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }
        return lastReceiptID;
    }

}
