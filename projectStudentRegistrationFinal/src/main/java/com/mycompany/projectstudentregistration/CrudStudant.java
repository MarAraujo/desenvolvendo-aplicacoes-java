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
import java.util.ArrayList;
import java.util.List;
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

            int create = pst.executeUpdate();

            if (create > 0) {
                System.out.println("Registrado com sucesso!");
                JOptionPane.showMessageDialog(null, "Registrado com Sucesso!");
                sucessfull = 0;
            }

        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Aluno já cadastrado!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
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

    public int editData(StudantData stdData) {
        int sucessefull = 1;
        Connection conn = ConectionDB.conector();
        PreparedStatement pst = null;

        String sql = "UPDATE studant_registration SET name = ?, email = ?, age = ? WHERE registration = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, stdData.getName());
            pst.setString(2, stdData.getEmail());
            pst.setInt(3, stdData.getAge());
            pst.setInt(4, stdData.getRegistration()); // Define value by pass for method WHERE do sql

            int change = pst.executeUpdate();

            if (change > 0) {
                System.out.println("Atualizado com Sucesso!");
                JOptionPane.showMessageDialog(null, "Atualizado com Sucesse!");
                sucessefull = 0;
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

        return sucessefull;

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

    public StudantData getDataForName(String name) {
        Connection conn = ConectionDB.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM studant_registration WHERE name LIKE ?";

        try {
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pst.setString(1,"%" + name + "%");
            rs = pst.executeQuery();

            if (rs.next()) {
                StudantData std = new StudantData();
                std.setName(name);
                std.setRegistration(rs.getInt("registration"));
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

    public int deleteDataStudantBank(int registration) {
        int statusDeleteStudant = 1;
        Connection conn = ConectionDB.conector();
        PreparedStatement pst = null;
        String sql = "DELETE FROM studant_registration WHERE registration = ?";

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, registration);
            int statusdelete = pst.executeUpdate();
            if (statusdelete > 0) {
                JOptionPane.showMessageDialog(null, "Deletado com Sucesso!");
                statusDeleteStudant = 0;
            }

        } catch (Exception ex) {
            System.out.println("Erro ao deletar os dados do banco" + ex.getMessage());
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
        return statusDeleteStudant;
    }

    public List<StudantData> getAllStudant() {
        Connection conn = ConectionDB.conector();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM studant_registration";
        List<StudantData> listAllStudant = new ArrayList<>();

        try {
            pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pst.executeQuery();
            

            while (rs.next()) {
                StudantData stdData = new StudantData();
                stdData.setRegistration(rs.getInt("registration"));
                stdData.setName(rs.getString("name"));
                stdData.setEmail(rs.getString("email"));
                stdData.setAge(rs.getInt("age"));
                listAllStudant.add(stdData);
            }
            

        } catch (Exception ex) {
            System.out.println("Erro ao deletar os dados do banco" + ex.getMessage());
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
        return listAllStudant;
    }
}
