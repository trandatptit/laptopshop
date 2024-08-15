<%-- 
    Document   : thanhtoan
    Created on : Nov 17, 2023, 1:29:07 PM
    Author     : trant
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi" class="h-100">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Thanh toán đơn hàng</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="./vendor/bootstrap/css/bootstrap.min.css" type="text/css">
        <!-- Font awesome -->
        <link rel="stylesheet" href="./vendor/font-awesome/css/font-awesome.min.css" type="text/css">

        <!-- Custom css - Các file css do chúng ta tự viết -->
        <link rel="stylesheet" href="./assets/css/app.css" type="text/css">
    </head>

    <body>

        <header class="mt-auto py-3">
            <div class="container">
                <p class="float-right">
                    <button><a href="show">Quay về giỏ hàng</a></button>
                </p>
            </div>
        </header>
        <main role="main">
            <!-- Block content - Đục lỗ trên giao diện bố cục chung, đặt tên là `content` -->
            <div class="container mt-4">
                <form action="thanhtoan" class="needs-validation" name="frmthanhtoan" method="post" >

                    <div class="py-5 text-center">
                        <i class="fa fa-credit-card fa-4x" aria-hidden="true"></i>
                        <h2>Thanh toán</h2>
                        <p class="lead">Vui lòng điền thông tin giao hàng trước khi Đặt hàng.</p>
                    </div>

                    <div class="row" style="justify-content: center">
                        <div class="col-md-8 order-md-1">
                            <h4 class="mb-3">Thông tin người nhận hàng</h4>

                            <div class="row">
                                <div class="col-md-12">
                                    <label for="kh_ten">Họ tên</label>
                                    <input type="text" required class="form-control" name="kh_ten" id="kh_ten"
                                           value="">
                                </div>

                                <div class="col-md-12">
                                    <label for="kh_diachi">Địa chỉ</label>
                                    <input type="text" required class="form-control" name="kh_diachi" id="kh_diachi"
                                           value="" >
                                </div>
                                <div class="col-md-12">
                                    <label for="kh_dienthoai">Điện thoại</label>
                                    <input type="text" required class="form-control" name="kh_dienthoai" id="kh_dienthoai"
                                           value="" >
                                </div>
                                <div class="col-md-12">
                                    <label for="kh_email">Email</label>
                                    <input type="text" required class="form-control" name="kh_email" id="kh_email"
                                           value="" >
                                </div>

                                <div class="col-md-12">
                                    <label for="kh_gioitinh">Ghi chú của bạn</label>
                                    <input type="text" class="form-control" name="kh_ghichu" id="kh_ghichu" >
                                </div>
                            </div>

                            <h4 class="mb-3">Hình thức thanh toán</h4>

                            <div class="d-block my-3">
                                <div class="custom-control custom-radio">
                                    <input id="httt-1" name="httt_ma" type="radio" class="custom-control-input" required=""
                                           value="1">
                                    <label class="custom-control-label" for="httt-1">Tiền mặt</label>
                                </div>
                                <div class="custom-control custom-radio">
                                    <input id="httt-2" name="httt_ma" type="radio" class="custom-control-input" required=""
                                           value="2">
                                    <label class="custom-control-label" for="httt-2">Chuyển khoản</label>
                                </div>

                            </div>
                            <hr class="mb-4">
                            <button class="btn btn-primary btn-lg btn-block" type="submit" name="btnDatHang">Đặt
                                hàng</button>
                        </div>
                    </div>
                </form>


            </div>
            <!-- End block content -->
        </main>
        <script src="./vendor/jquery/jquery.min.js"></script>
        <script src="./vendor/popperjs/popper.min.js"></script>
        <script src="./vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="./assets/js/app.js"></script>

    </body>

</html>