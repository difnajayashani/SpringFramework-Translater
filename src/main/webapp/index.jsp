<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>Login Page</title>

    <!-- CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/form-elements.css">


    <!-- Javascript -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>


</head>

<body >

<!-- Nav Bar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Online Translater Website</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">About Us</a></li>

        </ul>
    </div>
</nav>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">

        <div class="container center">
            <div class="row">
                <h1><strong> Online Translater </strong>  Home Page</h1>
                <jsp:include page="header.jsp">
                    <jsp:param name="title" value="My website"/>
                </jsp:include>


            </div>

            <div class="row">
                <center>
                    <div class="form-box">
                        <div class="form-top">

                            <div class="form-top-left">
                                <h3>Login to our site</h3>

                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-key"></i>
                            </div>
                        </div>

                        <div class="form-bottom">
                            <form class="form-horizontal" role="form" name="login"  method="post" action="MyServlet" >
                                <div class="form-group ">
                                    <label class="control-label col-sm-4" >Username:</label>

                                    <div class="col-sm-8"> <input type="text" name="username" placeholder="Username..." class="form-username form-control" id="form-username"></div>



                                </div>

                                <div class="form-group">

                                    <label class="control-label col-sm-4" >Password:</label>

                                    <div class="col-sm-8"> <input type="password" name="password" placeholder="Password..." class="form-password form-control" id="form-password"></div>


                                </div>


                                <div class="form-group ">

                                    <div class="col-sm-4"></div>
                                    <div class=" col-sm-4">
                                        <button type="submit" class="btn">Sign in!</button>

                                    </div>

                                    <div class="col-sm-4"></div>

                                </div>


                                <div class="form-group">

                                    <div class="col-sm-8">

                                        <p style="color:red">

                                            <%
                                                String s= (String)request.getAttribute("error");
                                                request.setAttribute("s",s);
                                            %>

                                           <c:set var="s" scope="request" value="${s}"/>
                                            <c:out value="${s}"/>


                                        </p>


                                    </div>

                                    <div class="col-sm-4"></div>


                                </div>


                            </form>
                        </div>

                    </div>
                </center>
            </div>

        </div>

    </div>
</div>


<!-- Footer -->
<footer>

    <jsp:include page="footer.jsp" />
</footer>
</body>

</html>