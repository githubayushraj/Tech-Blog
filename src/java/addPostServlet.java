//import com.tech.dao.PostDao;
//import com.tech.entite.Post;
//import com.tech.entite.Users;
//import com.tech.helper.Connector;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.http.HttpSession;
//
//@MultipartConfig
//public class addPostServlet extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            // Log the received data
//            System.out.println("Received post data");
//            
//            String categoryParam = request.getParameter("category");
//            System.out.println("Category Param: " + categoryParam);
//            
//            if (categoryParam == null || categoryParam.isEmpty()) {
//                out.println("Category ID is missing.");
//                return;
//            }
//            
//            int c_id = Integer.parseInt(categoryParam);
//            String p_title = request.getParameter("post_title");
//            String p_content = request.getParameter("content");
//            String p_code = request.getParameter("code");
//            Part part = request.getPart("image");
//            String p_imgname = part != null ? part.getSubmittedFileName() : null;
//
//            // Log received data
//            System.out.println("Category ID: " + c_id);
//            System.out.println("Post Title: " + p_title);
//            System.out.println("Post Content: " + p_content);
//            System.out.println("Post Code: " + p_code);
//            System.out.println("Post Image Name: " + p_imgname);
//
//            // Get user ID from session
//            HttpSession session = request.getSession();
//            Users user = (Users) session.getAttribute("currentuser");
//            if (user == null) {
//                out.println("User not logged in.");
//                return;
//            }
//            int user_id = user.getId();
//
//            // Log user ID
//            System.out.println("User ID: " + user_id);
//
//            // Create Post object
//            Post post = new Post(p_title, p_imgname, p_content, null, c_id, p_code, user_id);
//
//            // Save post to database
//            PostDao postDao = new PostDao(Connector.getConnection());
//            boolean success = postDao.savePost(post);
//
//            if (success) {
//                out.println("Successfully posted...");
//            } else {
//                out.println("Something went wrong");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.getWriter().println("Error: " + e.getMessage());
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        processRequest(request, response);
//    }
//}





//
//package com.tech.servlets;
//
//import com.tech.dao.PostDao;
//import com.tech.entite.Post;
//import com.tech.helper.Connector;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Timestamp;
//
//@WebServlet("/addPostServlet")
//@MultipartConfig
//public class addPostServlet extends HttpServlet {
//
//    private PostDao postDao;
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            // Initialize your database connection here
//            Connection conn = Connector.getConnection();
//            postDao = new PostDao(conn);
//        } catch (Exception e) {
//            throw new ServletException("Database connection error", e);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("post_title");
//        String content = request.getParameter("content");
//        String code = request.getParameter("code");
//        String categoryIdStr = request.getParameter("category");
//
//        // Handle file upload
//        Part filePart = request.getPart("image");
//        String pic = null;
//        if (filePart != null) {
//            pic = filePart.getSubmittedFileName();
//            // Save the file to a specific directory if needed
//            // For simplicity, just storing the file name here
//        }
//
//        if (title == null || categoryIdStr == null) {
//            response.getWriter().print("Missing required fields.");
//            return;
//        }
//
//        int categoryId;
//        try {
//            categoryId = Integer.parseInt(categoryIdStr);
//        } catch (NumberFormatException e) {
//            response.getWriter().print("Invalid category ID.");
//            return;
//        }
//
//        Post post = new Post(title, pic, content, new Timestamp(System.currentTimeMillis()), categoryId, code, 1); // Assuming userID = 1 for now
//        boolean isSaved = postDao.savePost(post);
//
//        if (isSaved) {
//            response.sendRedirect("success.jsp"); // Redirect to a success page
//        } else {
//            response.getWriter().print("Failed to add post.");
//        }
//    }
//
//
//}
