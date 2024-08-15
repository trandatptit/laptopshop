<%-- 
    Document   : profile
    Created on : Oct 22, 2023, 5:23:55 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.NguoiDung" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../css/profile.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String error = (String) session.getAttribute("error");
            if (error != null) {
        %>
        <script>
            swal("Error", "<%= error%>", "error");
        </script>
        <%
            }
            session.removeAttribute("error");   
        %>

        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
        <script>
            swal("Congrats", "<%= message%>", "success");
        </script>
        <%
            }       
            session.removeAttribute("message");   
        %>
        <div class="container light-style flex-grow-1 container-p-y">

            <h4 class="font-weight-bold py-2 mb-2">
                Tài khoản của bạn
            </h4>

            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <div class="col-md-3 pt-0">
                        <div class="list-group list-group-flush account-settings-links">
                            <%
                                String changeInfo = (String) session.getAttribute("changeInfo");
                                String active_info = "";
                                if(changeInfo != null){
                                    active_info = "active";
                                }else{
                                    active_info = "";
                                }
                                String changePass = (String) session.getAttribute("changePass");
                                String active_pass = "";
                                if(changePass != null){
                                    active_pass = "active";
                                }else{
                                    active_pass = "";
                                }
                            %>                            
                            <a class="list-group-item list-group-item-action <%= active_info %>" 
                               href="profile?action=change_info">Tổng quan</a>
                            <a class="list-group-item list-group-item-action <%= active_pass %>" 
                               href="profile?action=change_pass">Đổi mật khẩu</a>                           

                        </div>
                    </div>

                    <%
                        NguoiDung nguoiDung = (NguoiDung)session.getAttribute("acc");
                        String ten = nguoiDung.getTen();
                        String email = nguoiDung.getEmail();
                        String phoneNumber = nguoiDung.getSdt();
                        String address = nguoiDung.getDiaChi();
                        String avatar = nguoiDung.getAvatar();      
                        String role = (String)session.getAttribute("role");
                    %>

                    <div class="col-md-9">
                        <c:if test="${sessionScope.changeInfo != null}">                                    
                            <form action="<c:url value='/user/profile?action=change_info' />" method="post" enctype="multipart/form-data">
                                <div class="tab-content">   
                                    <div class="tab-pane fade active show" id="account-general">

                                        <div class="card-body media align-items-center">
                                            <%                                    
                                                if(avatar == null || avatar.isEmpty()){                                                                        
                                            %>
                                            <img class="rounded-circle me-lg-2"  id="avatar-image" src="../image/default_avatar.png" alt="" style="width: 80px; height: 80px;">
                                            <%
                                                }else{
                                            %>
                                            <img class="rounded-circle me-lg-2"  id="avatar-image" src="../image/<%= avatar %>" alt="" style="width: 80px; height: 80px;">
                                            <%
                                                }
                                            %>
                                            <div class="media-body ml-4">
                                                <label class="btn btn-outline-primary">
                                                    Tải ảnh mới lên
                                                    <input type="file" id="avatar-input" name="avatar" class="account-settings-fileinput">
                                                </label> &nbsp;                                        

                                                <div class="text-light small mt-1">Cho phép ảnh JPG, GIF or PNG. kích thước tối đa 800K</div>
                                            </div>
                                        </div>
                                        <hr class="border-light m-0">

                                        <div class="card-body">
                                            <div class="form-group">
                                                <label class="form-label">Tên tài khoản</label>
                                                <input type="text" name="username" class="form-control mb-1" value="<%= ten %>">
                                            </div>                                    
                                            <div class="form-group">
                                                <label class="form-label">E-mail</label>
                                                <input type="text" name="email" class="form-control mb-1" readonly value="<%= email %>">                                        
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">Số điện thoại</label>
                                                <input type="text" name="phone" class="form-control" value="<%= phoneNumber %>">
                                            </div>
                                            <div class="form-group">
                                                <label class="form-label">Địa chỉ</label>
                                                <input type="text" name="address" class="form-control" value="<%= address %>">
                                            </div>
                                        </div>

                                    </div>
                                    <script>
                                        // JavaScript to update the avatar image when a file is selected
                                        document.getElementById('avatar-input').addEventListener('change', function () {
                                            let imageElement = document.getElementById('avatar-image');
                                            if (this.files && this.files[0]) {
                                                let reader = new FileReader();
                                                reader.onload = function (e) {
                                                    imageElement.src = e.target.result;
                                                };
                                                reader.readAsDataURL(this.files[0]);
                                            }
                                        });
                                    </script>                                                             
                                    <div class="text-right" style="padding: 0 1.25rem 1.25rem 1.25rem">
                                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>&nbsp;
                                        <button type="button" class="btn btn-default">
                                            <%
                                                if(role != "" && role.equals("admin")){                                                                                        
                                            %>
                                            <a href="<c:url value='/admin' />">
                                                Trở về
                                            </a>
                                            <%
                                                } else {
                                            %>
                                            <a href="<c:url value='/home' />">
                                                Trở về
                                            </a>
                                            <%
                                                }
                                            %>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </c:if>   
                        <c:if test="${sessionScope.changePass != null}">   
                            <form action="<c:url value='/user/profile?action=change_pass' />" method="post" enctype="multipart/form-data">
                                <div class="tab-content">                                    
                                    <div class="tab-pane fade active show" id="account-change-password">
                                        <div class="card-body pb-2">

                                            <div class="form-group">
                                                <label class="form-label">Mật khẩu cũ</label>
                                                <div id="show_hide_password1">                                                    
                                                    <input type="password" name="currentPass" class="form-control" required />
                                                    <div class="input-group-addon">                                                 
                                                        <a href=""><i class="fas fa-eye-slash"></i></a>
                                                    </div>                                                
                                                </div>                                                
                                            </div>

                                            <div class="form-group">   
                                                <label class="form-label">Mật khẩu mới</label>
                                                <h6 id="status_pass"></h6>
                                                <div id="show_hide_password2">                                                    
                                                    <input type="password" id="password" name="newPass" required class="form-control" />
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

                                            <div class="form-group">  
                                                <label class="form-label">Nhập lại mật khẩu mới</label>
                                                <h6 id="status_pass_confirm"></h6>
                                                <div id="show_hide_password3"> 
                                                    <input type="password" id="confirmPassword" name="confirmPassword" required class="form-control" />                                            
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

                                        </div>
                                    </div>  
                                    <div class="text-right" style="padding: 0 1.25rem 1.25rem 1.25rem">
                                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>&nbsp;
                                        <button type="button" class="btn btn-default">
                                            <%
                                                if(role != "" && role.equals("admin")){                                                                                        
                                            %>
                                            <a href="<c:url value='/admin' />">
                                                Trở về
                                            </a>
                                            <%
                                                } else {
                                            %>
                                            <a href="<c:url value='/home' />">
                                                Trở về
                                            </a>
                                            <%
                                                }
                                            %>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </c:if>                                    
                    </div>
                </div>
            </div>


        </div>

        <!--<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>-->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">            
        </script>        
        <script src="../js/user/profile.js"></script>
    </body>
</html>
