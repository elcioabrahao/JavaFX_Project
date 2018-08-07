/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author PrasanthKumar
 */
public class LoggerMain {
    
    public static void initialize(){
        try{
        org.apache.log4j.PropertyConfigurator.configure(LoggerMain.class.getClassLoader().getResourceAsStream("resources/log4j.properties"));
        }catch(Exception e){
            Logger.getLogger(LoggerMain.class.getName()).log(Level.FATAL, null, e);
        }
        
    }
    
}
