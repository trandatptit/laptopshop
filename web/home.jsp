<%-- 
    Document   : home
    Created on : Oct 19, 2023, 10:45:56 AM
    Author     : PC
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.NguoiDung" %>
<%@ page import="model.DanhMuc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laptop AZ</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <!-- FONT AWESOME  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">

        <!-- FONT GOOGLE -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700&display=swap" rel="stylesheet">

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
        
        <%
            String statusOrder = (String) session.getAttribute("datHangThanhCong");
            if (statusOrder != null) {
                String mess = "Dat hang thanh cong";
        %>
        <script>
            swal("Congrats", "<%= mess%>", "success");
        </script>
        <%
            }
            session.removeAttribute("datHangThanhCong");            
        %>
        <!-- header -->
        <header id="header">
            <div class="container" style="padding: 0 0">                
                <nav class="navbar navbar-expand-lg navbar-light bg-light col-12">
                    <div class="container">
                        <a class="navbar-brand" href="home">
                            <img src="./image/logo_az.jpg" alt="" style="height: 89px; width: 150px">
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
                                            <a href="<c:url value='/user/profile' />" class="dropdown-item">Thông tin</a>
                                            <a href="orderuser" class="dropdown-item">Đơn mua</a>
                                            <a href="logout" class="dropdown-item">Đăng xuất</a>
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
                        <c:forEach begin="2" end="10" var="index">
                            <div class="carousel-item">
                                <img src="./image/quangcao_${index}.jpg" class="d-block w-100" alt="...">
                            </div>                            
                        </c:forEach>
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
                    <!--26/11/2023-->
                    <c:forEach items="${requestScope.listCategory}" var="c">
                        <div class="card">
                            <c:set var="tenDanhMuc" value="${c.tenDanhMuc}" />
                            <c:set var="tenDanhMuc" value="${tenDanhMuc.trim().toLowerCase()}" />
                            <a href="category?cid=${c.cid}"><img src="./image/${tenDanhMuc}.jpg" alt=""></a>
                        </div>                        
                    </c:forEach>
                </div>             

            </div>
        </section>



        <section class="item">
            <div class="container">
                <div class="row" >
                    <h2>Sản phẩm mới</h2>
                </div>
                <div class="row"  style="margin-left: -30px; margin-right: -30px">
                    <c:if test="${sessionScope.create == null && sessionScope.edit == null}">
                        <c:forEach items="${requestScope.listItemsPerPage}" var="p" varStatus="loop">
                            <div class="col-lg-3 col-md-4 col-sm-6 col-12 mb-4">
                                <div class="card h-100">
                                    <div class="card-img-top card_top">
                                        <a href="productdetail?id=${p.id}">
                                            <img src="./image/${p.anhDaiDien}" class="img-fluid image_product" alt="${p.tenSanPham}">

                                        </a>
                                    </div>
                                    <div class="card-body d-flex flex-column justify-content-between">
                                        <!--Phần đầu của card-body--> 
                                        <div class="card-info-top">
                                            <a href="productdetail?id=${p.id}">
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
                    </c:if>
                </div>
                <div class="row">
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
