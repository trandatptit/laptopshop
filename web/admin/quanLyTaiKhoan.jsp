<%-- 
    Document   : quanLyTaiKhoan
    Created on : Oct 31, 2023, 10:06:21 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.NguoiDung" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">      
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <!-- Customized Bootstrap Stylesheet -->
        <link href="../css/admin/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="../css/admin/style.css" rel="stylesheet">

        <title>Quan ly tai khoan</title>
    </head>
    <body> 

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
        <div class="container-fluid position-relative bg-white d-flex p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <%
                NguoiDung nguoiDung = (NguoiDung)session.getAttribute("acc");
                String ten = nguoiDung.getTen();
                String avatar = nguoiDung.getAvatar();                                                                                      
            %>

            <!-- Sidebar Start -->
            <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-light navbar-light">                    
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <div class="position-relative">
                            <%                                    
                                if(avatar == null || avatar.isEmpty()){                                                                        
                            %>

                            <img class="rounded-circle me-lg-2" src="../image/default_avatar.png" alt="" style="width: 40px; height: 40px;">
                            <%
                                }else{
                            %>
                            <img class="rounded-circle me-lg-2" src="../image/<%= avatar %>" alt="" style="width: 40px; height: 40px;">
                            <%
                                }
                            %>
                            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                        </div>
                        <div class="ms-3">
                            <h6 class="mb-0"><%= ten %></h6>
                            <span>Admin</span>
                        </div>
                    </div>
                    <div class="navbar-nav w-100">
                        <%
                            String managerAccount = (String)request.getAttribute("managerAccount");
                            String activeManagerAccount = "";
                            if(managerAccount != null){
                                activeManagerAccount = "active";
                            }                                                     
                        %>                        
                        <a href="<c:url value='/admin' />" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>Dashboard</a>                        
                        <a href="<c:url value='/admin/don-hang' />" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Quản lý đơn hàng</a>
                        <a href='<c:url value="/admin/san-pham"/>' class="nav-item nav-link"><i class="fa fa-keyboard me-2"></i>Quản lý sản phẩm</a>
                        <a href='<c:url value="/admin/danh-muc"/>' class="nav-item nav-link"><i class="fa fa-table me-2"></i>Quản lý danh mục</a>
                        <a href="<c:url value='/admin/tai-khoan' />" class="nav-item nav-link <%= activeManagerAccount %>"><i class="fa fa-chart-bar me-2"></i>Quản lý tài khoản</a>                        
                        <a href="<c:url value='/admin/thong-ke' />" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Thống kê</a>                        
                    </div>
                </nav>
            </div>
            <!-- Sidebar End -->

            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">

                    <a href="#" class="sidebar-toggler flex-shrink-0">
                        <i class="fa fa-bars"></i>
                    </a>
                    <form class="d-none d-md-flex ms-4">
                        <input class="form-control border-0" type="search" placeholder="Search">
                    </form>
                    <div class="navbar-nav align-items-center ms-auto">                        
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <%                                    
                                    if(avatar == null || avatar.isEmpty()){                                                                        
                                %>

                                <img class="rounded-circle me-lg-2" src="../image/default_avatar.png" alt="" style="width: 40px; height: 40px;">
                                <%
                                    }else{
                                %>
                                <img class="rounded-circle me-lg-2" src="../image/<%= avatar %>" alt="" style="width: 40px; height: 40px;">
                                <%
                                    }
                                %>
                                <span class="d-none d-lg-inline-flex"><%= ten %></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                                <a href="<c:url value='/user/profile' />" class="dropdown-item">My Profile</a>
                                <a href="#" class="dropdown-item">Settings</a>
                                <a href="../logout" class="dropdown-item">Log Out</a>
                            </div>
                        </div>
                    </div>
                </nav>
                <!-- Navbar End -->


                <!-- Sale & Revenue Start -->
                <div class="container-fluid pt-4 px-4">
                    <h3>Quản lý tài khoản</h3>
                    <div>
                        <form action="tai-khoan?action=search" method="post" class="input-group mb-4 justify-content-end">
                            <div style="width: 40%">
                                <input type="text" name="text" value="${requestScope.text}" class="form-control" placeholder="Tìm tên hoặc email người dùng">
                            </div>
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-primary" type="button">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>                                

                        </form>


                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Email</th>
                                        <th>Name</th>
                                        <th>Role</th>
                                        <th>Phone</th>                                    
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int cnt = 1;
                                    %>
                                    <c:forEach items="${requestScope.listItemsPerPage}" var="u">
                                        <tr>
                                            <td>
                                                <%= cnt++ %>
                                            </td>
                                            <td>${u.email}</td>
                                            <td>${u.ten}</td>
                                            <td>
                                                Khach Hang                                                
                                            </td>
                                            <td>${u.sdt}</td>                                        
                                            <td>                                                
                                                <a href="tai-khoan?id=${u.id}&action=delete" class="btn btn-danger">
                                                    <i class="fas fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
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
                <!-- Sale & Revenue End -->

            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


        <!-- Template Javascript -->
        <script src="../js/admin/main.js"></script>        
    </body>
</html>
