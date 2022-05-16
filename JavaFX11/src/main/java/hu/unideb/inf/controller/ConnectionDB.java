package hu.unideb.inf.controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private String url = "jdbc:h2:~/db_jpa_fxml";
    private String user = "sa";
    private String password = "";

    public Connection fileconnection()  {
        try {
            Class.forName("org.h2.Driver");
            Connection connectdata = DriverManager.getConnection(url,user,password);
            return connectdata;
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
            return null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
            return null;
        }
    }
}
