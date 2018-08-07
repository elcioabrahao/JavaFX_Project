/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

/**
 *
 * @author PrasanthKumar
 */
public class DevoteeDetailsSql {
    public static String SELECT_DEVOTEE_DETAILS="SELECT "
            + "DEVOTEE_ID , FIRST_NAME , LAST_NAME ,"
            + " PHONE , EMAIL , ADDRESS , CITY , STATE_DET , COUNTRY_DET , ZIP ,"
            + " DATE_CREATED , DATE_MODIFIED  FROM DEVOTEE_DETAILS WHERE DEVOTEE_ID=?";
    /*
    public static String SELECT_PARTICULAR_DEVOTEEID="SELECT "
            + "DEVOTEE_ID "           
            + " FROM DEVOTEE_DETAILS WHERE ((FIRST_NAME=? AND PHONE=? AND PHONE IS NOT NULL) OR (FIRST_NAME=? AND EMAIL=? AND EMAIL IS NOT NULL))";
    */
    /*
    public static String SELECT_PARTICULAR_DEVOTEEID_BASEDONPHONEEMAIL="SELECT "
            + "DEVOTEE_ID "           
            + " FROM DEVOTEE_DETAILS WHERE ((PHONE=? AND PHONE IS NOT NULL) AND (EMAIL=? AND EMAIL IS NOT NULL))";
   */ 
    public static String SELECT_PARTICULAR_DEVOTEEID_BASEDONPHONE="SELECT "
            + "DEVOTEE_ID "           
            + " FROM DEVOTEE_DETAILS WHERE (PHONE=? AND PHONE IS NOT NULL)";
    public static String SELECT_PARTICULAR_DEVOTEEID_BASEDONEMAIL="SELECT "
            + "DEVOTEE_ID "           
            + " FROM DEVOTEE_DETAILS WHERE (EMAIL=? AND EMAIL IS NOT NULL)";
    public static String INSERT_DEVOTEE_DETAILS="INSERT INTO DEVOTEE_DETAILS VALUE (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
    public static String UPDATE_DEVOTEE_DETAILS = "UPDATE DEVOTEE_DETAILS SET FIRST_NAME=?, LAST_NAME=?, "
            + " PHONE = ? , EMAIL = ? , ADDRESS = ? , CITY  = ?, STATE_DET = ? , COUNTRY_DET = ? , ZIP  = ?, "
            + "DATE_MODIFIED = ? WHERE DEVOTEE_ID=?";
    public static String DEVOTEE_DETAILS_LAST_ID = "SELECT MAX(DEVOTEE_ID) FROM DEVOTEE_DETAILS";
    public static String SELECT_RECENT_50_DEVOTEES = "SELECT "
            + "DEVOTEE_ID , FIRST_NAME , LAST_NAME ,"
            + " PHONE , EMAIL , ADDRESS , CITY , STATE_DET , COUNTRY_DET , ZIP "
            + " FROM DEVOTEE_DETAILS "
            + "WHERE DEVOTEE_ID IN (SELECT DISTINCT DEVOTEE_ID FROM RECEIPT_DETAILS ORDER BY DATE_CREATED DESC ) ORDER BY FIRST_NAME ASC LIMIT 50";


public static String SEARCH_BY_DEV_NAME = "SELECT "
            + "DEVOTEE_ID , FIRST_NAME , LAST_NAME ,"
            + " PHONE , EMAIL , ADDRESS , CITY , STATE_DET , COUNTRY_DET , ZIP "
            + " FROM DEVOTEE_DETAILS "
            + "WHERE FIRST_NAME LIKE ? OR LAST_NAME LIKE ? OR CONCAT_WS('', FIRST_NAME, LAST_NAME) LIKE ? ";
public static String SEARCH_BY_DEV_PHONE = "SELECT "
            + "DEVOTEE_ID , FIRST_NAME , LAST_NAME ,"
            + " PHONE , EMAIL , ADDRESS , CITY , STATE_DET , COUNTRY_DET , ZIP "
            + " FROM DEVOTEE_DETAILS "
            + "WHERE PHONE LIKE ? ";
public static String SEARCH_BY_DEV_EMAIL = "SELECT "
            + "DEVOTEE_ID , FIRST_NAME , LAST_NAME ,"
            + " PHONE , EMAIL , ADDRESS , CITY , STATE_DET , COUNTRY_DET , ZIP "
            + " FROM DEVOTEE_DETAILS "
            + "WHERE EMAIL LIKE ? ";

}
