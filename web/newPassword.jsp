<%-- 
    Document   : newPassword
    Created on : Oct 21, 2023, 9:28:16 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>New Password</title>
        <link
            href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
            rel='stylesheet'>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
        <script type='text/javascript'
        src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>
            .placeicon {
                font-family: fontawesome
            }

            .custom-control-label::before {
                background-color: #dee2e6;
                border: #dee2e6
            }
            .error {
                color: red;
                font-size: 14px;
            }
        </style>
        <link rel="stylesheet" href="./css/newPassword.css"/>
    </head>
    <body>        
        <section style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container py-5 h-100">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <!-- Card Container -->
                            <div class="card bg-white rounded mt-2 mb-2">
                                <div class="card-body px-5">
                                    <!-- Main Heading -->
                                    <div class="row justify-content-center align-items-center pt-3">
                                        <h1 class="text-center">
                                            <strong>Đặt lại mật khẩu</strong>
                                        </h1>
                                    </div>
                                    <div class="pt-3 pb-3">
                                        <form class="form-horizontal" action="newPassword" method="post">
                                            <!-- New Password Input -->
                                            <div class="form-outline mb-4" id="show_hide_password1">  
                                                <input type="password" name="password" placeholder="Mật khẩu mới" class="form-control form-control-lg" required/>
                                                <div class="input-group-addon">                                                 
                                                    <a href=""><i class="fas fa-eye-slash"></i></a>
                                                </div>
                                            </div>
                                            <div class="form-outline mb-4" id="show_hide_password2">  
                                                <input type="password" name="confPassword" placeholder="Nhập lại mật khẩu mới" class="form-control form-control-lg" required/>
                                                <div class="input-group-addon">                                                 
                                                    <a href=""><i class="fas fa-eye-slash"></i></a>
                                                </div>
                                            </div>
                                            <!--                                            <div class="form-group row justify-content-center px-3">
                                                                                            <div class="col-9 px-0">
                                                                                                <input type="password" name="password" placeholder="New Password"
                                                                                                       class="form-control border-info" required>
                                                                                            </div>
                                                                                        </div>-->
                                            <!-- Confirm New Password Input -->
                                            <!--                                            <div class="form-group row justify-content-center px-3">
                                                                                            <div class="col-9 px-0">
                                                                                                <input type="password" name="confPassword" placeholder="Confirm New Password"
                                                                                                       class="form-control border-info" required>
                                                                                            </div>
                                                                                        </div>-->
                                            <div class="form-group row justify-content-center px-3">
                                                <div class="col-9 px-0">
                                                    <% String message = (String) request.getAttribute("message"); %>
                                                    <% if (message != null) { %>
                                                    <p class="error"><%= message %></p>
                                                    <% } %>
                                                </div>
                                            </div>
                                            <!-- Reset Button -->
                                            <div>
                                                <button class="btn btn-success btn-block btn-lg gradient-custom-4 text-body" style="width: 100%" type="submit">Đặt lại</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- Alternative Login -->
                                <div class="card-footer px-5 bg-light">
                                    <!-- Horizontal Line -->
                                    <div class="px-4 pt-5">
                                        <hr>
                                    </div>
                                    <!-- Register Now -->
                                    <div class="pt-2">
                                        <div class="row justify-content-center">
                                            <h5>
                                                Bạn chưa có tài khoản?
                                                <span>
                                                    <a href="register" class="text-danger"> Đăng ký ngay!</a>
                                                </span>
                                            </h5>
                                        </div>
                                        <div class="row justify-content-center align-items-center pt-4 pb-5">
                                            <div class="col-4"><!-- Add content if needed --></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!--        <section style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
                    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                        <div class="container  py-5 h-100">
                            <div class="row justify-content-center">
                                <div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-5">
                                     White Container 
                                    <div class="container bg-white rounded mt-2 mb-2 px-0">
                                         Main Heading 
                                        <div class="row justify-content-center align-items-center pt-3">
                                            <h1>
                                                <strong>Reset Password</strong>
                                            </h1>
                                        </div>
                                        <div class="pt-3 pb-3">
                                            <form class="form-horizontal" action="newPassword" method="post">
                                                 User Name Input 
                                                <div class="form-group row justify-content-center px-3">
                                                    <div class="col-9 px-0">
                                                        <input type="password" name="password" placeholder="&#xf084; &nbsp; New Password"
                                                               class="form-control border-info placeicon" required>
                                                    </div>
                                                </div>
                                                 Password Input 
                                                <div class="form-group row justify-content-center px-3">
                                                    <div class="col-9 px-0">
                                                        <input type="password" name="confPassword"
                                                               placeholder="&#xf084; &nbsp; Confirm New Password"
                                                               class="form-control border-info placeicon" required>
                                                    </div>
                                                </div>
                                                <div class="form-group row justify-content-center px-3">
                                                    <div class="col-9 px-0">
        
    </div>
</div>

 Log in Button 
<div class="form-group row justify-content-center">
    <div class="col-3 px-3 mt-3">
        <input type="submit" value="Reset"
               class="btn btn-block btn-info">
    </div>
</div>
</form>
</div>
Alternative Login 
<div class="mx-0 px-0 bg-light">

Horizontal Line 
<div class="px-4 pt-5">
<hr>
</div>
Register Now 
<div class="pt-2">
<div class="row justify-content-center">
    <h5>
        Don't have an Account?
        <span>
            <a href="register" class="text-danger"> Register Now!</a>
        </span>
    </h5>
</div>
<div class="row justify-content-center align-items-center pt-4 pb-5">
    <div class="col-4">

    </div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</section>        -->
        <script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
        <script src="./js/user/newPassword.js"></script>
    </body>
</html>
