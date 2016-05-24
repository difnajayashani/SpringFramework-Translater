<html xmlns:jsp="http://java.sun.com/JSP/Page">

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--including JSTL to the page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>

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




    <!--javascript to populate the bootstrap table -->
    <script src="bootstrap_table.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>



</head>

<body>
    <fmt:setLocale value="En"/>
    <fmt:bundle basename="labelNames">

        <%
            request.setAttribute("buffer",request.getSession().getAttribute(" permissionList"));

        %>




    <!-- Nav bar declared -->
    <nav class="navbar navbar-default navbar-fixed-top ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><fmt:message key="homepage.navbar.heading"/></a>
            </div>

            <!-- permission set for Translate permission-->
            <ul class="nav navbar-nav nav-tabs">
                <c:forEach var="permission" items="${buffer}">
                    <c:choose>
                        <c:when test="${permission == 'Translate'}">
                            <li class="active">
                                <a href="#mydropdown1" data-toggle="tab"><fmt:message key="homepage.navbar.nav1"/></a>
                            </li>
                        </c:when>
                    </c:choose>
                </c:forEach>

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="homepage.navbar.nav2"/>
                    </a>

                    <ul class="dropdown-menu">

                        <!--permission set for Add User permission -->
                        <c:forEach var="permission" items="${buffer}">
                            <c:choose>
                                <c:when test="${permission == 'Add User'}">
                                    <li ><a data-toggle="tab" href="#dropdown1">
                                        <fmt:message key="homepage.navbar.nav2.tab1"/></a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <!--permission set for Edit User and Delete User permission -->
                        <c:forEach var="permission" items="${buffer}">
                            <c:choose>
                                <c:when test="${(permission == 'Edit User' || 'Delete User')}">
                                    <li><a data-toggle="tab" href="#dropdown2">
                                        <fmt:message key="homepage.navbar.nav2.tab2"/></a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <c:forEach var="permission" items="${buffer}">
                            <c:choose>
                                <c:when test="${(permission != 'Edit User') && (permission != 'Add User') && (permission !=  'Delete User') && (permission != 'Translate') && (permission !=  'Login')  && (permission ==  'Search User') }">
                                    <li><a data-toggle="tab" href="#dropdown2">
                                        <fmt:message key="homepage.navbar.nav2.tab2"/></a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                    </ul>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:forEach var="permission" items="${buffer}">
                    <c:choose>
                         <c:when test="${permission ==  'Login'}">
                            <li><a href="http://localhost:8080/LogoutServlet" class="glyphicon glyphicon-log-out">
                                <%--<span class="glyphicon glyphicon-log-out"></span>--%>
                                <fmt:message key="homepage.navbar.logout"/></a>
                            </li>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </nav>

    <!-- Top content -->
    <div class="top-content">
        <div class="inner-bg">

            <div class="row">

            <c:forEach var="permission" items="${buffer}">

                    <%--<c:when test="${permission == 'Add User'}">--%>
                    <c:if test="${(permission !=  'Translate') && (permission ==  'Search User')  }">
                    <td>
                        <c:out value="${permission}" />
                    </td>

                    </c:if>

            </c:forEach>

            </div>

            <div class="row">

                <c:forEach var="permission" items="${buffer}">
                 <%--   <c:choose>
                        <c:when test="${permission ==  'Translate'}">--%>
                            <td>
                                <%--<c:out value="${permission}" />--%>
                            </td>

                 <%--       </c:when>
                    </c:choose>--%>
                </c:forEach>

            </div>


                <div class="row">
                    <h1><strong><fmt:message key="homepage.heading.bold"/></strong>
                    <fmt:message key="homepage.heading.notbold"/>
                    </h1>
                </div>



        <!-- Nav bar tabs content -->
        <div class="row">
            <div class="tab-content">

                <!--translater nav-bar -->

                    <c:forEach var="permission" items="${buffer}">
                        <c:choose>
                            <c:when test="${permission == 'Translate'}">
                                <div id="mydropdown1" class="tab-pane fade in active">
                                <h3><strong><fmt:message key="homepage.form.title"/></strong></h3>
                                    <jsp:include page="translater.jsp">
                                        <jsp:param name="title" value="My translater"/>
                                    </jsp:include>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>


                <!--add user drop down nav-bar -->

                    <c:forEach var="permission" items="${buffer}">
                        <c:choose>
                            <c:when test="${permission == 'Add User'}">
                                <div id="dropdown1" class="tab-pane fade in active">
                                    <jsp:include page="adduser.jsp">
                                        <jsp:param name="title2" value="Add User"/>
                                    </jsp:include>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>


                <!--view user drop down nav-bar -->
<%--                <c:forEach var="permission" items="${buffer}">
                    <c:choose>
                        <c:when test="${permission == 'Edit User' || permission == 'Delete User' }">--%>
                            <div id="dropdown2" class="tab-pane fade">
                                <jsp:include page="viewuser.jsp">
                                    <jsp:param name="title" value="View User"/>
                                </jsp:include>
                            </div>
                 <%--       </c:when>
                    </c:choose>
                </c:forEach>--%>

                </div>
            </div>
        </div>

    </div>

        <!-- Footer -->
        <footer>

            <jsp:include page="footer.jsp" />
        </footer>


    </fmt:bundle>
    </body>

</html>