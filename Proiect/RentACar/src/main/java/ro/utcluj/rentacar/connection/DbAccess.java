/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.rentacar.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Martin
 */
public class DbAccess {
   private Connection connection;
   private static DbAccess instance; 
    private DbAccess() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        this.connection = DriverManager.getConnection("jdbc:derby://localhost/rent-a-car;create=false","app","app");
    }
    
    public static final DbAccess getInstance() throws ClassNotFoundException, SQLException{
        if(instance == null){
            instance = new DbAccess();
        }
    return instance;        
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
}
