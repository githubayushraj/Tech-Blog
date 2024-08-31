    <%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sorry! Page not found</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .error {
            text-align: center;
            margin-top: 50px;
        }
        .error img {
            max-width: 50%;
            height: auto;
        }
        .error h2 {
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <div class="error text-centre p-3">
        <img src="img/error404.png" class="img-fluid" alt="404 error"/>
        <h2 class="display-3">Sorry! Page not found</h2>
        <%= exception%>
        <br>
        <a class="btn btn-outline-primary" href="index.jsp">Home Page</a>
    </div>
</body>
</html>
