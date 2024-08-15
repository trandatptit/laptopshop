<%-- 
    Document   : category
    Created on : Nov 26, 2023, 5:56:33 PM
    Author     : PC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.NguoiDung" %>
<%@ page import="model.DanhMuc" %>
<%@ page import="model.SanPham" %>
<%@ page import="java.util.ArrayList" %>
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
        <style>
            body{
                min-height: 100vh;
                display: flex;
                flex-direction: column;
                justify-content: space-between;

            }
        </style>

        <link rel="stylesheet" href="./css/main.css">
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
        <div>
            <!-- header -->
            <header id="header">
                <div class="container">
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
                                <form action="" method="post" style="width: 50%;margin-left: 70px;">                                
                                    <div class="input-group navbar-search">
                                        <input type="text" name="text" id="search" class="form-control" placeholder="Search" style="border-radius: 5px 0 0 5px;">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="submit"><i
                                                    class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                    <div id="searchResults" style="position: absolute;top: 100%;left: 84px;width: 55%;z-index: 100;background-color: white;outline: none;display: none;">
                                        <ul class="list-group suggest_search">                                        
                                        </ul>
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



            <!-- filter -->        
            <section id="laptop" class="filter">
                <div class="container">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <c:if test="${not empty requestScope.tenDanhMuc}">
                                <li class="breadcrumb-item active" aria-current="page">${requestScope.tenDanhMuc}</li>
                                </c:if>
                        </ol>
                    </nav>

                    <div class="row mt-4" style="margin-left: 0; margin-right: 0">
                        <form action="filter" method="post" class="form-inline mb-4">
                            <input type="hidden" value="${requestScope.cid}" name="cid">
                            <div class="form-group">
                                <label for="ramSelect">RAM:</label>
                                <select class="form-control" name="ram" id="ramSelect">
                                    <c:if test="${requestScope.ram != null}">
                                        <option value="${requestScope.ram}" selected>${requestScope.ram}</option>                                    
                                    </c:if>                                                           
                                    <option value=""></option>                                    
                                    <option value="4 GB">4 GB</option>
                                    <option value="8 GB">8 GB</option>
                                    <option value="16 GB">16 GB</option>
                                    <option value="32 GB">32 GB</option>
                                    <option value="64 GB">64 GB</option>
                                </select>
                            </div>

                            <div class="form-group ml-2">
                                <label for="cpuSelect">Vi xử lý:</label>
                                <select class="form-control" name="cpu" id="cpuSelect">
                                    <c:if test="${requestScope.cpu != null}">
                                        <option value="${requestScope.cpu}" selected>${requestScope.cpu}</option>                                    
                                    </c:if> 
                                    <option value=""></option>
                                    <option value="i3">Intel Core i3</option>
                                    <option value="i5">Intel Core i5</option>
                                    <option value="i7">Intel Core i7</option>
                                    <option value="i9">Intel Core i9</option>
                                    <option value="celeron pentium">Intel Celeron/Pentium</option>        
                                    <option value="ryzen 5">AMD Ryzen 5</option>
                                    <option value="ryzen 7">AMD Ryzen 7</option>
                                    <option value="ryzen 9">AMD Ryzen 9</option>
                                    <option value="apple m1">Apple M1</option>
                                    <option value="apple m2">Apple M2</option>
                                    <!-- Add other Apple processors here -->
                                </select>
                            </div>

                            <div class="form-group ml-2">
                                <label for="screenSelect">Màn hình:</label>
                                <select class="form-control" name="screen" id="screenSelect">
                                    <c:if test="${requestScope.screen != null}">
                                        <option value="${requestScope.screen}" selected>${requestScope.screen}</option>                                    
                                    </c:if>
                                    <option value=""></option>                                    
                                    <option value="11.6 inch">11.6 inch</option>
                                    <option value="13 inch">13 inch</option>
                                    <option value="13.3 inch">13.3 inch</option>
                                    <option value="13.4 inch">13.4 inch</option>
                                    <option value="13.6 inch">13.6 inch</option>
                                    <option value="14 inch">14 inch</option>
                                    <option value="14.2 inch">14.2 inch</option>
                                    <option value="15.6 inch">15.6 inch</option>
                                    <option value="16 inch">16 inch</option>
                                    <option value="16.2 inch">16.2 inch</option>
                                    <option value="17 inch">17 inch</option>
                                    <option value="17.3 inch">17.3 inch</option>
                                    <option value="18 inch">18 inch</option>
                                    <!-- Adjust screen sizes as needed -->
                                </select>
                            </div>

                            <div class="form-group ml-2">
                                <label for="priceSelect">Giá:</label>
                                <select class="form-control" name="price" id="priceSelect">
                                    <c:if test="${requestScope.price != null}">
                                        <option value="" selected>${requestScope.price}</option>                                    
                                    </c:if>                             
                                    <option value=""></option>                                    
                                    <option value="0-10000000">Dưới 10 triệu</option>
                                    <option value="10000000-15000000">Từ 10 triệu đến 15 triệu</option>
                                    <option value="15000000-20000000">Từ 15 triệu đến 20 triệu</option>
                                    <option value="20000000-25000000">Từ 20 triệu đến 25 triệu</option>
                                    <option value="25000000-1000000000">Hơn 25 triệu</option>
                                </select>
                            </div>

                            <button type="submit" class="btn btn-primary ml-2">Filter</button>
                        </form>
                    </div>

                </div>
            </section>



            <section class="item">
                <div class="container">                
                    <div class="row">      
                        <%
                            ArrayList<SanPham> listItemsPerPage = (ArrayList)(request.getAttribute("listItemsPerPage"));
                            if(listItemsPerPage.isEmpty()){
                        %>
                        <h2>Không có sản phẩm nào</h2>
                        <%
                            }else{
                        %>
                        <c:forEach items="${requestScope.listItemsPerPage}" var="p" varStatus="loop">
                            <div class="col-lg-3 col-md-4 col-sm-6 col-12 mb-4">
                                <div class="card h-100">
                                    <div class="card-img-top card_top">
                                        <a href="productDetail">
                                            <img src="./image/${p.anhDaiDien}" class="img-fluid image_product" alt="${p.tenSanPham}">

                                        </a>
                                    </div>
                                    <div class="card-body d-flex flex-column justify-content-between">
                                        <!--Phần đầu của card-body--> 
                                        <div class="card-info-top">
                                            <a>
                                                <h5 class="card-title limit-lines-2">${p.tenSanPham}</h5>
                                            </a>
                                            <p class="card-text limit-lines">${p.moTaNgan}</p>
                                        </div>
                                        <!--Phần cuối của card-body--> 
                                        <div class="card-info-bottom">
                                            <div>
                                                <div>
                                                    <fmt:formatNumber value="${p.giaBan}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                    <span style="color: red;">đ${formattedPrice}</span>
                                                    <br>
                                                    <span>Số lượng: ${p.soLuong}</span>
                                                </div>
                                                <div>
                                                    <!--Thêm sao đánh giá nếu cần--> 
                                                    <span class="fa fa-star checked"></span> 
                                                    <span class="fa fa-star checked"></span> 
                                                    <span class="fa fa-star checked"></span> 
                                                    <span class="fa fa-star"></span> 
                                                    <span class="fa fa-star"></span> 
                                                    <!--Nút thêm vào giỏ hàng-->                                                     
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>      
                        <%
                        }
                        %>


                    </div>
                    <div class="row" style="margin-left: 0; margin-right: 0">
                        <!-- Phân trang -->
                        <ul class="pagination">
                            <!-- Hiển thị nút "Previous" -->
                            <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>

                            <!-- Hiển thị các trang bên trái của trang hiện tại -->
                            <c:forEach begin="${currentPage - 1}" end="${currentPage + 2}" var="page">
                                <c:if test="${page > 0 && page <= totalPages}">
                                    <c:choose>
                                        <c:when test="${page == currentPage}">
                                            <li class="page-item active">
                                                <span class="page-link">${page}</span>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a class="page-link" href="?page=${page}">${page}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>

                            <!-- Hiển thị nút "Next" -->
                            <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
        <footer class="bg-dark text-light py-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <h5>Contact Us</h5>
                        <p>Email: laptopAZadm@gmail.com</p>
                        <p>Phone: 0987879003</p>
                    </div>
                    <div class="col-md-4">
                        <h5>Follow Us</h5>
                        <p>Facebook</p>
                        <p>Twitter</p>
                        <p>Instagram</p>
                    </div>
                    <div class="col-md-4">
                        <h5>Quick Links</h5>
                        <ul class="list">
                            <li><a href="home">Home</a></li>
                            <li><a href="#">Laptops</a></li>
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contact</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>



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
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script>
                $(document).ready(function () {
                    // Khi người dùng nhập vào ô tìm kiếm
                    $('#search').on('input', function () {
                        let searchText = $(this).val();

                        // Gọi Ajax để lấy dữ liệu tìm kiếm
                        $.ajax({
                            url: '/shop/search',
                            type: 'POST',
                            data: {text: searchText},
                            success: function (data) {
                                // Hiển thị kết quả tìm kiếm trong #searchResults
                                $('#searchResults').html(data);
                                $('#searchResults').show();
                            },
                            error: function () {
                                console.log('Error during search.');
                            }
                        });
                    });

                    // Khi click ra khỏi ô tìm kiếm hoặc div chứa danh sách gợi ý, ẩn kết quả
                    $('#search').on('blur', function () {
                        // Sử dụng setTimeout để kiểm tra nếu người dùng click vào danh sách gợi ý thì không ẩn kết quả
                        setTimeout(function () {
                            if (!$('#searchResults:hover').length) {
                                $('#searchResults').hide();
                            }
                        }, 300); // Đợi 300ms trước khi ẩn kết quả
                    });

                    // Khi click vào danh sách gợi ý, không ẩn kết quả
                    $('#searchResults').on('mousedown', function () {
                        return false;
                    });
                });

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
