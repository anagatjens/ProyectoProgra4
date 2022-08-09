/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ana Gatjens Campos
 */
public class Conexion {
    Connection con;
        public Conexion(){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HotelOmega;integratedSecurity=true;encrypt=false;trustServerCertificate=false;");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error"+e);
            }
        }

        public Connection getConection(){
            return con;
        }
    
}
