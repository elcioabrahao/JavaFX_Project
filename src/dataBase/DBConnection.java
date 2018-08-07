/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import stmodel.AppConfig;

/**
 *
 * @author
 */
public class DBConnection {

    final static Logger logger = Logger.getLogger(DBConnection.class.getName());
    public Connection con;
    // String url ="jdbc:mysql://localhost:3306/";
    String url = AppConfig.getProperties().getProperty("dburl");

    String user = AppConfig.getProperties().getProperty("dbuser");
    String pass = AppConfig.getProperties().getProperty("dbpwd");
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";

    public Connection mkDataBase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.FATAL, null, ex);

        }
        return con;
    }

    public Connection geConnection() {
        //System.out.println("url"+url);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url + AppConfig.getProperties().getProperty("database") + unicode, user, pass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.FATAL, null, ex);
            logger.info("Too Many Connection");
        }

        return con;
    }
}
