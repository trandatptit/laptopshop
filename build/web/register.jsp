<%-- 
    Document   : register
    Created on : Oct 18, 2023, 8:54:33 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>        

        <link rel="stylesheet" href="./css/register.css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>
    <body>
        <section class="bg-image"
                 style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Đăng kí tài khoản</h2>

                                    <form action="register" id="signupForm" method="post">
                                        <div class="form-outline mb-4">
                                            <input type="text" name="username" required class="form-control form-control-lg" placeholder="Tên tài khoản" />                                            
                                        </div>

                                        <div class="form-outline mb-4"> 
                                            <h6 id="status_email"></h6>
                                            <input type="email" name="email" id="email" required placeholder="Email"  class="form-control form-control-lg" />                                            
                                            <script>
                                                function isEmailValid(email) {
                                                    // Regular expression pattern for a valid email address
                                                    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                                                    return emailPattern.test(email);
                                                }
                                                let emailInput = document.getElementById("email");
                                                emailInput.addEventListener("blur", (event) => {
                                                    let email = emailInput.value.trim();
                                                    let status_email = document.getElementById("status_email");
                                                    if (!isEmailValid(email)) {
                                                        status_email.innerHTML = "Email khong dung dinh dang";
                                                        status_email.classList.add('mb-2');
                                                        status_email.style.color = "red";
                                                    } else {
                                                        status_email.innerHTML = "";
                                                    }
                                                });
                                            </script>
                                        </div>                                        

                                        <div class="form-outline mb-4" >   
                                            <h6 id="status_pass"></h6>
                                            <div id="show_hide_password1">
                                                <input type="password" id="password" name="password" required placeholder="Mật khẩu" class="form-control form-control-lg" />
                                                <div class="input-group-addon">                                                 
                                                    <a href=""><i class="fas fa-eye-slash"></i></a>
                                                </div>                                                
                                            </div>
                                            <script>
                                                // Get a reference to the password input field and the error message span
                                                let passwordInput = document.getElementById("password");
                                                // Add an event listener to the input field to validate the password
                                                passwordInput.addEventListener("blur", (event) => {
                                                    const password = passwordInput.value;
                                                    let status_pass = document.getElementById("status_pass");
                                                    // Define the regular expression pattern for password validation
                                                    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
                                                    // Check if the password matches the pattern
                                                    if (!passwordPattern.test(password)) {
                                                        status_pass.innerHTML = "Password must contain at least eight characters, at least one letter, one number and one special character.";
                                                        status_pass.classList.add('mb-2');
                                                        status_pass.style.color = "red";
                                                    } else {
                                                        status_pass.innerHTML = "";
                                                    }
                                                });
                                            </script>
                                        </div>

                                        <div class="form-outline mb-4" >
                                            <h6 id="status_pass_confirm"></h6>
                                            <div id="show_hide_password2"> 
                                                <input type="password" id="confirmPassword" name="confirmPassword" required class="form-control form-control-lg" placeholder="Nhập lại mật khẩu" />                                            
                                                <div class="input-group-addon">                                                 
                                                    <a href=""><i class="fas fa-eye-slash"></i></a>
                                                </div>                                                
                                            </div>
                                            <script>//check if password in confirm password field matches that in password field
                                                let password = document.getElementById("password");
                                                let passwordConfirm = document.getElementById("confirmPassword");
                                                let status_pass_confirm = document.getElementById("status_pass_confirm");
                                                passwordConfirm.addEventListener("blur", (event) => {
                                                    try {
                                                        if (passwordConfirm.value !== password.value) {
                                                            status_pass_confirm.innerHTML = "Password not matched";
                                                            status_pass_confirm.classList.add('mb-2');
                                                            status_pass_confirm.style.color = "red";
                                                        } else {
                                                            status_pass_confirm.innerHTML = "";
                                                        }
                                                    } catch (error) {
                                                        status_pass_confirm.innerHTML = "Password not matched";
                                                        status_pass_confirm.classList.add('mb-2');
                                                        status_pass_confirm.style.color = "red";
                                                    }
                                                });
                                            </script>                                            
                                        </div>                                       

                                        <div class="form-outline mb-4">  
                                            <h6 id="status_phone"></h6>
                                            <input type="text" id="phone" name="phone" required class="form-control form-control-lg" placeholder="Số điện thoại" />
                                            <script>//check phone number
                                                let phone = document.getElementById("phone");
                                                let status_phone = document.getElementById("status_phone");
                                                phone.addEventListener("blur", (event) => {
                                                    try {
                                                        if (phone.value.length !== 10) {
                                                            status_phone.innerHTML = "Phone number have 10 digits";
                                                            status_phone.classList.add('mb-2');
                                                            status_phone.style.color = "red";
                                                        } else {
                                                            status_phone.innerHTML = "";
                                                        }
                                                    } catch (error) {
                                                        status_phone.innerHTML = "Phone number have 10 digits";
                                                        status_phone.classList.add('mb-2');
                                                        status_phone.style.color = "red";
                                                    }
                                                });
                                            </script>                                            
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="text" name="address" required class="form-control form-control-lg" placeholder="Địa chỉ"/>
                                        </div>

                                        <div class="form-check d-flex justify-content-center mb-5">
                                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3cg" />
                                            <label class="form-check-label" for="form2Example3g">
                                                Tôi đồng ý tất cả các <a href="#!" class="text-body"><u>Điều khoản dịch vụ</u></a>
                                            </label>
                                        </div>

                                        <div class="d-flex justify-content-center">
                                            <button type="submit" style="width: 100%"
                                                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">
                                                Đăng ký
                                            </button>
                                        </div>

                                        <p class="text-center text-muted mt-5 mb-0">Bạn đã có tài khoản?
                                            <a href="login" class="fw-bold text-body">
                                                <span>Đăng nhập tại đây</span>
                                            </a>
                                        </p>


                                        <%
                                            // Server-side code to handle failed registration attempt
                                            String message = (String) request.getAttribute("ms");
                                            if (message != null) {
                                        %> 
                                        <!-- Display error message for failed registration -->
                                        <div class="WrongRegister">
                                            <p><%= message%></p>
                                        </div>
                                        <%
                                            }
                                        %>                                                                                
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>   

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="./js/user/register.js"></script>
    </body>
</html>
