package com.tech.servlets;

import com.tech.dao.UserData;
import com.tech.entite.Message;
import com.tech.entite.Users;
import com.tech.helper.Connector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetch email and password from login form
            String userEmail = request.getParameter("email");
            String userPassword = request.getParameter("password");
            
            UserData dao = new UserData(Connector.getConnection());
            Users user = dao.getUserEmailAndPassword(userEmail, userPassword);
            
            if (user == null) {
                // Login error
                // Create message class instance with error styling
                Message msg = new Message("Invalid User", "error", "alert-danger");
                HttpSession s = request.getSession();
                s.setAttribute("msg", msg);
                
                // Redirect to login page
                response.sendRedirect("login.jsp");
            } else {
                // Login successful
                HttpSession s = request.getSession();
                s.setAttribute("currentuser", user);
                
                // Redirect to profile page
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
