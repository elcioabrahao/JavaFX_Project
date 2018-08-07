/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

import bean.DevoteeDetails;
import dataBase.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import sql.DevoteeDetailsSql;

/**
 *
 * @author PrasanthKumar
 */
public class DevoteeDetailsCRUD {

    final static Logger logger = Logger.getLogger(DevoteeDetailsCRUD.class.getName());

    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    public int checkDevExists(DevoteeDetails dd) {

        logger.info("INSIDE CHECK IF DEVOTEE EXISTS METHOD");
        int devotee_id = -1;
        try {
            con = dbCon.geConnection();
            /*
            pst = con.prepareStatement(DevoteeDetailsSql.SELECT_PARTICULAR_DEVOTEEID);
            pst.setString(1, dd.getFirstName());
            pst.setString(2, dd.getPhone());
            pst.setString(3, dd.getFirstName());
            pst.setString(4, dd.getEmail());
             */
 
            /*
            if((!(dd.getPhone().equals(""))&&(dd.getPhone()!=null)) && (!(dd.getEmail().equals(""))&&(dd.getEmail()!=null))){
                pst = con.prepareStatement(DevoteeDetailsSql.SELECT_PARTICULAR_DEVOTEEID_BASEDONPHONEEMAIL);
                pst.setString(1, dd.getPhone());            
                pst.setString(1, dd.getEmail());
                rs = pst.executeQuery();
                while (rs.next()) {
                    devotee_id = rs.getInt(1);
                }
                pst.close();
            }*/
            /* there cannot be more than one phone number entry */
            /*
            
            95 - a
            95 - b
            
            overwrite with 95 b - only one entry will be created.
            ""-no number - no entry updated
            
            a - 70
            a - 95
            
            overwrite with 95 - a
            */
            if ((!(dd.getPhone().equals("")) && (dd.getPhone() != null)) && (devotee_id==-1)){
                pst = con.prepareStatement(DevoteeDetailsSql.SELECT_PARTICULAR_DEVOTEEID_BASEDONPHONE);
                pst.setString(1, dd.getPhone());
                rs = pst.executeQuery();
                while (rs.next()) {
                    devotee_id = rs.getInt(1);
                }
                pst.close();
            }
            if ((!(dd.getEmail().equals("")) && (dd.getEmail() != null)) && (devotee_id==-1)) {
                pst = con.prepareStatement(DevoteeDetailsSql.SELECT_PARTICULAR_DEVOTEEID_BASEDONEMAIL);
                pst.setString(1, dd.getEmail());
                rs = pst.executeQuery();
                while (rs.next()) {
                    devotee_id = rs.getInt(1);
                }
                pst.close();
            }
            con.close();

        } catch (SQLException e) {

            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }

        return devotee_id;
    }

