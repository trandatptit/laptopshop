<%-- 
    Document   : quanLySanPham
    Created on : Oct 9, 2023, 10:30:51 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.NguoiDung" %>
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

        <title>Quản lý sản phẩm</title>
        <script src="../ckeditor/ckeditor.js"></script>
        <style>
            /* CSS cho hình ảnh xem trước */
            .preview-image {
                width: 200px; /* Điều chỉnh kích thước theo ý muốn */
                height: 200px;
                object-fit: contain; /* Để hình ảnh vừa khuôn */
            }

            th{
                vertical-align: middle;
            }
        </style>

    </head>
    <body>
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
                            String managerProduct = (String)request.getAttribute("managerProduct");
                            String activeProduct = "";
                            if(managerProduct != null){
                                activeProduct = "active";
                            }
                        %>
                        <a href="<c:url value='/admin' />" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>Dashboard</a>                        
                        <a href="<c:url value='/admin/don-hang' />" class="nav-item nav-link"><i class="fa fa-th me-2"></i>Quản lý đơn hàng</a>
                        <a href='<c:url value="/admin/san-pham"/>' class="nav-item nav-link <%= activeProduct %>"><i class="fa fa-keyboard me-2"></i>Quản lý sản phẩm</a>
                        <a href='<c:url value="/admin/danh-muc"/>' class="nav-item nav-link"><i class="fa fa-table me-2"></i>Quản lý danh mục</a>
                        <a href="<c:url value='/admin/tai-khoan' />" class="nav-item nav-link"><i class="fa fa-chart-bar me-2"></i>Quản lý tài khoản</a>                        
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
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><%                                    
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
                    <c:if test="${sessionScope.create == null && sessionScope.edit == null}">
                        <h3>Quản lý sản phẩm</h3>
                        <div class="d-flex align-items-center justify-content-between">
                            <a class="btn btn-primary" href="san-pham?action=create">Thêm mới sản phẩm</a>                            

                            <form action="san-pham?action=search" method="post" class="input-group justify-content-end" style="width: 40%;">
                                <input type="text" name="text" value="${requestScope.text}" class="form-control" placeholder="Tìm theo ten san pham">
                                <div class="input-group-append">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </form>
                        </div>

                        <hr/>
                    </c:if>

                    <!--form create san pham-->
                    <c:if test="${sessionScope.create != null}">
                        <h3 class="mb-4">Create Sản phẩm</h3>
                        <form action="san-pham?action=saveCreate" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="tenSanPham" class="form-label">Tên Sản Phẩm</label>
                                <input type="text" class="form-control" id="tenSanPham" name="tenSanPham" required>
                            </div>

                            <div class="mb-3">
                                <label for="moTaNgan" class="form-label">Mô tả ngắn</label>
                                <textarea class="form-control" id="moTaNgan" name="moTaNgan" rows="4"></textarea>
                            </div>

                            <div class="mb-3">
                                <label for="moTaChiTiet" class="form-label">Mô tả chi tiết</label>
                                <textarea class="form-control" id="moTaChiTiet" name="moTaChiTiet" rows="10"></textarea>
                            </div>

                            <div class="row mb-3">
                                <div class="col">
                                    <label for="giaBan" class="form-label">Giá Bán</label>
                                    <input type="number" class="form-control" id="giaBan" name="giaBan" required>
                                </div>
                                <div class="col">
                                    <label for="soLuong" class="form-label">Số Lượng</label>
                                    <input type="number" class="form-control" id="soLuong" name="soLuong" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="anhDaiDien" class="form-label">Ảnh Đại Diện</label>
                                <input type="file" class="form-control" id="imageInput" name="anhDaiDien" accept="image/*">
                                <div id="imagePreview" ></div>
                            </div>

                            <div class="mb-3">
                                <label for="anhSanPham" class="form-label">Ảnh Sản Phẩm</label>
                                <input type="file" class="form-control" id="imageInputs" name="anhSanPham" accept="image/*" multiple>
                                <div id="imagePreviews" class="d-flex flex-wrap mt-4 gap-4"></div>
                            </div>

                            <div class="mb-3">
                                <label for="category" class="form-label">Danh Mục</label>
                                <select class="form-select" id="category" name="category">
                                    <c:forEach items="${requestScope.listDanhMuc}" var="o">
                                        <option value="${o.cid}">${o.tenDanhMuc}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="cpu" class="form-label">CPU</label>
                                <input type="text" class="form-control" id="cpu" name="cpu" required>
                            </div>

                            <div class="row mb-3">
                                <div class="col">
                                    <label for="ram" class="form-label">RAM</label>
                                    <input type="text" class="form-control" id="ram" name="ram" required>
                                </div>
                                <div class="col">
                                    <label for="ssd" class="form-label">SSD</label>
                                    <input type="text" class="form-control" id="ssd" name="ssd" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col">
                                    <label for="card" class="form-label">Card</label>
                                    <input type="text" class="form-control" id="card" name="card" required>
                                </div>
                                <div class="col">
                                    <label for="congKetNoi" class="form-label">Cổng Kết Nối</label>
                                    <input type="text" class="form-control" id="congKetNoi" name="congKetNoi" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="manHinh" class="form-label">Màn Hình</label>
                                <input type="text" class="form-control" id="manHinh" name="manHinh" required>
                            </div>

                            <div class="mb-3">
                                <label for="heDieuHanh" class="form-label">Hệ Điều Hành</label>
                                <input type="text" class="form-control" id="heDieuHanh" name="heDieuHanh" required>
                            </div>

                            <div class="mb-3">
                                <label for="thoiDiemRaMat" class="form-label">Thời Điểm Ra Mắt</label>
                                <input type="number" class="form-control" id="thoiDiemRaMat" name="thoiDiemRaMat" required>
                            </div>

                            <button type="submit" class="btn btn-success">Save</button>
                            <a class="btn btn-secondary m-4" href="san-pham">
                                <i class="ri-arrow-left-s-line"></i> Back
                            </a>
                        </form>

                    </c:if>

                    <!--form edit san pham-->    
                    <c:if test="${sessionScope.edit != null}">
                        <h3 class="mb-4">Edit sản phẩm</h3>
                        <form action="san-pham?action=saveEdit" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${requestScope.sanPham.id}">

                            <div class="mb-3">
                                <label for="tenSanPham" class="form-label">Tên Sản Phẩm</label>
                                <input type="text" class="form-control" id="tenSanPham" name="tenSanPham" value="${requestScope.sanPham.tenSanPham}" required>
                            </div>

                            <div class="mb-3">
                                <label for="moTaNgan" class="form-label">Mô tả ngắn</label>
                                <textarea class="form-control" id="moTaNgan" name="moTaNgan" rows="4">${requestScope.sanPham.moTaNgan}</textarea>
                            </div>

                            <div class="mb-3">
                                <label for="moTaChiTiet" class="form-label">Mô tả chi tiết</label>
                                <textarea class="form-control" id="editor1" name="moTaChiTiet" rows="10">${requestScope.sanPham.moTaChiTiet}</textarea>
                                <!-- CKEditor code can be added here if needed -->
                            </div>

                            <div class="row mb-3">
                                <div class="col">
                                    <label for="giaBan" class="form-label">Giá Bán</label>
                                    <input type="number" class="form-control" value="${requestScope.sanPham.giaBan}" id="giaBan" name="giaBan" required>
                                </div>
                                <div class="col">
                                    <label for="soLuong" class="form-label">Số Lượng</label>
                                    <input type="number" class="form-control" value="${requestScope.sanPham.soLuong}" id="soLuong" name="soLuong" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="anhDaiDien" class="form-label">Ảnh Đại Diện</label>
                                <input type="file" class="form-control" id="imageInput" name="anhDaiDien" accept="image/*">
                                <div id="imagePreview" class="preview-image mt-4">
                                    <img src="../image/${requestScope.sanPham.anhDaiDien}" class="img-thumbnail" style="width: 200px; height: 200px; object-fit: contain" />
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="anhSanPham" class="form-label">Ảnh Sản Phẩm</label>
                                <input type="hidden" id="arrayListInput" name="arrayListInput">
                                <input type="file" class="form-control" id="imageInputs" name="anhSanPham" accept="image/*" multiple>
                                <div id="imagePreviews" class="d-flex flex-wrap mt-4 gap-4">
                                    <c:forEach items="${requestScope.listImageProduct}" var="i">
                                        <div class="d-flex flex-column gap-4"> 
                                            <img src="../image/${i.url}" class="img-thumbnail" style="width: 200px; height: 200px; object-fit: contain" /> 
                                            <button class="btn btn-danger delete">Xóa</button>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!--                                <script>                                     
                                                                    let test = document.getElementById("imagePreviews");
                                                                    let img = test.querySelectorAll("img");
                                                                    console.log(img);
                                                                    
                                                                </script>-->

                            </div>


                            <div class="mb-3">
                                <label for="category" class="form-label">Category</label>
                                <select class="form-select" id="category" name="category">
                                    <c:forEach items="${requestScope.listDanhMuc}" var="o">
                                        <c:choose>
                                            <c:when test="${o.cid == requestScope.sanPham.danhMuc.cid}">
                                                <option value="${o.cid}" selected="selected">${o.tenDanhMuc}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${o.cid}">${o.tenDanhMuc}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="cpu" class="form-label">CPU</label>
                                <input type="text" class="form-control" id="cpu" name="cpu" value="${requestScope.thongSo.cpu}" required>
                            </div>

                            <div class="row mb-3">
                                <div class="col">
                                    <label for="ram" class="form-label">RAM</label>
                                    <input type="text" class="form-control" id="ram" name="ram" value="${requestScope.thongSo.ram}" required>
                                </div>
                                <div class="col">
                                    <label for="ssd" class="form-label">SSD</label>
                                    <input type="text" class="form-control" id="ssd" name="ssd" value="${requestScope.thongSo.ssd}" required>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col">
                                    <label for="card" class="form-label">Card</label>
                                    <input type="text" class="form-control" id="card" name="card" value="${requestScope.thongSo.card}" required>
                                </div>
                                <div class="col">
                                    <label for="congKetNoi" class="form-label">Cổng Kết Nối</label>
                                    <input type="text" class="form-control" id="congKetNoi" name="congKetNoi" value="${requestScope.thongSo.congKetNoi}" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="manHinh" class="form-label">Màn Hình</label>
                                <input type="text" class="form-control" id="manHinh" name="manHinh" value="${requestScope.thongSo.manHinh}" required>
                            </div>

                            <div class="mb-3">
                                <label for="heDieuHanh" class="form-label">Hệ Điều Hành</label>
                                <input type="text" class="form-control" id="heDieuHanh" name="heDieuHanh" value="${requestScope.thongSo.heDieuHanh}" required>
                            </div>

                            <div class="mb-3">
                                <label for="thoiDiemRaMat" class="form-label">Thời Điểm Ra Mắt</label>
                                <input type="number" class="form-control" id="thoiDiemRaMat" name="thoiDiemRaMat" value="${requestScope.thongSo.thoiDiemRaMat}" required>
                            </div>

                            <button type="submit" class="btn btn-success">Save</button>
                            <a class="btn btn-secondary m-4" href="san-pham">
                                <i class="ri-arrow-left-s-line"></i> Back
                            </a>
                        </form>
                    </c:if>


                    <!--hien thi danh sach san pham-->
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
                                    <c:forEach items="${requestScope.listItemsPerPage}" var="p">
                                        <tr>
                                            <td>
                                                <img src="../image/${p.anhDaiDien}" alt="anh" width="100px" height="100px" style="object-fit: contain" />
                                            </td>
                                            <td>${p.tenSanPham}</td>
                                            <td>${p.danhMuc.tenDanhMuc}</td>
                                            <td>${p.giaBan}</td>
                                            <td>${p.soLuong}</td>
                                            <td>
                                                <a href="san-pham?id=${p.id}&action=edit" class="btn btn-primary">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                                <a href="san-pham?id=${p.id}&action=delete" class="btn btn-danger">
                                                    <i class="fas fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <nav aria-label="Page navigation">
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
                                        <li class="page-item">
                                            <c:choose>
                                                <c:when test="${page == currentPage}">
                                                    <span class="page-link">${page}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="page-link" href="?page=${page}">${page}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
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
                        </nav>
                    </c:if>

                </div>
                <!-- Sale & Revenue End -->

            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <script>
            const imageInput = document.getElementById('imageInput');
            const imagePreview = document.getElementById('imagePreview');
            imageInput.addEventListener('change', function () {
                const file = imageInput.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const img = new Image();
                        img.src = e.target.result;
                        img.classList.add('preview-image'); // Thêm lớp Bootstrap
                        imagePreview.innerHTML = '';
                        imagePreview.appendChild(img);
                    };
                    reader.readAsDataURL(file);
                } else {
                    imagePreview.innerHTML = 'Chưa chọn hình ảnh.';
                }
            });

            const imageInputs = document.getElementById('imageInputs');
            const imagePreviews = document.getElementById('imagePreviews');

            imageInputs.addEventListener('change', function () {
                // Xóa tất cả hình ảnh hiện tại
                imagePreviews.innerHTML = '';
                const files = imageInputs.files;
                const newFiles = Array.from(files); // Sao chép danh sách tệp

                for (let i = 0; i < files.length; i++) {
                    const file = files[i];
                    if (file) {
                        const reader = new FileReader();
                        reader.onload = function (e) {
                            const img = new Image();
                            img.src = e.target.result;
                            img.classList.add('img-thumbnail'); // Thêm lớp Bootstrap
                            img.style.width = "200px";
                            img.style.height = "200px";
                            img.style.objectFit = "contain";
                            // Tạo một nút để xóa hình ảnh
                            const deleteButton = document.createElement('button');
                            deleteButton.classList.add('btn', 'btn-danger', 'btn-sm');
                            deleteButton.textContent = 'Xóa';
                            // Hiển thị hình ảnh và nút xóa
                            const imageContainer = document.createElement('div');
                            imageContainer.classList.add('d-flex', 'flex-column', "gap-4");
                            imageContainer.appendChild(img);
                            imageContainer.appendChild(deleteButton);
                            imagePreviews.appendChild(imageContainer);
                            // Thêm sự kiện xóa cho nút xóa này
                            deleteButton.addEventListener('click', function () {
                                // Xác định vị trí của phần tử đang xử lý
                                const index = newFiles.indexOf(file);
                                if (index !== -1) {
                                    // Xóa phần tử khỏi mảng
                                    newFiles.splice(index, 1);
                                    // Tạo một đối tượng DataTransfer và gán danh sách tệp mới vào đó
                                    const dataTransfer = new DataTransfer();
                                    newFiles.forEach(function (file) {
                                        dataTransfer.items.add(file);
                                    });
                                    // Đặt lại giá trị của input với danh sách tệp mới
                                    imageInputs.value = null;
                                    imageInputs.files = dataTransfer.files;
                                }

                                // Xóa phần tử chứa hình ảnh và nút xóa
                                imageContainer.remove();
                            });
                        };
                        reader.readAsDataURL(file);
                    }
                }
            });

            const deleteButton = document.getElementsByClassName("delete");
            for (let i = 0; i < deleteButton.length; i++) {
                deleteButton[i].addEventListener("click", function () {
                    let parentElement = this.parentNode;
                    parentElement.remove();
                    console.log("xoa thanh cong" + i);
                });
            }            
            
        </script>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>


        <!-- Template Javascript -->
        <script src="../js/admin/main.js"></script>  
    </body>
</html>
