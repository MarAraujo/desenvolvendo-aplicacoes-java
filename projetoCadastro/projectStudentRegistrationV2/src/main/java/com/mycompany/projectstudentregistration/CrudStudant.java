/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectstudentregistration;

/**
 *
 * @author marce
 */
import com.mycompany.projectstudentregistration.Conected;
import com.mycompany.projectstudentregistration.ConectionDB;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CrudStudant extends Conected {

    ResultSet rs;

    public CrudStudant() {

    }

    private static CrudStudant crud;

    public static CrudStudant getInstance() {
        if (crud == null) {
            crud = new CrudStudant();
        }
        return crud;
    }

    public int create(StudantData stdData) {
        int sucessfull = 1;
        Connection conn = ConectionDB.conector();
        PreparedStatement pst = null;
        String sql = "INSERT INTO `studant_registration`(`registration`, `name`, `email`, `age`) VALUES (?,?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, stdData.getRegistration());
            pst.setString(2, stdData.getName());
            pst.setString(3, stdData.getEmail());
            pst.setInt(4, stdData.getAge());

            int adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                System.out.println("Registrado com sucesso!");
                JOptionPane.showMessageDialog(null, "Registrado com Sucesso!");
                sucessfull = 0;
            }

        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar: " + ex.getMessage());
            }
        }
        return sucessfull;
    }

    public StudantData getData(int registration) {
        Connection conn = ConectionDB.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM studant_registration WHERE registration = ?";

        try {
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setInt(1, registration);

            rs = pst.executeQuery();

            if (rs.next()) {
                StudantData std = new StudantData();
                rs.first();
                std.setRegistration(registration);
                std.setName(rs.getString("name"));
                std.setEmail(rs.getString("email"));
                std.setAge(rs.getInt("age"));

                return std;
            } else {
                // Registro não encontrado
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            // Certifique-se de fechar o ResultSet, PreparedStatement e Connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar ResultSet: " + ex.getMessage());
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar PreparedStatement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar Connection: " + ex.getMessage());
                }
            }
        }
    }
}
