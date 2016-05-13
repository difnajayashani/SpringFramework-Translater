
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

  <!-- Nav bar declared -->
  <nav class="navbar navbar-default navbar-fixed-top ">
     <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#"><fmt:message key="homepage.navbar.heading"/></a>
      </div>

      <ul class="nav navbar-nav nav-tabs">
        <li class="active"><a href="#mydropdown1" data-toggle="tab"><fmt:message key="homepage.navbar.nav1"/></a></li>

        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><fmt:message key="homepage.navbar.nav2"/><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a data-toggle="tab" href="#dropdown1"><fmt:message key="homepage.navbar.nav2.tab1"/></a></li>
            <li><a data-toggle="tab" href="#dropdown2"><fmt:message key="homepage.navbar.nav2.tab2"/></a></li>

           </ul>
        </li>

      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> <fmt:message key="homepage.navbar.logout"/></a></li>
      </ul>

     </div>

  </nav>

  <!-- Top content -->
  <div class="top-content">

    <div class="inner-bg">

      <div class="container center">

        <div class="row">
          <h1><strong><fmt:message key="homepage.heading.bold"/></strong><fmt:message key="homepage.heading.notbold"/></h1>
         <%-- <jsp:include page="header.jsp">
            <jsp:param name="title" value="My website"/>
          </jsp:include>--%>

        </div>

      </div>

      <!-- Nav bar tabs content -->
      <div class="row">
        <div class="tab-content">
          <div id="mydropdown1" class="tab-pane fade in active">
            <h3><strong><fmt:message key="homepage.form.title"/></strong></h3>

              <jsp:include page="translater.jsp">
                <jsp:param name="title" value="My translater"/>
              </jsp:include>

          </div>

          <div id="dropdown1" class="tab-pane fade">
            <%--<h3><strong>Add a New User</strong></h3>--%>

              <jsp:include page="adduser.jsp">
                  <jsp:param name="title" value="Add User"/>
              </jsp:include>

          </div>

          <div id="dropdown2" class="tab-pane fade">
            <%--<h3><strong>View Users</strong></h3>--%>


              <jsp:include page="viewuser.jsp">
                  <jsp:param name="title" value="Add User"/>
              </jsp:include>
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
