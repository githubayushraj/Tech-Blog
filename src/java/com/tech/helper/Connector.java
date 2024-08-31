package com.tech.helper;

import java.sql.*;
public class Connector {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tech_blog", "root", "Ayush@123#");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}

