/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marce
 */
public class DataSource {
    
    private String hostname;
    private int port;
    private String database;
    private String username;
    private String password;
    
    private Connection connection;
    
    public DataSource(){
        try{
            hostname = "localhost";
            port = 3306;
            database = "datatableregistration";
            username = "root";
            password = "";
            String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, username, password);
            
            System.out.println("Conectado...");
        }
        catch(SQLException ex){
            System.err.println("Erro na Conexao" + ex.getMessage());
        }
        catch(Exception ex){
            System.err.println("Erro geral" + ex.getMessage());
        }
    }
    public Connection getConnection(){
        return this.connection;
    }
    public void closeDataSource(){
        try{
            connection.close();
        }
        catch(Exception ex){
            System.err.println("Erro desconectar" + ex.getMessage());
        }
    }
}
