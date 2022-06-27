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
              <h4 class="mb-0 font-size-18">Create User</h4>

              <div class="page-title-right">
                <ol class="breadcrumb m-0">
                  <li class="breadcrumb-item"><a href="javascript: void(0);">Create User</a></li>
                  <li class="breadcrumb-item active">Create User</li>
                </ol>
              </div>

            </div>
          </div>
        </div>
        <!-- end page title -->
        <div>
          <form method="post">
            <div class="mb-3">
              <label for="fullName" class="form-label">Full name</label>
              <input type="text" class="form-control" id="fullName" name="fullName">
            </div>
            <div class="mb-3">
              <label for="age" class="form-label">Age</label>
              <input type="number" required class="form-control" id="age" name="age">
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input type="text" class="form-control" id="email" name="email">
            </div>
            <div class="mb-3">
              <label for="mobile" class="form-label">Mobile</label>
              <input type="tel" class="form-control" id="mobile" name="mobile">
            </div>
            <div class="mb-3">
              <label for="address" class="form-label">Address</label>
              <input type="text" class="form-control" id="address" name="address">
            </div>
            <button type="submit" class="btn btn-outline-primary">Create</button>
          </form>
        </div>
        <div>
          <c:if test="${requestScope['success'] == true}">
            <ul class="success">
              <li>Create success</li>
            </ul>
          </c:if>
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
