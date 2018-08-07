/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stmodel;

/**
 *
 * @author PrasanthKumar
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class AppConfig {

    final static Logger logger = Logger.getLogger(AppConfig.class.getName());
    public static Properties prop = new Properties();

    //public static void configureProps(){
    public static void initialize() {

        InputStream input = null;

        try {

            String filename = "resources/config.properties";
            input = AppConfig.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                logger.info("Sorry, unable to find properties file - " + filename);
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.FATAL, null, ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    Logger.getLogger(AppConfig.class.getName()).log(Level.FATAL, null, e);
                }
            }
        }

    }
    public static Properties getProperties(){
        return prop;
    }
}
