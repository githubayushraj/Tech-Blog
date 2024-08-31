package com.tech.servlets;

import com.tech.dao.PostDao;
import com.tech.entite.Post;
import com.tech.entite.Users;
import com.tech.helper.Connector;
import java.io.File;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
public class AddPostServlet extends HttpServlet {
    private final String UPLOAD_DIR = "C:/Users/lenovo/OneDrive/Documents/NetBeansProjects/TechBlog/web/blog_pics";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetching form data
            String categoryParam = request.getParameter("category");
            int c_id = Integer.parseInt(categoryParam);
            String p_title = request.getParameter("post_title");
            String p_content = request.getParameter("content");
            String p_code = request.getParameter("code");
            Part part = request.getPart("image");
            String p_imgname = part != null ? part.getSubmittedFileName() : null;

            // Save image in blog_pics folder
            if (p_imgname != null && !p_imgname.isEmpty()) {
                String filePath = UPLOAD_DIR + File.separator + p_imgname;
                part.write(filePath);
            }

            // Getting the current user from session
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("currentuser");

            if (user == null) {
                out.println("User not logged in");
                return;
            }

            int user_id = user.getId();

            // Create Post object
            Post post = new Post(p_title, p_imgname, p_content, null, c_id, p_code, user_id);

            // Save the post to the database
            PostDao postDao = new PostDao(Connector.getConnection());
            boolean success = postDao.savePost(post);

            if (success) {
                out.println("Post successfully created");
            } else {
                out.println("Something went wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        processRequest(request, response);
    }
}
