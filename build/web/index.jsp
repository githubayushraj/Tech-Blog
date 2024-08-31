<%@page import="java.util.List"%>
<%@page import="com.tech.entite.Post"%>
<%@page import="com.tech.dao.PostDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="com.tech.helper.Connector" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Bootstrap CSS -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <!--for sweet alert-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!--design banner use clipy path from google-->
        <style>
            .banner-background {
                clip-path: polygon(50% 0%, 99% 0, 100% 91%, 67% 99%, 32% 92%, 0 99%, 1% 0);
                background: linear-gradient(45deg, #f06, #ffba08);
                color: white;
                padding: 50px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>

        <!-- Navbar -->
        <%@include file="navbar.jsp" %>


        <!-- Banner -->
        <div class="container-fluid p-0 m-0 bg-dark text-white">
            <div class="jumbotron banner-background">
                <div class="container">
                    <h3 class="display-3">Welcome to TechBlog</h3>
                    <p>Welcome to Tech Blog, World of Technology</p> 
                    <p>A programming language is a way for programmers (developers) to communicate with computers. Programming languages consist of a set of rules that allow string values to be converted into various ways of generating machine code, or, in the case of visual programming languages, graphical elements.</p>
                    <button class="btn btn-outline-light m-2 free">Start! It's free</button>
                    <a href="login.jsp" class="btn btn-outline-light m-2">Login</a>
                </div>                
            </div>
        </div>

        <!-- Cards -->
        <div class="container mt-4">
            <div class="row justify-content-center">
                <%
                    PostDao pd = new PostDao(Connector.getConnection());
                    List<Post> pst = pd.getAllPost();
                    for (Post p : pst) {
                %>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <img src="blog_pics/<%= p.getPic()%>" class="card-img-top" alt="Post Image" style="height: 250px; object-fit: cover;">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title"><%= p.getTitle()%></h5>
                            <button id="read-more" type="button" class="btn btn-primary mt-auto">Read More</button>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>



        <!-- JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

        <script>
            $(document).ready(function () {
                $(".btn-primary").click(function () {
                    Swal.fire({
                        title: "Join Us to Explore More!",
                        text: "Unlock full access to all posts by registering now. Don't miss out on the latest tech insights!",
                        icon: "info",
                        confirmButtonText: "Register Now"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location = "register.jsp";
                        }
                    });
                });
            });
        </script>
        
        <script>
            $(document).ready(function () {
                $(".free").click(function () {
                    Swal.fire({
                        title: "Join Us to Explore More!",
                        text: "Unlock full access to all posts by registering now. Don't miss out on the latest tech insights!",
                        icon: "info",
                        confirmButtonText: "Register Now"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location = "register.jsp";
                        }
                    });
                });
            });
        </script>



    </body>
</html>

