<%@page import="com.tech.dao.LikeDao"%>
<%@page import="com.tech.dao.LikeDao"%>
<%@page import="com.tech.entite.Message"%>
<%@page import="com.tech.entite.Categories"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.entite.Post"%>
<%@page import="com.tech.dao.PostDao"%>
<%@page import="com.tech.helper.Connector"%>
<%@page import="com.tech.entite.Users"%>

<%@page errorPage="error_404.jsp" %>

<%
    HttpSession s = request.getSession();
    Users user = (Users) s.getAttribute("currentuser");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return; // Important to stop further processing
    }
%>

<%
    int postId = Integer.parseInt(request.getParameter("post_id"));
    PostDao d = new PostDao(Connector.getConnection());
    Post p = d.getAllPostByPostId(postId);
    String userName = d.getUserNameByPid(postId);

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/myStyle.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">



    <style>
        .banner-background {
            clip-path: polygon(50% 0%, 99% 0, 100% 91%, 67% 99%, 32% 92%, 0 99%, 1% 0);
            background: linear-gradient(45deg, #f06, #ffba08);
            color: white;
            padding: 50px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .navbar-custom {
            background: linear-gradient(45deg, #1a73e8, #4285f4);
        }
        .post-button {
            background-color: #ff4081;
            color: white;
            border-radius: 20%; /* Adjust this value as needed */
            padding: 5px 15px; /* Add padding to make it look like a button */
            text-align: center; /* Center the text */
        }
        .navbar-nav.m-auto {
            margin-left: auto;
            margin-right: auto;
        }
        .nav-link {
            color: white !important;
        }
        .nav-link:hover {
            color: #ff4081 !important;
        }
        .post-title{
            font-weight: 100;
            font-size: 30px
        }
        .post-content{
            font-weight: 100;
            font-size: 25px;
        }
        .post-date{
            font-style: italic;
            font-weight: bold;
        }
        .row-user{
            border: 1px solid #e2e2e2;
            padding-top: 10px;
        }
        body{
            background: url(img/techblogback.jpeg);
            background-attachment: fixed;
            background-size: cover;
        }
    </style>    
    
</head>
<body>        

    <!--start navbar-->
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container-fluid">
            <a class="navbar-brand text-white" href="profile.jsp">Tech Blog</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                    <!--                        <li class="nav-item dropdown">
                                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
                                                    Categories
                                                </a>
                                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    <li><a class="dropdown-item" href="#">Java</a></li>
                                                    <li><a class="dropdown-item" href="#">Python</a></li>
                                                    <li><a class="dropdown-item" href="#">C++</a></li>
                                                </ul>
                                            </li>-->
                </ul>

                <!-- Post button in the middle -->
                <ul class="navbar-nav m-auto">
                    <li class="nav-item">
                        <a class="nav-link post-button" href="#"  data-bs-toggle="modal" data-bs-target="#add-post-modal">Post</a>
                    </li>
                </ul>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#!" data-bs-toggle="modal" data-bs-target="#profile-modal"><%= user.getFullName()%></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.jsp">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Display success or error message if any -->
    <%
        Message m = (Message) session.getAttribute("msg");
        if (m != null) {
    %>
    <div class="alert <%= m.getCssClass()%> text-center mt-3" role="alert">
        <%= m.getContent()%>
    </div>
    <%
            session.removeAttribute("msg");
        }
    %>

    <!--end navbar-->

    <!--start modal-->
    <div class="modal fade" id="profile-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h5 class="modal-title" id="exampleModalLabel">Tech Blog</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="container text-center">
                        <img src="profilepics/<%= user.getProfile()%>" class="img-fluid rounded-circle mb-3" style="border-radius:50%; max-width:150px;" alt="User Profile Picture">
                        <h5 class="modal-title mt-3" id="exampleModalLabel"><%= user.getFullName()%></h5>

                        <div id="profile-detail">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <th scope="row">ID :</th>
                                        <td><%= user.getId()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Email :</th>
                                        <td><%= user.getEmail()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Gender :</th>
                                        <td><%= user.getGender()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Mob no :</th>
                                        <td><%= user.getContactNo()%></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">Register on :</th>
                                        <td><%= user.getCreatedAt()%></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>                                                                                         
                        <div id="profile-edit" style="display: none">
                            <h2>Edit your profile</h2>
                            <form action="EditServlet" method="post" enctype="multipart/form-data">
                                <!--informative table-->
                                <table>
                                    <tr>
                                        <td>ID :</td>
                                        <td><span style="color: red;"><%= user.getId()%></td>
                                    </tr>
                                    <tr>
                                        <td>Email :</td>
                                        <td><span style="color: red;"><%= user.getEmail()%></td>
                                    </tr>
                                    <tr>
                                        <td>Gender :</td>
                                        <td><span style="color: red;"><%= user.getGender()%></td>
                                    </tr>
                                    <tr>
                                        <td>Profile pic :</td>
                                        <td><input type="file" name="image" class="form-control"></td>
                                    </tr>
                                    <tr>
                                        <td>Name :</td>
                                        <td><input type="text" class="form-control" name="user_name" value="<%= user.getFullName()%>"></td>
                                    </tr>
                                    <tr>
                                        <td>Mob no :</td>
                                        <td><input type="number" class="form-control" name="user_mob_no" value="<%= user.getContactNo()%>"></td>
                                    </tr>
                                    <tr>
                                        <td>Password :</td>
                                        <td><input type="password" class="form-control" name="user_password" value="<%= user.getPassword()%>"></td>
                                    </tr>
                                </table>
                                <!--end table-->
                                <div class="container text-center">
                                    <br>
                                    <button type="submit" class="btn btn-outline-primary">Save</button>
                                </div>
                            </form>
                        </div>

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button id="edit-profile-btn" type="button" class="btn btn-primary">Edit</button>
                </div>
            </div>
        </div>
    </div>


    <!--add model for post...-->
    <!-- Modal -->
    <div class="modal fade" id="add-post-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add New Post</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="AddPostServlet" method="post" enctype="multipart/form-data">
                        <div class="form-group mt-2">
                            <select class="form-control" name="category" id="category" required>
                                <option value="" disabled selected>----- Select Category -----</option>
                                <%
                                    PostDao postDao = new PostDao(Connector.getConnection());
                                    ArrayList<Categories> categoryList = postDao.getAllCategories();
                                    if (categoryList != null && !categoryList.isEmpty()) {
                                        for (Categories c : categoryList) {
                                %>
                                <option value="<%= c.getCid()%>"><%= c.getName()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select>

                        </div>

                        <div class="form-group mt-2">
                            <input type="text" name="post_title" class="form-control" placeholder="Enter post title...">
                        </div>

                        <div class="form-group mt-2">
                            <textarea name="content" class="form-control" placeholder="Enter your content (if any)"></textarea>
                        </div> 

                        <div class="form-group mt-2">
                            <textarea name="code" class="form-control" placeholder="Enter your code (if any)"></textarea>
                        </div> 

                        <div class="form-group mt-2">
                            <label>Select your picture...</label>
                            <input type="file" name="image" class="form-control">
                        </div>

                        <div class="form-group mt-2 text-center">
                            <button id="submit-post" type="submit" class="btn btn-primary">Submit Post</button>
                        </div>
                    </form>


                </div>
            </div>
        </div>
    </div>



    <!--main part of body-->

    <div class="container ">
        <div class="row ">
            <div class="col-md-5 offset-3">
                <div class="card">
                    <div class="card-header m-0">
                        <b><%= p != null ? p.getTitle() : "No title available"%></b>
                    </div>
                    <div>

                    </div>
                    <div class="card-body ">
                        <img src="blog_pics/<%=p.getPic()%>" class="card-img-top mb-1" alt="card-img-cap">
                        <div class="row row-user">
                            <div class="col-md-6">
                                <p><a href="#"><%=userName%></a> has posted</p>
                            </div>
                            <div class="col-md-6">
                                <p class="post-date"><%=p.getPostdate().toLocaleString()%></p>
                            </div>
                        </div>
                        <p class="post-content"><%= p != null ? p.getContent() : "No content available"%></p>
                        <!--                            <br> comment -->
                        <pre><%= p != null ? p.getCode() : "No code available"%></pre>
                    </div>
                    <div class="card-footer">
                        <%
                            LikeDao ld = new LikeDao(Connector.getConnection());
                        %>
                        <div class="footer primary-background text-center mb-2">
                            <a href="#" onclick="doLike(<%=p.getPid()%>,<%= user.getId()%>)" class="btn btn-outline-primary"><i class="fas fa-thumbs-up"></i> <span class="like-counter"><%=ld.countLike(p.getPid())%></span></a>
                            <a href="#" class="btn btn-outline-primary"><i class="far fa-comment"></i> <span>1</span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <!--end of main part of body-->


    <!--end of modal for post-->

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <!--sweet alert msg after post submission-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!--for like button-->
    <script src="js/myJs.js" type="text/javascript"></script>

    <script>
                                $(document).ready(function () {
                                    let editStatus = false;

                                    $("#edit-profile-btn").click(function () {
                                        if (!editStatus) {
                                            $("#profile-detail").hide();
                                            $("#profile-edit").show();
                                            editStatus = true;
                                            $(this).text("Back");
                                        } else {
                                            $("#profile-detail").show();
                                            $("#profile-edit").hide();
                                            editStatus = false;
                                            $(this).text("Edit");
                                        }
                                    });
                                });
    </script>

    <!--// now add post js using ajax-->
    <script>
        $(document).ready(function () {
            $("#addPostServlet").on("submit", function (event) {
                event.preventDefault();
                let postData = new FormData(this);
                $.ajax({
                    url: "addPostServlet",
                    type: 'post',
                    data: postData,
                    processData: false,
                    contentType: false,
                    success: function (data, textStatus, jqXHR) {
                        alert("Successfully posted.");
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("Failed to post.");
                    }
                });
            });
        });
    </script>


</body>
</html>
