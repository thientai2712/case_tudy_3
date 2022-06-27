<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/25/2022
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>

    <script type="text/javascript">
        function validate()
        {
            var email=document.getElementById("email").value;
            var pass=document.getElementById("pass").value;

            if(email=="")
            {
                document.getElementById("emailerror").innerHTML="Please enter Email...";
                return false;
            }
            else
            {
                document.getElementById("emailerror").innerHTML="";
            }

            if(pass=="")
            {
                document.getElementById("passerror").innerHTML="Please enter password...";
                return false;
            }
            else
            {
                document.getElementById("passerror").innerHTML="";
            }

            if(pass.length<3 || pass.length>8)
            {
                document.getElementById("passerror").innerHTML="Password should be in betwwen 3  to 8 character..";
                return false;
            }
            else
            {
                document.getElementById("passerror").innerHTML="";
            }

            return true;
        }
    </script>
</head>
<body>
<div align="center" class="container">
    <div class="row-cols-1">
        <fieldset>

            <%
                String status =(String)request.getAttribute("status");
                if(status != null)
                {
                    out.println(status);
                }
            %>
            <legend><h1>Login</h1></legend>
            <form  onsubmit="return validate()"  method="post">
                <table>
                    <tr>
                        <td>Email : </td>
                        <td><input type="email" id="email"  name="emailid"></td>
                        <td><span style="color:red" id="emailerror" >*</span></td>
                    </tr>

                    <tr>
                        <td>Password : </td>
                        <td><input type="password" id="pass" name="password"></td>
                        <td><span style="color:red" id="passerror">*</span></td>
                    </tr>

                    <tr>
                        <td><input type="submit" value="login"></td>
                    </tr>
                </table>
            </form>
            <br>
            <hr>
            <a href="login.jsp">Sign Up</a>
        </fieldset>
    </div>
</div>
</body>
</html>
