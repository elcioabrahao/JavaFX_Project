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
public class ReceiptDetailsSql {
    public static String SELECT_RECEIPT_DETAILS = 
            " SELECT RECEIPT_ID , DEVOTEE_ID ,"
            + " SERVICE_DATE , DONATION  , SERVICE  ,"
            + " PAYMENT_TYPE, PAYMENT_DET, "
            + "DATE_CREATED  , DATE_MODIFIED "
            + "FROM RECEIPT_DETAILS WHERE RECEIPT_ID=?" ;
    public static String SELECT_lAST100_RECEIPT_DETAILS = 
            " SELECT RECEIPT_ID , DEVOTEE_ID ,"
            + " SERVICE_DATE , DONATION  , SERVICE  ,"
            + " PAYMENT_TYPE, PAYMENT_DET, "
            + "DATE_CREATED  , DATE_MODIFIED "
            + "FROM RECEIPT_DETAILS ORDER BY RECEIPT_ID DESC LIMIT 100" ;
    
    //public static String INSERT_RECEIPT_DETAILS = "INSERT INTO RECEIPT_DETAILS VALUE (   ?, ? , ? , ?  , ?  , ? , ? , ? , ? , ? )";
    public static String INSERT_RECEIPT_DETAILS = "INSERT INTO RECEIPT_DETAILS VALUE (? , ? , ?  , ?  , ? , ? , ? , ? , ? )";
    public static String RECEIPT_DETAILS_LAST_ID = "SELECT MAX(RECEIPT_ID) FROM RECEIPT_DETAILS";
    
    
}
