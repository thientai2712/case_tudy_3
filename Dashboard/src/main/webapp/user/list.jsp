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
                            <h4 class="mb-0 font-size-18">Welcome To User</h4>

                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">User List</a></li>
                                    <li class="breadcrumb-item active">User List</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <a href="/user?action=create">
                        <button class="btn btn-primary">
                            Create User
                        </button>
                    </a>
                </div>
                <div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Age</th>
                            <th>Email</th>
                            <th>Mobile</th>
                            <th>Address</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope['users']}" var="item">
                            <tr>
                                <td>${item.getId()}</td>
                                <td>${item.getFullName()}</td>
                                <td>${item.getAge()}</td>
                                <td>${item.getEmail()}</td>
                                <td>${item.getMobile()}</td>
                                <td>${item.getAddress()}</td>
                                <td>
                                    <a href="/user?action=update&id=${item.getId()}">
                                        <button type="button" class="btn btn-primary">Edit</button>
                                    </a>
                                    <a href="/user?action=delete&id=${item.getId()}">
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
                            <li>Delete success</li>
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
