
package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/todo";
    private static final String USER = "root";
    private static final String PASSWORD = "Mohith@$2003";

    public static Connection getConn() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
