<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<!--including JSTL to the page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    request.setAttribute("buffer",request.getSession().getAttribute("permissionList"));
  %>
  

  <!-- Nav bar declared -->
  <nav class="navbar navbar-default navbar-fixed-top ">
     <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="#"><fmt:message key="homepage.navbar.heading"/></a>
        </div>

          <ul class="nav navbar-nav nav-tabs">

            <%-- setting permission for translater tab --%>
            <c:forEach var="permission" items="${buffer}">
                    <c:choose>
                      <c:when test="${permission == 'Translate'}">
                        <li class="active"><a href="#mydropdown1" data-toggle="tab"><fmt:message key="homepage.navbar.nav1"/></a></li>
                      </c:when>
                    </c:choose>
            </c:forEach>


                <%-- setting permission for user add tab --%>
              <c:forEach var="permission" items="${buffer}">
                    <c:choose>
                      <c:when test="${permission == 'Add User'}">
                        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <fmt:message key="homepage.navbar.nav2"/><span class="caret"></span></a>

                                <ul class="dropdown-menu">

                                    <li><a data-toggle="tab" href="#dropdown1">
                                      <fmt:message key="homepage.navbar.nav2.tab1"/></a></li>

                                  </c:when>
                                  </c:choose>
                                  </c:forEach>


                                    <%-- setting permission for user view tab --%>
                                  <c:forEach var="permission" items="${buffer}">
                                    <c:choose>
                                      <c:when test="${permission == 'Add User' || 'Search User' || 'Edit User' ||
                                       'Delete User'}">

                                        <li><a data-toggle="tab" href="#dropdown2">
                                          <fmt:message key="homepage.navbar.nav2.tab2"/></a></li>
                                      </c:when>
                                    </c:choose>
                                  </c:forEach>
                                </ul>
                            </li>
                        </ul>

      <ul class="nav navbar-nav navbar-right">
        <li><a href="http://localhost:8080/LogoutServlet"><span class="glyphicon glyphicon-log-out"></span>
          <fmt:message key="homepage.navbar.logout"/></a></li>
      </ul>

     </div>

  </nav>

  <!-- Top content -->
  <div class="top-content">

    <div class="inner-bg">

      <div class="container center">

        <div class="row">
          <h1><strong><fmt:message key="homepage.heading.bold"/></strong><fmt:message key="homepage.heading.notbold"/></h1>

        </div>

      </div>


      <!-- Nav bar tabs content -->
      <div class="row">
        <div class="tab-content">
          <div id="mydropdown1" class="tab-pane fade in active">

            <c:forEach var="permission" items="${buffer}">
              <c:choose>
                <c:when test="${permission == 'Translate'}">
                  <h3><strong><fmt:message key="homepage.form.title"/></strong></h3>

                      <%--   <c:import var="data1" url="translater.jsp"/>
                            <c:out value="${data1}"/>
                      --%>

                      <jsp:include page="translater.jsp">
                          <jsp:param name="title" value="My translater"/>
                      </jsp:include>

                </c:when>
              </c:choose>
            </c:forEach>
          </div>


          <div id="dropdown1" class="tab-pane fade">
            <c:forEach var="permission" items="${buffer}">
              <c:choose>
                <c:when test="${permission == 'Add User'}">

                  <jsp:include page="adduser.jsp">
                      <jsp:param name="title" value="Add User"/>
                  </jsp:include>
                </c:when>
              </c:choose>
            </c:forEach>
          </div>


          <div id="dropdown2" class="tab-pane fade">
            <%--<h3><strong>View Users</strong></h3>--%>
              <c:forEach var="permission" items="${buffer}">
                <c:choose>
                  <c:when test="${permission == 'Add User' || 'Search User' || 'Edit User' || 'Delete User'}">

                    <jsp:include page="viewuser.jsp">
                        <jsp:param name="title" value="Add User"/>
                    </jsp:include>
                  </c:when>
                </c:choose>
              </c:forEach>
          </div>


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
