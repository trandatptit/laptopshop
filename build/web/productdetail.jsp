<%-- 
    Document   : productdetail
    Created on : Nov 27, 2023, 3:49:46 PM
    Author     : trant
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.NguoiDung" %>
<!DOCTYPE html>
<html>
    
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết sản phẩm</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- FONT AWESOME  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

        <!-- FONT GOOGLE -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap" rel="stylesheet">
        
        <!-- Bootstrap CSS -->
        
        <link rel="stylesheet" href="./css/main.css">
        <!-- Custom css - Các file css tự viết -->
        <link rel="stylesheet" href="./assets/css/product-detail.css" type="text/css">

        
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
        <header id="header">
            <div class="container" style="padding: 0px">
                <!--                <div class="row header-banner">
                                    <img src="./image/banner_TOP.png" alt="">
                                </div>-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light col-12">
                    <div class="container">
                        <a class="navbar-brand" href="home">
                            <img src="./image/logo_az.jpg" alt="" style="height: 89px; wheight: 150px" ">
<!--                            Laptop AZ-->
                        </a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse header-navbar--content col-md-10" id="navbarSupportedContent">
                            <form class="form-inline ml-lg-auto">
                                <div class="input-group navbar-search">
                                    <input type="text" class="form-control" placeholder="Search" aria-label="Search"
                                           aria-describedby="basic-addon1" style="border-radius: 5px 0 0 5px;">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-secondary" type="button"><i
                                                class="fa fa-search"></i></button>
                                    </div>
                                </div>
                            </form>
                            <ul class="navbar-nav ml-lg-auto d-flex align-items-center">
                                <li class="nav-item">
                                    <a class="nav-link" href="show"><i class="fa fa-shopping-cart"></i>Giỏ Hàng(${requestScope.size})</a>
                                    <!--<a class="nav-link" href="./cart.html"><i class="fa fa-shopping-cart"></i>Mybag(${requestScope.size}) item</a>-->
                                </li>
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
                                            <a href="#" class="dropdown-item">Settings</a>
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
                    </div>
                </nav>
            </div>
            <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa fa-angle-double-up"></i></button>
        </header>
        
        <!--Bắt đầu hiển thị chi tiết sản phẩm-->
        <main role="main">
            <!-- Block content - Đục lỗ trên giao diện bố cục chung, đặt tên là `content` -->
            <div class="container mt-4">
                <div id="thongbao" class="alert alert-danger d-none face" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>

                <div class="card">
                    <div class="container-fliud">
                        <form name="f" id="frmsanphamchitiet" method="post" action="">
                            

                            <div class="wrapper row">
                                <div class="preview col-md-6">
                                    <div class="preview-pic tab-content">
                                        <div class="tab-pane active" id="pic-1">
                                            <img src="./image/${sanpham.anhDaiDien}" class="img-fluid image_product img-active" id="activeImage">
                                        </div>
                                        <c:forEach items="${requestScope.hinhanhsp}" var="i" varStatus="loop">
                                            <div class="tab-pane ${loop.first ? 'active' : ''}" id="pic-${loop.index}">
                                                <img src="./image/${i.url}" class="img-thumbnail" onclick="changeImage(this, ${loop.index})"  />
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <ul class="preview-thumbnail nav nav-tabs">
                                        <c:forEach items="${requestScope.hinhanhsp}" var="i" varStatus="loop">
                                            <li class="${loop.first ? 'active' : ''}">
                                                <a data-target="#pic-${loop.index}" data-toggle="tab" class="">
                                                    <img src="./image/${i.url}" class="img-thumbnail" onclick="changeImage(this, ${loop.index})" />
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>



                                <div class="details col-md-6">
                                    <h3 class="product-title">${sanpham.tenSanPham}</h3>
                                    <div class="rating">
                                        <div class="stars">
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star checked"></span>
                                            <span class="fa fa-star"></span>
                                            <span class="fa fa-star"></span>
                                        </div>
                                        <span class="review-no">999 reviews</span>
                                    </div>
                                    <p class="product-description">${sanpham.moTaNgan}</p>
                                    <small class="text-muted">Giá cũ: <s><span>10,990,000.00 vnđ</span></s></small>
                                            <h5 class="price">Giá hiện tại: <span>
                                                    <fmt:formatNumber value="${sanpham.giaBan}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                    <span style="color: red;">${formattedPrice} VNĐ</span>
                                        </span></h5>
                                    <p class="vote"><strong>100%</strong> hàng <strong>Chất lượng</strong>, đảm bảo
                                        <strong>Uy
                                            tín</strong>!</p>
                                    <h5 class="sizes">Bảo Hành:
                                        <span>12 Tháng LaptopAZ</span>
                                    </h5>
                                    <h7 class="colors">Tình trạng:
                                        <span style="color: red;">Còn ${sanpham.soLuong} sản phẩm</span>
                                    </h7>
                                    <div class="form-group">
                                        <label for="soluong">Số lượng đặt mua:</label>
                                        <input style="text-align: center" name="num" value="1"/>
                                    </div>
                                    <div class="action">
<!--                                        <a class="add-to-cart btn btn-default" id="btnThemVaoGioHang">Thêm vào giỏ hàng</a>-->
                                        <input class="add-to-cart btn btn-default" id="btnThemVaoGioHang" type="button" onclick="buy('${sanpham.id}')" value="Thêm vào giỏ hàng"/>
                                    </div>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>

                <div class="card">
                    <div class="container-fluid">
                        <h3>Thông tin chi tiết về Sản phẩm</h3>
                        <div class="row">
                            <div class="col">
                                ${sanpham.moTaChiTiet}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End block content -->
        </main>
        <script src="./vendor/jquery/jquery.min.js"></script>
        <script src="./vendor/popperjs/popper.min.js"></script>
        <script src="./vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Custom script - Các file js do mình tự viết -->
        <script src="./assets/js/app.js"></script>         
        <script>
            function changeImage(clickedImage, imageIndex) {
                debugger
                // Lấy src của ảnh được click
                var newSrc = clickedImage.src;

                // Thay đổi src của ảnh ở tab-pane active
                document.getElementById('activeImage').src = newSrc;

                // Hiển thị tab-pane tương ứng với ảnh được click
                $('.tab-content').find('.tab-pane').removeClass('active');
                $('#pic-' + imageIndex).addClass('active');
            }
        </script> 
        
        <script type="text/javascript">
            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?id=" + id + "&num=" + m;
                document.f.submit();
                
            }
        </script>                    
    </body>
</html>
