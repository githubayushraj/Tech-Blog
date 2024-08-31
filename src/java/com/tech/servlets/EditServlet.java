package com.tech.servlets;

import com.tech.dao.UserData;
import com.tech.entite.Message;
import com.tech.entite.Users;
import com.tech.helper.Connector;
import com.tech.helper.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class EditServlet extends HttpServlet {

    private final String UPLOAD_DIR = "C:/Users/lenovo/OneDrive/Documents/NetBeansProjects/TechBlog/web/profilepics";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetch all data from profile page at time of editable
            String name = request.getParameter("user_name");
            String mob = request.getParameter("user_mob_no");
            String password = request.getParameter("user_password");
            Part part = request.getPart("image");
            String imgname = part.getSubmittedFileName();

            // Get user from session
            HttpSession s = request.getSession();
            Users user = (Users) s.getAttribute("currentuser");

            if (user != null) {
                // Update old data by fetching above data
                String oldProfile = user.getProfile();
                user.setFullName(name);
                user.setPassword(password);
                user.setContactNo(mob);

                if (!imgname.isEmpty()) {
                    user.setProfile(imgname);
                }

                // Update database
                UserData userdao = new UserData(Connector.getConnection());
                boolean flag = userdao.updateUser(user);

                if (flag) {
                    s.setAttribute("currentuser", user); // Update session with new user data

                    if (!imgname.isEmpty()) {
                        // Construct the path for the new image
                        String path = UPLOAD_DIR + File.separator + user.getProfile();
                        out.println("New image path: " + path); // Debugging output

                        // Delete the old image if it exists and is not the default one
                        if (!oldProfile.equals("default.png")) {
                            String oldImagePath = UPLOAD_DIR + File.separator + oldProfile;
                            out.println("Old image path: " + oldImagePath); // Debugging output
                            if (Helper.deleteFile(oldImagePath)) {
                                out.println("Old profile image deleted successfully.");
                            } else {
                                out.println("Failed to delete old profile image.");
                            }
                        }

                        // Save the new image
                        if (Helper.updateFile(part.getInputStream(), path)) {
                            out.println("Profile successfully updated...");
                            // Create message class instance with success styling
                            Message msg = new Message("Profile successfully updated...", "success", "alert-success");
                            s.setAttribute("msg", msg);
                        } else {
                            out.println("Failed to save new profile picture...");
                            Message msg = new Message("Failed to save new profile picture...", "error", "alert-danger");
                            s.setAttribute("msg", msg);
                        }
                    } else {
                        // No new image provided, just update the other details
                        out.println("Profile successfully updated...");
                        Message msg = new Message("Profile successfully updated...", "success", "alert-success");
                        s.setAttribute("msg", msg);
                    }
                } else {
                    out.println("Update failed...!!!");
                }
            } else {
                out.println("No user found in session. Please login again.");
                response.sendRedirect("profile.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
