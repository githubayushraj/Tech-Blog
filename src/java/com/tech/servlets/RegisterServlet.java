package com.tech.servlets;

import com.tech.dao.UserData;
import com.tech.entite.Users;
import com.tech.helper.Connector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Fetch all data
            String check = request.getParameter("check");
            if (check == null) {
                out.println("Terms and condition not accepted...");
            } else {
                // If check is not null, data comes here
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String contact = request.getParameter("contact");
                String gender = request.getParameter("gender");


                // Create user object and set all data to object;
                Users user = new Users(name, email, password, contact, gender);

                // Create a user dao object;
                UserData dao = new UserData(Connector.getConnection());
                boolean result = dao.saveUser(user);

                // Debugging: Check if user was saved successfully
                System.out.println("User saved: " + result);

                if (result) {
                    out.println("successfully");
                } else {
                    out.println("Error while saving user");
                }
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
