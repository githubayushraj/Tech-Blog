package com.tech.dao;

import com.tech.entite.Categories;
import com.tech.entite.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    private Connection conn;

    public PostDao(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<Categories> getAllCategories() {
        ArrayList<Categories> list = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("c_id");
                String c_name = rs.getString("c_name");
                String description = rs.getString("description");
                Categories c = new Categories(id, c_name, description);
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean savePost(Post post) {
        boolean flag = false;
        String query = "INSERT INTO post(p_title, p_pic, p_content, p_code, c_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getPic());
            pstmt.setString(3, post.getContent());
            pstmt.setString(4, post.getCode());
            pstmt.setInt(5, post.getCid());
            pstmt.setInt(6, post.getUserId());

            int rowsAffected = pstmt.executeUpdate();
            flag = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag;
    }

//    get all post
    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from post order by p_id desc");
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("p_id");
                String ptittle = set.getString("p_title");
                String ppic = set.getString("p_pic");
                String pcontent = set.getString("p_content");
                Timestamp pdate = set.getTimestamp("p_date");
                int cid = set.getInt("c_id");
                String pcode = set.getString("p_code");
                int userid = set.getInt("user_id");

                //create post object
                Post post = new Post(pid, ptittle, ppic, pcontent, pdate, cid, pcode, userid);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //get all post by categories id
    public List<Post> getAllPostByCatId(int catId) {

        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from post where c_id=?");
            pstmt.setInt(1, catId);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("p_id");
                String ptittle = set.getString("p_title");
                String ppic = set.getString("p_pic");
                String pcontent = set.getString("p_content");
                Timestamp pdate = set.getTimestamp("p_date");
                String pcode = set.getString("p_code");
                int userid = set.getInt("user_id");

                //create post object by using categories id
                Post post = new Post(pid, ptittle, ppic, pcontent, pdate, pcode, userid);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //get all post detail by post id
    //get all post by post id
    public Post getAllPostByPostId(int postId) {
        String query = "SELECT * FROM post WHERE p_id = ?";
        Post p = null;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set the postId parameter
            pstmt.setInt(1, postId);

            // Execute the query
            try (ResultSet set = pstmt.executeQuery()) {
                // Process the result set
                if (set.next()) { // Use if instead of while since we're fetching one post by ID
                    int pid = set.getInt("p_id");
                    String pTitle = set.getString("p_title");
                    String pPic = set.getString("p_pic");
                    String pContent = set.getString("p_content");
                    Timestamp pDate = set.getTimestamp("p_date");
                    String pCode = set.getString("p_code");
                    int cId = set.getInt("c_id");
                    int userId = set.getInt("user_id");

                    // Create Post object
                    p = new Post(pid, pTitle, pPic, pContent, pDate, cId, pCode, userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions appropriately
        }

        return p; // Return the Post object
    }

    public String getUserNameByPid(int post_id) {
        String query = "select * from post WHERE p_id=?";
        int user_Id = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set the postId parameter
            pstmt.setInt(1, post_id);

            // Execute the query
            try (ResultSet set = pstmt.executeQuery()) {
                // Process the result set
                if (set.next()) { // Use if instead of while since we're fetching one post by ID
                    user_Id = set.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions appropriately
        }
        return getName(user_Id);
    }

    public String getName(int user_id) {
        String query = "select * from users WHERE user_id=?";
        String user_name = "";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set the postId parameter
            pstmt.setInt(1, user_id);

            // Execute the query
            try (ResultSet set = pstmt.executeQuery()) {
                // Process the result set
                if (set.next()) { // Use if instead of while since we're fetching one post by ID
                    user_name = set.getString("full_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions appropriately
        }

        return user_name;
    }

    // fetch all post from user id
    public List<Post> getAllPostByUserId(int userid) {

        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from post where c_id=?");
            pstmt.setInt(1, userid);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("p_id");
                String ptittle = set.getString("p_title");
                String ppic = set.getString("p_pic");
                String pcontent = set.getString("p_content");
                Timestamp pdate = set.getTimestamp("p_date");
                String pcode = set.getString("p_code");
                int user_id = set.getInt("user_id");

                //create post object by using categories id
                Post post = new Post(pid, ptittle, ppic, pcontent, pdate, pcode, user_id);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Post> getAllPostByCatIdUserId(int userId) {
        List<Post> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM post WHERE user_id=?";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("p_id");
                String ptittle = set.getString("p_title");
                String ppic = set.getString("p_pic");
                String pcontent = set.getString("p_content");
                Timestamp pdate = set.getTimestamp("p_date");
                String pcode = set.getString("p_code");
                int user_id = set.getInt("user_id");

                //create post object by using categories id
                Post post = new Post(pid, ptittle, ppic, pcontent, pdate, pcode, user_id);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Post> getUserPostByUserIdCatId(int userId, int catId) {

        List<Post> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("select * from post where user_id=? AND c_id=?");
            pstmt.setInt(1, userId);
            pstmt.setInt(2, catId);
            ResultSet set = pstmt.executeQuery();
            while (set.next()) {
                int pid = set.getInt("p_id");
                String ptittle = set.getString("p_title");
                String ppic = set.getString("p_pic");
                String pcontent = set.getString("p_content");
                Timestamp pdate = set.getTimestamp("p_date");
                String pcode = set.getString("p_code");
                int userid = set.getInt("user_id");

                //create post object by using categories id
                Post post = new Post(pid, ptittle, ppic, pcontent, pdate, pcode, userid);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
