/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author PrasanthKumar
 */
public class Converters {
    final static Logger logger = Logger.getLogger(Converters.class.getName());
    
  public static String replaceNull(String input) {
  return input == null ? "" : input;
}
   public static String replaceNullDate(Date input) {
       if(input==null){
           return "";
       }else{
           return input+"";
       }
}
    public static String replaceNullTimestamp(Timestamp input) {
       if(input==null){
           return "";
       }else{
          return input+"";
       }
    }
    
}
