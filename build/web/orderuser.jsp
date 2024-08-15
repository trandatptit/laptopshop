<%-- 
    Document   : profile
    Created on : Oct 22, 2023, 5:23:55 PM
    Author     : PC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.NguoiDung" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        
        <%
            String status = (String) session.getAttribute("trangthai");

            if (status != null) {
                if (status.equals("4")) {
                    status = "Đã hủy thành công";
                %>
                <script>
                    swal("Congrats", "<%= status%>", "success");
                </script>
                <%
                } else if (status.equals("3")) {
                    status = "Cảm ơn bạn đã xác nhận";
                %>
                <script>
                    swal("Congrats", "<%= status%>", "success");
                </script>
                <%
                }
                session.removeAttribute("trangthai");
            }
        %>

        
        <div class="container light-style flex-grow-1 container-p-y">

            <h4 class="font-weight-bold py-2 mb-2">
                ĐƠN MUA
            </h4>

            <div class="card overflow-hidden">
                <div class="row no-gutters row-bordered row-border-light">
                    <div class="col-md-3 pt-0">
                        <div class="list-group list-group-flush account-settings-links">
                            <%
                                String dangxuly = (String) session.getAttribute("dangxuly");
                                String active_dangxuly = "";
                                if(dangxuly != null){
                                    active_dangxuly = "active";
                                }else{
                                    active_dangxuly = "";
                                }
                                String danggiaohang = (String) session.getAttribute("danggiaohang");
                                String active_danggiaohang = "";
                                if(danggiaohang != null){
                                    active_danggiaohang = "active";
                                }else{
                                    active_danggiaohang = "";
                                }
                                String hoanthanh = (String) session.getAttribute("hoanthanh");
                                String active_hoanthanh = "";
                                if(hoanthanh != null){
                                    active_hoanthanh = "active";
                                }else{
                                    active_hoanthanh = "";
                                }
                                String dahuy = (String) session.getAttribute("dahuy");
                                String active_dahuy = "";
                                if(dahuy != null){
                                    active_dahuy = "active";
                                }else{
                                    active_dahuy = "";
                                }
                            %>                            
                            <a class="list-group-item list-group-item-action <%= active_dangxuly %>" 
                               href="orderuser?action=dangxuly">Đơn đang xử lý</a>
                            <a class="list-group-item list-group-item-action <%= active_danggiaohang %>" 
                               href="orderuser?action=danggiaohang">Đơn đang giao</a>                        
                            <a class="list-group-item list-group-item-action <%= active_hoanthanh %>" 
                                href="orderuser?action=hoanthanh">Đơn đã giao</a>
                            <a class="list-group-item list-group-item-action <%= active_dahuy %>" 
                               href="orderuser?action=dahuy">Đơn đã hủy</a>
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
                        <div class="tab-content">   
                                    <div class="tab-pane fade active show" id="account-general">

                                        <div class="card-body media align-items-center">
                                            <%                                    
                                                if(avatar == null || avatar.isEmpty()){                                                                        
                                            %>
                                            <img class="rounded-circle me-lg-2"  id="avatar-image" src="./image/default_avatar.png" alt="" style="width: 80px; height: 80px;">
                                            <%
                                                }else{
                                            %>
                                            <img class="rounded-circle me-lg-2"  id="avatar-image" src="./image/<%= avatar %>" alt="" style="width: 80px; height: 80px;">
                                            <%
                                                }
                                            %>
                                            <div class="media-body ml-4">
                                                <label class="btn btn-outline-primary">
                                                    <%= ten %>
                                                </label> &nbsp;      
                                            </div>
                                        </div>
                                        <hr class="border-light m-0">
                                        
                                        <!--// action viet trong nay-->
                                        <div class="card-body">
                                            
                                            
                                            <!--//don dang xu ly-->
                                            
                                                <c:if test="${sessionScope.dangxuly != null}">
                                                    <table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>stt</th>
                                                                <th>đơn</th>
                                                                <th>hủy</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                int cnt = 1;
                                                            %>
                                                            <c:forEach items="${requestScope.dangxuly}" var="u">
                                                                <tr>
                                                                    <td>
                                                                        <%= cnt++ %>
                                                                    </td>
                                                                    <td>
                                                                        <c:forEach items="${requestScope.hashxuly}" var="h">
                                                                            <c:if test="${u.id == h.key}">                                    
                                                                                <table class="table table-bordered">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>Tên sản phẩm</th>
                                                                                            <th>số lượng</th>
                                                                                            <th>Thành tiền</th>
                                                                                        </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                        <c:forEach items="${h.value}" var="o">
                                                                                            <tr>
                                                                                                <td>${o.sanPham.tenSanPham}</td>
                                                                                                <td>
                                                                                                    <fmt:formatNumber value="${o.dongia}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                    <h6><c:out value="${o.soluong} x ${formattedPrice}VNĐ" /></h6>
                                                                                                </td>
                                                                                                <td>
                                                                                                    <fmt:formatNumber value="${o.dongia*o.soluong}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                    <h6><c:out value="${formattedPrice}VNĐ" /></h6>
                                                                                                </td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </td>
                                                                    <td>
                                                                        <form action="orderuser?id=${u.id}&action=updateStatus" method="post" >
                                                                                <input type="hidden" name="trangthai" value="4" /> 
                                                                                <button type="submit" class="btn btn-danger"><i class="fas fa-trash"></i></button>
                                                                        </form>
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                           
                                                
                                                <!--//đơn đang giao-->
                                            
                                                <c:if test="${sessionScope.danggiaohang != null}">
                                                    <table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>stt</th>
                                                                <th>đơn</th>
                                                                <th>Xác nhận</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                int cnt = 1;
                                                            %>
                                                            <c:forEach items="${requestScope.danggiaohang}" var="u">
                                                                <tr>
                                                                    <td>
                                                                        <%= cnt++ %>
                                                                    </td>
                                                                    <td>
                                                                    <c:forEach items="${requestScope.hashgiaohang}" var="h">
                                                                        <c:if test="${u.id == h.key}">                                    
                                                                            <table class="table table-bordered">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Tên sản phẩm</th>
                                                                                        <th>số lượng</th>
                                                                                        <th>Thành tiền</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach items="${h.value}" var="o">
                                                                                        <tr>
                                                                                            <td>${o.sanPham.tenSanPham}</td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.dongia}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                <h6><c:out value="${o.soluong} x ${formattedPrice}VNĐ" /></h6>
                                                                                            </td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.dongia*o.soluong}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                <h6><c:out value="${formattedPrice}VNĐ" /></h6>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    </td>
                                                                    <td>
                                                                        <form action="orderuser?id=${u.id}&action=updateStatus" method="post" >
                                                                                <input type="hidden" name="trangthai" value="3" /> 
                                                                                <button type="submit" class="btn btn-primary">Đã nhận hàng</button>
                                                                        </form>
                                                                        
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                            
                                                
                                                <!--đơn hành thành-->
                                            
                                                <c:if test="${sessionScope.hoanthanh != null}">
                                                    <table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>stt</th>
                                                                <th>đơn</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                int cnt = 1;
                                                            %>
                                                            <c:forEach items="${requestScope.hoanthanh}" var="u">
                                                                <tr>
                                                                    <td>
                                                                        <%= cnt++ %>
                                                                    </td>
                                                                    <td>
                                                                    <c:forEach items="${requestScope.hashhoanthanh}" var="h">
                                                                        <c:if test="${u.id == h.key}">                                    
                                                                            <table class="table table-bordered">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Tên sản phẩm</th>
                                                                                        <th>số lượng</th>
                                                                                        <th>Thành tiền</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach items="${h.value}" var="o">
                                                                                        <tr>
                                                                                            <td>${o.sanPham.tenSanPham}</td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.dongia}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                <h6><c:out value="${o.soluong} x ${formattedPrice}VNĐ" /></h6>
                                                                                            </td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.dongia*o.soluong}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                <h6><c:out value="${formattedPrice}VNĐ" /></h6>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                            
                                                
                                               <!--đơn đã hủy-->
                                            
                                                <c:if test="${sessionScope.dahuy != null}">
                                                    <table class="table table-bordered">
                                                        <thead>
                                                            <tr>
                                                                <th>stt</th>
                                                                <th>đơn</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                int cnt = 1;
                                                            %>
                                                            <c:forEach items="${requestScope.dahuy}" var="u">
                                                                <tr>
                                                                    <td>
                                                                        <%= cnt++ %>
                                                                    </td>
                                                                    <td>
                                                                    <c:forEach items="${requestScope.hashdahuy}" var="h">
                                                                        <c:if test="${u.id == h.key}">                                    
                                                                            <table class="table table-bordered">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Tên sản phẩm</th>
                                                                                        <th>số lượng</th>
                                                                                        <th>Thành tiền</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach items="${h.value}" var="o">
                                                                                        <tr>
                                                                                            <td>${o.sanPham.tenSanPham}</td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.dongia}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                <h6><c:out value="${o.soluong} x ${formattedPrice}VNĐ" /></h6>
                                                                                            </td>
                                                                                            <td>
                                                                                                <fmt:formatNumber value="${o.dongia*o.soluong}" type="currency" currencyCode="VND" var="formattedPrice" pattern="#,##0" />
                                                                                                <h6><c:out value="${formattedPrice}VNĐ" /></h6>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </c:if>
                                           
                                        </div>

                                    </div>
                                                                                                
                                    <div class="text-right" style="padding: 0 1.25rem 1.25rem 1.25rem">
                                        <button type="button" class="btn btn-default">
                                            <%
                                                if(role != "" && role.equals("admin")){                                                                                        
                                            %>
                                            <a href="<c:url value='/admin' />">
                                                Cancel
                                            </a>
                                            <%
                                                } else {
                                            %>
                                            <a href="<c:url value='/home' />">
                                                Cancel
                                            </a>
                                            <%
                                                }
                                            %>
                                        </button>
                                    </div>
                                </div>
                        <form action="<c:url value='/user/profile?action=change_info' />" method="post" enctype="multipart/form-data">
                                
                        </form>
                        <c:if test="${sessionScope.changeInfo != null}">                                    
                            
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
