/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.web.arproject.uas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mea
 */
public class Koneksi {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String database = "jdbc:mysql://localhost:3306/project_uas";
    private static final String user = "root";
    private static final String password = "";
    
    private Connection connection;
    private String errorMessage;

    public String getErorMessage() {
        return errorMessage;
    }
    
    public Connection getConnection(){
        connection = null;        
        errorMessage = "";
        
        try{ 
            Class.forName(driver); 
        } catch (ClassNotFoundException ex){ 
            errorMessage = "JDBC Driver tidak di temukan atau rusak\n"+ex;
        } 
        
        if (errorMessage.equals("")){ 
            try { 
                connection = DriverManager.getConnection(database+"?user="+user+"&password="+password+""); 
            } catch (SQLException ex) { 
                errorMessage = "Koneksi ke "+database+" gagal\n"+ex;
            }
        }
        
        return connection;
    }
}
