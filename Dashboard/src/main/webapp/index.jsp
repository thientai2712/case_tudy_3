<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/20/2022
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
          <h2>Demo JSP-Servlet Upload File</h2>
            <form method="post" action="UploadFileServlet" enctype="multipart/form-data">
              Select file to upload: <input type="file" name="file" size="60" /><br /><br />
              <input type="submit" value="Upload" />
            </form>
          <script>
            location.href = "/user?action=";
          </script>
  </body>
</html>
