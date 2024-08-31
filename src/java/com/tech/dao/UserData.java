package com.tech.dao;

import com.tech.entite.Users;
import java.sql.*;

public class UserData {

    private Connection conn;

    public UserData(Connection conn) {
        this.conn = conn;
    }

    // Method to insert user into the database
    public boolean saveUser(Users user) {
        boolean isSuccess = false; // To track if operation was successful

        String query = "INSERT INTO users(full_name, email, password, contact_no, gender, profile) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getContactNo());
            pstmt.setString(5, user.getGender());
            pstmt.setString(6, user.getProfile());

            int rowEffect = pstmt.executeUpdate();
            if (rowEffect >= 1) {
                isSuccess = true; // Successful insertion
                System.out.println("Registered successfully...");
            } else {
                System.out.println("Error occurred...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess; // Return the status of the operation
    }

    // Get user by email and password from database
    public Users getUserEmailAndPassword(String email, String password) {
        Users user = null;

        try {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Users();

                // Data from DB
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setContactNo(rs.getString("contact_no"));
                user.setGender(rs.getString("gender"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                user.setProfile(rs.getString("profile"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Update user information in the database
    public boolean updateUser(Users user) {
        boolean flag = false;
        try {
            String query = "UPDATE users SET full_name=?, password=?, contact_no=?, profile=? WHERE user_id=? AND email=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getContactNo());
            pstmt.setString(4, user.getProfile());
            pstmt.setInt(5, user.getId());
            pstmt.setString(6, user.getEmail());

            int roweffect = pstmt.executeUpdate();
            if (roweffect > 0) {
                System.out.println("Update successfully....");
                flag = true;
            } else {
                System.out.println("Update failed....!!!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
