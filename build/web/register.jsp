<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>

        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <!-- CSS for this page -->
        <link rel="stylesheet" href="css/myStyle.css">

        <!--for sweet alert-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </head>
    <body>

        <!-- Navbar -->
        <%@include file="navbar.jsp" %>

        <div class="banner-background">
            <h1 class="display-4">Welcome to TechBlog</h1>
            <p>Your source for the latest in technology and programming.</p>
        </div>

        <div class="container d-flex align-items-center justify-content-center" style="height: 100vh;">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6 form-container">
                    <div class="card">
                        <div class="card-header">
                            <p>Register Here</p>
                        </div>
                        <div id="msg" class="message"></div>
                        <div class="form card-body">
                            <form id="reg-form" action="RegisterServlet" method="post">
                                <input type="text" name="name" placeholder="Enter name" required>
                                <input type="email" name="email" placeholder="Enter email" required>
                                <input type="password" name="password" placeholder="Enter password" required>
                                <input type="tel" name="contact" placeholder="Enter contact number" required>
                                <select class="form-select" name="gender" required>
                                    <option value="" disabled selected>Select gender</option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                    <option value="other">Other</option>
                                </select>

                                <!-- Terms and Conditions Checkbox -->
                                <div class="form-check mt-3">
                                    <input class="form-check-input custom-checkbox" type="checkbox" id="termsAndConditions" name="check" required>
                                    <label class="form-check-label" for="termsAndConditions">
                                        I agree terms and conditions
                                    </label>
                                </div>
                                <div class="container text-center">
                                    <button type="submit" class="btn btn-custom mt-3">Register</button>
                                </div>


                            </form>
                        </div>

                        <!-- Loader -->
                        <div class="loader d-flex justify-content-center" style="display: none">
                            <div class="preloader-wrapper big active">
                                <div class="spinner-layer spinner-blue">
                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="gap-patch">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>
                                </div>

                                <div class="spinner-layer spinner-red">
                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="gap-patch">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>
                                </div>

                                <div class="spinner-layer spinner-yellow">
                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="gap-patch">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>
                                </div>

                                <div class="spinner-layer spinner-green">
                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="gap-patch">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>


        <!-- AJAX -->
        <script>
            $(document).ready(function () {
                $("#reg-form").on('submit', function (event) {
                    event.preventDefault();
                    let formData = new FormData(this);

                    $.ajax({
                        url: "RegisterServlet",
                        type: 'POST',
                        data: formData,
                        beforeSend: function () {
                            // Show loader and hide form
                            $(".loader").show();
                            $(".form").hide();
                        },
                        processData: false,
                        contentType: false,
                        success: function (data, textStatus, jqXHR) {
                            console.log("Successfully registered...");
                            $(".loader").hide();
                            $(".form").show();

                            if (data.trim() === 'successfully') {
                                $("#msg").html("Successfully registered...").removeClass("error").addClass("success");
                                //                            sweet message
                                Swal.fire("Successfully Registered... Redirecting to the login page.")
                                        .then((value) => {
                                            window.location = "login.jsp";
                                        });
                            } else {
                                $("#msg").html("Something went wrong").removeClass("success").addClass("error");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                            $(".loader").hide();
                            $(".form").show();
                        }
                    });
                });
            });
        </script>

    </body>
</html>
