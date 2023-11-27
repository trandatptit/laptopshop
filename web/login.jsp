<%-- 
    Document   : login
    Created on : Oct 19, 2023, 9:07:28 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="./css/login.css"/>
        <title>Login</title>
    </head>
    <body>
        <%
            String failedLogin = (String) request.getAttribute("failedLogin");
            if (failedLogin != null) {
        %>
        <script>
            swal("Error", "<%= failedLogin%>", "error");
        </script>
        <%
            }
        %>

        <%
            String status = (String) session.getAttribute("status");
            if (status != null) {
        %>
        <script>
            swal("Congrats", "<%= status%>", "success");
        </script>
        <%
            }
            session.removeAttribute("status");            
        %>

        <section class="vh-100" style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                            <div class="card shadow-2-strong" style="border-radius: 1rem;">
                                <form action="login" method="post">
                                    <div class="card-body p-5 text-center">

                                        <h3 class="mb-5">Sign in</h3>

                                        <div class="form-outline mb-4">                                    
                                            <input type="email" name="email" placeholder="Email" class="form-control form-control-lg" />
                                        </div>

                                        <div class="form-outline mb-4" id="show_hide_password">  
                                            <input type="password" name="password" placeholder="Password" class="form-control form-control-lg" />
                                            <div class="input-group-addon">                                                 
                                                <a href=""><i class="fas fa-eye-slash"></i></a>
                                            </div>
                                        </div>

                                        <!-- Checkbox -->
                                        <div class="form-check d-flex justify-content-start mb-4">
                                            <input class="form-check-input" type="checkbox"  value="" name="remember" id="form1Example3" />
                                            <label class="form-check-label" for="form1Example3"> Remember password </label>
                                        </div>
                                        <div>
                                            <button class="btn btn-success btn-block btn-lg gradient-custom-4 text-body" style="width: 100%" type="submit">Login</button>
                                        </div>

                                        <div class="mt-4">
                                            Don't have an account?<a href="register">Sign up</a>
                                        </div>
                                        <div class="signup_link">
                                            Forgot your password?<a href="forgotPassword">Reset here</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>            
                </div>

            </div>
        </section>

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

        <script src="./js/user/login.js"></script>
    </body>
</html>