    public DevoteeDetails saveDevoteeDet(DevoteeDetails dd) {
        boolean success = false;
        int dev_id = checkDevExists(dd);
        if (dev_id == -1) {
            logger.info("stmodel.DevoteeDetailsCRUD.saveDevoteeDet() --- inserting the devotee details " + dev_id);

            if ((dd.getDevoteeId() == 0) || (dd.getDevoteeId() == -1)) {
                dd.setDevoteeId(this.getLastDevoteeID() + 1);
            }

            try {
                con = dbCon.geConnection();
                pst = con.prepareStatement(DevoteeDetailsSql.INSERT_DEVOTEE_DETAILS);
                pst.setInt(1, dd.getDevoteeId());
                pst.setString(2, dd.getFirstName());
                pst.setString(3, dd.getLastName());
                pst.setString(4, dd.getPhone());
                pst.setString(5, dd.getEmail());
                pst.setString(6, dd.getAddress());
                pst.setString(7, dd.getCity());
                pst.setString(8, dd.getStateDet());
                pst.setString(9, dd.getCountryDet());
                pst.setString(10, dd.getZip());
                pst.setTimestamp(11, (Timestamp) dd.getDateCreated());
                pst.setTimestamp(12, (Timestamp) dd.getDateModified());

                pst.executeUpdate();
                pst.close();
                con.close();
                success = true;
                dd.setSuccessSave(success);

            } catch (SQLException ex) {
                Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, ex);
            }

        } else {
            logger.info("stmodel.DevoteeDetailsCRUD.saveDevoteeDet() --- updating the devotee details " + dev_id);
            dd.setDevoteeId(dev_id);
            dd = updateDevoteeDet(dd);
        }
        return dd;
    }

    public DevoteeDetails updateDevoteeDet(DevoteeDetails dd) {
        boolean success = false;
        logger.info("INSIDE updateDevoteeDet");
        try {
            con = dbCon.geConnection();
            pst = con.prepareStatement(DevoteeDetailsSql.UPDATE_DEVOTEE_DETAILS);
            pst.setInt(11, dd.getDevoteeId());
            pst.setString(1, dd.getFirstName());
            pst.setString(2, dd.getLastName());
            pst.setString(3, dd.getPhone());
            pst.setString(4, dd.getEmail());
            pst.setString(5, dd.getAddress());
            pst.setString(6, dd.getCity());
            pst.setString(7, dd.getStateDet());
            pst.setString(8, dd.getCountryDet());
            pst.setString(9, dd.getZip());
            pst.setTimestamp(10, (Timestamp) dd.getDateModified());

            pst.executeUpdate();
            pst.close();
            con.close();
            success = true;
            dd.setSuccessSave(success);

        } catch (SQLException ex) {
            Logger.getLogger(ReceiptDetailsCRUD.class.getName()).log(Level.FATAL, null, ex);
        }
        return dd;
    }

    public DevoteeDetails getDevoteeDetails(int devotee_id) {
        logger.info("INSIDE getDevoteeDetails");
        DevoteeDetails dd = new DevoteeDetails();
        try {
            con = dbCon.geConnection();
            pst = con.prepareStatement(DevoteeDetailsSql.SELECT_DEVOTEE_DETAILS);
            pst.setInt(1, devotee_id);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd.setFirstName(rs.getString(2));
                dd.setLastName(rs.getString(3));
                dd.setPhone(rs.getString(4));
                dd.setEmail(rs.getString(5));
                dd.setAddress(rs.getString(6));
                dd.setCity(rs.getString(7));
                dd.setStateDet(rs.getString(8));
                dd.setCountryDet(rs.getString(9));
                dd.setZip(rs.getString(10));
            }
            pst.close();
            con.close();

        } catch (SQLException e) {
            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }

        return dd;

    }

    public int getLastDevoteeID() {
        logger.info("INSIDE getLastDevoteeID");
        int lastDevoteeID = -1;
        try {
            con = dbCon.geConnection();
            logger.info("stmodel.DevoteeDetailsCRUD.getLastDevoteeID()");
            pst = con.prepareStatement(DevoteeDetailsSql.DEVOTEE_DETAILS_LAST_ID);
            rs = pst.executeQuery();
            while (rs.next()) {
                lastDevoteeID = rs.getInt(1);
            }
            pst.close();
            con.close();

        } catch (SQLException e) {
            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }
        return lastDevoteeID;
    }

    public DevoteeDetails getAllDevoteeDetails() {
        logger.info("INSIDE getAllDevoteeDetails");
        DevoteeDetails dd = new DevoteeDetails();

        //DevoteeDetails dd = new DevoteeDetails();
        try {
            con = dbCon.geConnection();
            pst = con.prepareStatement(DevoteeDetailsSql.SELECT_RECENT_50_DEVOTEES);
            rs = pst.executeQuery();
            while (rs.next()) {
                DevoteeDetails dd1 = new DevoteeDetails();
                dd1.setDevoteeId(rs.getInt(1));
                dd1.setFirstName(rs.getString(2));
                dd1.setLastName(rs.getString(3));
                dd1.setPhone(rs.getString(4));
                dd1.setEmail(rs.getString(5));
                dd1.setAddress(rs.getString(6));
                dd1.setCity(rs.getString(7));
                dd1.setStateDet(rs.getString(8));
                dd1.setCountryDet(rs.getString(9));
                dd1.setZip(rs.getString(10));
                dd1.setFullName(Converters.replaceNull(dd1.getFirstName()), Converters.replaceNull(dd1.getLastName()));
                dd1.setAddrComplete(Converters.replaceNull(dd1.getAddress()), Converters.replaceNull(dd1.getCity()), Converters.replaceNull(dd1.getStateDet()), Converters.replaceNull(dd1.getCountryDet()), Converters.replaceNull(dd1.getZip()));
                dd.setDevoteeDetails(dd1);
            }
            pst.close();
            con.close();
            logger.info("EXITING getAllDevoteeDetails");
        } catch (SQLException e) {
            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, e);
        }

        return dd;

    }

    public DevoteeDetails searchViewByName(String name, DevoteeDetails dd) {
        logger.info("INSIDE searchViewByName");
        con = dbCon.geConnection();
        dd.getDevoteeDetails().clear();
        name = name.replaceAll("\\s", "%");
        try {
            pst = con.prepareStatement(DevoteeDetailsSql.SEARCH_BY_DEV_NAME);
            pst.setString(1, "%" + name + "%");
            pst.setString(2, "%" + name + "%");
            pst.setString(3, "%" + name + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                DevoteeDetails dd1 = new DevoteeDetails();
                dd1.setDevoteeId(rs.getInt(1));
                dd1.setFirstName(rs.getString(2));
                dd1.setLastName(rs.getString(3));
                dd1.setPhone(rs.getString(4));
                dd1.setEmail(rs.getString(5));
                dd1.setAddress(rs.getString(6));
                dd1.setCity(rs.getString(7));
                dd1.setStateDet(rs.getString(8));
                dd1.setCountryDet(rs.getString(9));
                dd1.setZip(rs.getString(10));
                dd1.setFullName(Converters.replaceNull(dd1.getFirstName()), Converters.replaceNull(dd1.getLastName()));
                dd1.setAddrComplete(Converters.replaceNull(dd1.getAddress()), Converters.replaceNull(dd1.getCity()), Converters.replaceNull(dd1.getStateDet()), Converters.replaceNull(dd1.getCountryDet()), Converters.replaceNull(dd1.getZip()));
                dd.setDevoteeDetails(dd1);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, ex);
        }
        logger.info("EXITING searchViewByName");
        return dd;
    }

    public DevoteeDetails searchViewByPhone(String phone, DevoteeDetails dd) {
        logger.info("INSIDE searchViewByPhone");
        con = dbCon.geConnection();
        dd.getDevoteeDetails().clear();
        phone = phone.replaceAll("\\s", "%");
        try {
            pst = con.prepareStatement(DevoteeDetailsSql.SEARCH_BY_DEV_PHONE);
            pst.setString(1, "%" + phone + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                DevoteeDetails dd1 = new DevoteeDetails();
                dd1.setDevoteeId(rs.getInt(1));
                dd1.setFirstName(rs.getString(2));
                dd1.setLastName(rs.getString(3));
                dd1.setPhone(rs.getString(4));
                dd1.setEmail(rs.getString(5));
                dd1.setAddress(rs.getString(6));
                dd1.setCity(rs.getString(7));
                dd1.setStateDet(rs.getString(8));
                dd1.setCountryDet(rs.getString(9));
                dd1.setZip(rs.getString(10));
                dd1.setFullName(Converters.replaceNull(dd1.getFirstName()), Converters.replaceNull(dd1.getLastName()));
                dd1.setAddrComplete(Converters.replaceNull(dd1.getAddress()), Converters.replaceNull(dd1.getCity()), Converters.replaceNull(dd1.getStateDet()), Converters.replaceNull(dd1.getCountryDet()), Converters.replaceNull(dd1.getZip()));
                dd.setDevoteeDetails(dd1);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, ex);
        }
        logger.info("EXITING searchViewByPhone");
        return dd;
    }

    public DevoteeDetails searchViewByEmail(String email, DevoteeDetails dd) {
        logger.info("INSIDE searchViewByEmail");
        con = dbCon.geConnection();
        dd.getDevoteeDetails().clear();
        email = email.replaceAll("\\s", "%");
        try {
            pst = con.prepareStatement(DevoteeDetailsSql.SEARCH_BY_DEV_EMAIL);
            pst.setString(1, "%" + email + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                DevoteeDetails dd1 = new DevoteeDetails();
                dd1.setDevoteeId(rs.getInt(1));
                dd1.setFirstName(rs.getString(2));
                dd1.setLastName(rs.getString(3));
                dd1.setPhone(rs.getString(4));
                dd1.setEmail(rs.getString(5));
                dd1.setAddress(rs.getString(6));
                dd1.setCity(rs.getString(7));
                dd1.setStateDet(rs.getString(8));
                dd1.setCountryDet(rs.getString(9));
                dd1.setZip(rs.getString(10));
                dd1.setFullName(Converters.replaceNull(dd1.getFirstName()), Converters.replaceNull(dd1.getLastName()));
                dd1.setAddrComplete(Converters.replaceNull(dd1.getAddress()), Converters.replaceNull(dd1.getCity()), Converters.replaceNull(dd1.getStateDet()), Converters.replaceNull(dd1.getCountryDet()), Converters.replaceNull(dd1.getZip()));
                dd.setDevoteeDetails(dd1);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DevoteeDetailsCRUD.class.getName()).log(Level.FATAL, null, ex);
        }
        logger.info("EXITING searchViewByEmail");
        return dd;
    }
}
