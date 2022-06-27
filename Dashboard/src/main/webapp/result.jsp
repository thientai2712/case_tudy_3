<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/23/2022
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
           <h2>${requestScope.message}</h2>
</body>
</html>
<div>
    <form method="post">
        <div class="mb-3">
            <label for="fullName" class="form-label">Product Name</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="${requestScope['product'].fullName}">
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Price</label>
            <input type="text" class="form-control" id="price" name="price" value="${requestScope['product'].price}">
        </div>
        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="text" class="form-control" id="quantity" name="quantity" value="${requestScope['product'].quantity}">
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">description</label>
            <input type="text" class="form-control" id="description" name="description" value="${requestScope['product'].description}">
        </div>
        <div class="mb-3">
            <label for="createdAt" class="form-label">createdAt</label>
            <input type="text" class="form-control" id="createdAt" name="createdAt" value="${requestScope['product'].createdAt}">
        </div>
        <div class="mb-3">
            <label for="updatedAt" class="form-label">Address</label>
            <input type="text" class="form-control" id="updatedAt" name="updatedAt" value="${requestScope['product'].updatedAt}">
        </div>
        <button type="submit" class="btn btn-outline-primary">Update</button>
    </form>
</div>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1>List of users</h1>
        </div>
        <div class="col-lg-6">
            <a href="/users?action=create">
                <button type="button" class="btn btn-outline-success">Create User</button>
            </a>
        </div>
    </div>

    <div class="mb-3 col-lg-3">
        <form>
            <input type="hidden" name="action" value="search">
            <label for="dob" class="form-label">DOB</label>
            <input type="date" class="form-control" id="dob" name="dob">
            <button type="submit" class="btn btn-outline-success">Search</button>
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
                    <td>
                        <a href="/product?action=search&name=${item.getName()}">
                            <button type="button" class="btn btn-outline-primary">Search</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="footer">
        <c:if test="${!requestScope['errors'].isEmpty()}">
            <ul class="error">
                <c:forEach items="${requestScope['errors']}" var="item">
                    <li>${item}</li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>