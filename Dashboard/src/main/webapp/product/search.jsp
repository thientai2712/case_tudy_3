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
                    <div class="col-lg-6">
                        <h1>List Search</h1>
                    </div>
                </div>

                <div class="mb-3 col-lg-3">
                    <form>
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name">
                        <a href="/product?action=search&name=${item.getName()}">
                            <button type="submit" class="btn btn-outline-success">Search</button>
                         </a>
                    </form>
                </div>

                <div>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Full name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope['productList']}" var="item">
                            <tr>
                                <td>${item.getName()}</td>
                                <td>${item.getPrice()}</td>
                                <td>${item.getQuantity()}</td>
                                <td>${item.getDescription()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div >
                    <c:if test="${!requestScope['errors'].isEmpty()}">
                        <ul class="error">
                            <c:forEach items="${requestScope['errors']}" var="item">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </div>
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
