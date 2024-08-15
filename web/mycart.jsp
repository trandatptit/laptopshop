<%-- 
    Document   : mycart
    Created on : Nov 15, 2023, 3:01:19 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.NguoiDung" %>

<!DOCTYPE html>
<html lang="vi" class="h-100">

    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Giỏ hàng của bạn</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="./vendor/bootstrap/css/bootstrap.min.css" type="text/css">
        <!-- Font awesome -->
        <link rel="stylesheet" href="./vendor/font-awesome/css/font-awesome.min.css" type="text/css">

        <!-- Custom css - Các file css do chúng ta tự viết -->
        <link rel="stylesheet" href="./assets/css/app.css" type="text/css">
    </head>

    <body>
        <%
                NguoiDung nguoiDung = (NguoiDung)session.getAttribute("acc");
                String ten = null, avatar = null;
                if(nguoiDung != null){
                    ten = nguoiDung.getTen();
                    avatar = nguoiDung.getAvatar();    
                }
             
        %>
        <!-- header -->
        <nav class="navbar navbar-expand-md navbar-dark sticky-top bg-dark">
            <div class="container">

                <div class="navbar-collapse collapse" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="home">Trang chủ <span class="sr-only">(current)</span></a>
                        </li>

                    </ul>

                </div>
                <ul class="navbar-nav px-3">
                    <%
                                    if(nguoiDung != null){
                    %>
                    <div class="navbar-nav align-items-center ms-auto">                        
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <%                                    
                                    if(avatar == null || avatar.isEmpty()){                                                                        
                                %>

                                <img class="rounded-circle me-lg-2" src="./image/default_avatar.png" alt="" style="width: 40px; height: 40px;">
                                <%
                                    }else{
                                %>
                                <img class="rounded-circle me-lg-2" src="./image/<%= avatar %>" alt="" style="width: 40px; height: 40px;">
                                <%
                                    }
                                %>
                                <span class="d-none d-lg-inline-flex"><%= ten %></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                                <a href="<c:url value='/user/profile' />" class="dropdown-item">My Profile</a>
                                <a href="orderuser" class="dropdown-item">Đơn mua</a>
                                <a href="logout" class="dropdown-item">Log Out</a>
                            </div>
                        </div>
                    </div>
                    <% 
                        }else{
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="loginDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Đăng nhập
                        </a>
                        <div class="dropdown-menu" aria-labelledby="loginDropdown">
                            <a class="dropdown-item" href="login">Đăng nhập</a>
                            <a class="dropdown-item" href="register">Đăng kí</a>
                        </div>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </nav>
        <!-- end header -->

        <main role="main">
            <!-- Block content - Đục lỗ trên giao diện bố cục chung, đặt tên là `content` -->
            <div class="container mt-4">
                <div id="thongbao" class="alert alert-danger d-none face" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>

                <h1 class="text-center">Giỏ hàng</h1>
                <div class="row">
                    <div class="col col-md-12">
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th></th>
                                    <th>Tên</th>
                                    <th>Số lượng</th>
                                    <th>Giá</th>
                                    <th>Tổng tiền</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody id="datarow">
                                <c:set var="o" value="${requestScope.cart}"/>
                                <c:set var="tt" value="0"/>
                                <c:forEach items="${o.items}" var="i">
                                    <c:set var="tt" value="${tt+1}"/>
                                    <tr>
                                        <td>${tt}</td>
                                        <td>
                                            <img src="./image/${i.sanPham.anhDaiDien}" alt="anh" width="100px" height="100px" style="object-fit: contain" />
                                        </td>
                                        <td>${i.sanPham.tenSanPham}</td>
                                        <td class="text-center">
                                            <div class="btn-group" role="group" aria-label="Số lượng">
                                                <a href="process?num=-1&id=${i.sanPham.id}" class="btn btn-outline-secondary">-</a>
                                                <button type="button" class="btn btn-light" disabled>${i.soluong}</button>
                                                <a href="process?num=1&id=${i.sanPham.id}" class="btn btn-outline-secondary">+</a>
                                            </div>
                                        </td>

                                        <td class="tr">
                                            <fmt:formatNumber value="${i.dongia}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                            <c:out value="${formattedPrice} VNĐ" />
                                        </td>
                                        <td class="tr">
                                            <fmt:formatNumber value="${i.dongia*i.soluong}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                            <c:out value="${formattedPrice} VNĐ" />
                                        </td>
                                        <td style="text-align: center">
                                            <form action="process" method="post">
                                                <input type="hidden" name="id" value="${i.sanPham.id}"/>
                                                <button type="submit" class="btn btn-danger btn-delete-sanpham">
                                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <fmt:formatNumber value="${o.tongtien}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                        <h3><c:out value="Tổng tiền: ${formattedPrice} VNĐ" /></h3>
                        <hr/>

                        <a href="home" class="btn btn-warning btn-md"><i class="fa fa-arrow-left"
                                                                         aria-hidden="true"></i>&nbsp;Quay
                            về trang chủ</a>
                        <a href="thanhtoan.jsp" class="btn btn-primary btn-md"><i
                                class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;Thanh toán</a>


                    </div>
                </div>
            </div>
            <!-- End block content -->
        </main>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="./vendor/jquery/jquery.min.js"></script>
        <script src="./vendor/popperjs/popper.min.js"></script>
        <script src="./vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Custom script - Các file js do mình tự viết -->
        <script src="./assets/js/app.js"></script>

    </body>

</html>