<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 4/22/16
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
  <title>Try v1.2 Bootstrap Online</title>
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <script src="bootstrap/js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>


<!-- Top content -->
<div class="top-content">

  <div class="container center">
    <div class="row">
      <h1><strong> Online Translater</strong> &amp; User Management</h1>
      <jsp:include page="header.jsp">
        <jsp:param name="title" value="My website"/>
      </jsp:include>


    </div>

    <div class="row">

      <ul class="nav nav-tabs">
        <li class="active"><a href="#mydropdown1" data-toggle="tab"><strong>Online Translater</strong></a></li>

        <li class="dropdown">
          <a href="#" data-toggle="dropdown" class="dropdown-toggle"><strong>User Management</strong><span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a data-toggle="tab" href="#dropdown1">Add User</a></li>
            <li><a data-toggle="tab" href="#dropdown2">View User</a></li>
          </ul>
        </li>
      </ul>
      <div class="tab-content">
        <div id="mydropdown1" class="tab-pane fade in active">
          <h3><strong>Translate Your Text</strong></h3>
            <jsp:include page="translater.jsp">
              <jsp:param name="title" value="My translater"/>
            </jsp:include>

        </div>

        <div id="dropdown1" class="tab-pane fade">
          <h3><strong>Add a New User</strong></h3>
          <jsp:include page="adduser.jsp">
            <jsp:param name="title" value="Add User"/>
          </jsp:include>

        </div>
        <div id="dropdown2" class="tab-pane fade">
          <h3><strong>View All Users</strong></h3>
          <jsp:include page="view_users.jsp">
            <jsp:param name="title" value="Add User"/>
          </jsp:include>
        </div>


      </div>


    </div>
  </div>
</div>

</body>

</html>
