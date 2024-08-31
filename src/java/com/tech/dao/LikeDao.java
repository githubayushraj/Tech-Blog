package com.tech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeDao {
    private Connection conn;

    public LikeDao(Connection conn) {
        this.conn = conn;
    }

    public boolean insertLike(int p_id, int u_id) {
        boolean flag = false;
        String q = "INSERT INTO likes (p_id, u_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(q)) {
            pstmt.setInt(1, p_id);
            pstmt.setInt(2, u_id);
            pstmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }
        return flag;
    }

    public int countLike(int p_id) {
        int count = 0;
        String q = "SELECT COUNT(*) AS like_count FROM likes WHERE p_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(q)) {
            pstmt.setInt(1, p_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("like_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }
        return count;
    }

    public boolean isLikedByUser(int p_id, int u_id) {
        boolean flag = false;
        String q = "SELECT * FROM likes WHERE p_id = ? AND u_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(q)) {
            pstmt.setInt(1, p_id);
            pstmt.setInt(2, u_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }
        return flag;
    }

    public boolean deleteLike(int p_id, int u_id) {
        boolean flag = false;
        String q = "DELETE FROM likes WHERE p_id = ? AND u_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(q)) {
            pstmt.setInt(1, p_id);
            pstmt.setInt(2, u_id);
            pstmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this instead
        }
        return flag;
    }
}
