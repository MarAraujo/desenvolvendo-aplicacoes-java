/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectstudentregistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * author marcelo
 */
public class ConectionDB {
    
    static String USER = "root";
    static String PASSWORD = "";

    public static Connection conector() {
        String message = "<html>Banco de dados <font color=#ff0000> offline </font></html>";
        java.sql.Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/datatableregistration";
        String user = USER;
        String password = PASSWORD;
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, message, "Atenção", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
}