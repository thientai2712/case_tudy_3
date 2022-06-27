<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/21/2022
  Time: 8:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/layout/head.jsp" %>
</head>
<body>
<!-- Begin page -->
<div id="layout-wrapper">

    <%@ include file="/layout/navbar/header.jsp" %>
    <!-- ========== Left Sidebar Start ========== -->
    <div class="vertical-menu">

        <%@ include file="/layout/navbar/sidebar-menu.jsp" %>
    </div>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->
    <div class="main-content">

        <div class="page-content">
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box d-flex align-items-center justify-content-between">
                            <h4 class="mb-0 font-size-18">Welcome To Product</h4>

                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Products</a></li>
                                    <li class="breadcrumb-item active">Product list</li>
                                </ol>
                            </div>

                        </div>
                    </div>
                </div>
                <div>
                    <a href="/product?action=create">
                        <button class="btn btn-primary">
                            Create Product
                        </button>
                    </a>
                </div>
                <div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Product Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Description</th>
                            <th>CreatedAt</th>
                            <th>UpdatedAt</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope['productList']}" var="item">
                            <tr>
                                <td>${item.getId()}</td>
                                <td>${item.getName()}</td>
                                <td>${item.getPrice()}</td>
                                <td>${item.getQuantity()}</td>
                                <td>${item.getDescription()}</td>
                                <td>${item.getCreatedAt()}</td>
                                <td>${item.getUpdatedAt()}</td>
                                <td>
                                    <a href="/product?action=update&id=${item.getId()}">
                                        <button type="button" class="btn btn-primary">Edit</button>
                                    </a>
                                    <a href="/product?action=delete&id=${item.getId()}">
                                        <button type="button" class="btn btn-danger">Delete</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div>
                    <c:if test="${requestScope['success'] == true}">
                        <ul class="success">
                            <li>Delete sucsess</li>
                        </ul>
                    </c:if>
                </div>
                <!-- end page title -->
            </div>
            <!-- container-fluid -->
        </div>
        <!-- End Page-content -->

        <!-- Modal -->
        <!-- end modal -->

        <%@ include file="/layout/footer.jsp" %>
    </div>
    <!-- end main content-->

</div>
<!-- END layout-wrapper -->

<!-- Right Sidebar -->
<%@ include file="/layout/navbar/right_sidebar.jsp" %>
<!-- /Right-bar -->

<!-- Right bar overlay-->
<div class="rightbar-overlay"></div>

<%@ include file="/layout/script/script.jsp" %>
</body>
</html>
