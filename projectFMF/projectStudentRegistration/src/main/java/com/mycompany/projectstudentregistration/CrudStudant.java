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
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CrudStudant extends Conected {

    public CrudStudant() {

    }

    private static CrudStudant crud;

    public static CrudStudant getInstance() {
        if (crud == null) {
            crud = new CrudStudant();
        }
        return crud;
    }

    public void create(StudantData stdData) {
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
    }
}

