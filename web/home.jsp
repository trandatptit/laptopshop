<%-- 
    Document   : home
    Created on : Oct 19, 2023, 10:45:56 AM
    Author     : PC
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
        <title>Laptop AZ</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- FONT AWESOME  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

        <!-- FONT GOOGLE -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="./css/main.css">
    </head>
    <body>
        
        <!-- Kiểm tra xem có thông báo trong Session không -->
        
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



        <!-- banner -->
        <section class="carousel">
            <div class="container">
                <div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="3000">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <img src="./image/quangcao_1.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_2.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_3.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_4.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_5.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_6.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_7.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_8.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_9.jpg" class="d-block w-100" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./image/quangcao_10.jpg" class="d-block w-100" alt="...">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </section>

        <!-- filter -->
        <section id="laptop" class="filter">
            <div class="container">
                <div class="row">
                    
                    <div class="card">
                        <a href="#"><img src="./image/Acer44-b_25.jpg" alt=""></a>
                    </div>
                    <div class="card">
                        <a href="#"><img src="./image/Asus44-b_1.png" alt=""></a>
                    </div>
                    <div class="card">
                        <a href="#"><img src="./image/Dell44-b_2.jpg" alt=""></a>
                    </div>
                    <div class="card">
                        <a href="#"><img src="./image/HP44-b_27.jpg" alt=""></a>
                    </div>
                    <div class="card">
                        <a href="#"><img src="./image/Lenovo44-b_35.png" alt=""></a>
                    </div>
                    <div class="card">
                        <a href="#"><img src="./image/MSI44-b_17.png" alt=""></a>
                    </div>
                    <div class="card">
                        <a href="#"><img src="./image/MacBook44-b_27.png" alt=""></a>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="dropdown">
                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            RAM
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="#">1GB</a>
                            <a class="dropdown-item" href="#">2GB</a>
                            <a class="dropdown-item" href="#">4GB</a>
                            <a class="dropdown-item" href="#">8GB</a>
                            <a class="dropdown-item" href="#">16GB</a>
                            <a class="dropdown-item" href="#">32GB</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Vi xử lý
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="#">i3</a>
                            <a class="dropdown-item" href="#">i5</a>
                            <a class="dropdown-item" href="#">i7</a>
                            <a class="dropdown-item" href="#">i9</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Màn hình
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="#"> Nhỏ hơn 13 inch</a>
                            <a class="dropdown-item" href="#">13 inch</a>
                            <a class="dropdown-item" href="#">14 inch</a>
                            <a class="dropdown-item" href="#">15 inch</a>
                            <a class="dropdown-item" href="#"> Lớn hơn 15 inch</a>
                        </div>
                    </div>
                    <div class="dropdown">
                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Giá
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" href="#">Dưới 10 triệu</a>
                            <a class="dropdown-item" href="#">Từ 10 triệu đến 15 triệu</a>
                            <a class="dropdown-item" href="#">Từ 15 triệu đến 20 triệu</a>
                            <a class="dropdown-item" href="#">Từ 20 triệu đến 25 triệu</a>
                            <a class="dropdown-item" href="#">Hơn 20 triệu</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="item">
            <div class="container">
                <div class="row">
                    <h2>Sản phẩm mới</h2>
                </div>
                <div class="row">
                    <c:if test="${sessionScope.create == null && sessionScope.edit == null}">
                        <c:forEach items="${requestScope.data}" var="p" varStatus="loop">
                            <div class="col-lg-3 col-md-4 col-sm-6 col-12 mb-4">
                                <div class="card h-100">
                                    <div class="card-img-top card_top">
                                        <a href="productdetail?id=${p.id}">
                                            <img src="./image/${p.anhDaiDien}" class="img-fluid image_product" alt="${p.tenSanPham}">
                                            
                                        </a>
                                    </div>
                                    <div class="card-body d-flex flex-column justify-content-between">
                                         Phần đầu của card-body 
                                        <div class="card-info-top">
                                            <a>
                                                <h5 class="card-title limit-lines-2">${p.tenSanPham}</h5>
                                            </a>
                                            <p class="card-text limit-lines">${p.moTaNgan}</p>
                                        </div>
                                         Phần cuối của card-body 
                                        <div class="card-info-bottom">
                                            <div>
                                                <div>
                                                    <fmt:formatNumber value="${p.giaBan}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                    <span style="color: red;">đ${formattedPrice}</span>
                                                    <br>
                                                    <span>Số lượng: ${p.soLuong}</span>
                                                </div>
                                                <div>
                                                     Thêm sao đánh giá nếu cần 
                                                     <span class="fa fa-star checked"></span> 
                                                     <span class="fa fa-star checked"></span> 
                                                     <span class="fa fa-star checked"></span> 
                                                     <span class="fa fa-star"></span> 
                                                     <span class="fa fa-star"></span> 
                                                     Nút thêm vào giỏ hàng                                                     
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </section>

        <section id="bestseller" class="bestseller">

            <div class="container">
                <div class="row bestseller-title">
                    <h2>Sản phẩm bán chạy</h2>
                </div>
                <!--H-->
                <form name="f" action="" method="post">
                    <input style="text-align: center" name="num" value="1"/>
                    <hr/>
                    <div class="row bestseller-content">
                        <c:if test="${sessionScope.create == null && sessionScope.edit == null}">
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Hình ảnh</th>
                                            <th>Tên SP</th>
                                            <th>Danh Mục</th>
                                            <th>Đơn giá</th>
                                            <th>Số lượng</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.data}" var="O">
                                            <tr>
                                                <td>
                                                    <img src="./image/${O.anhDaiDien}" alt="anh" width="100px" height="100px" style="object-fit: contain" />
                                                </td>
                                                <td>${O.tenSanPham}</td>
                                                <td>${O.danhMuc.tenDanhMuc}</td>
                                                <td>${O.giaBan}</td>
                                                <td>${O.soLuong}</td>
                                                <td>
                                                    <input type="button" onclick="buy('${O.id}')" value="Buy item"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </c:if>
                    </div>
                </form>
            </div>
        </section>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>

        <!-- popper -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>

        <!-- BS4 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
        <!-- back to top -->
        <script type="text/javascript">
                                                        var mybutton = document.getElementById("myBtn");
                                                        var headerNav = document.getElementsByTagName('header');

                                                        window.onscroll = function () {
                                                            scrollFunction();
                                                        };
                                                        function scrollFunction() {
                                                            if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
                                                                mybutton.style.display = "block";
                                                            } else {
                                                                mybutton.style.display = "none";
                                                            }
                                                        }
                                                        function topFunction() {
                                                            document.body.scrollTop = 0;
                                                            document.documentElement.scrollTop = 0;
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
