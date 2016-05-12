<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 5/2/16
  Time: 7:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search User</title>

  <!-- CSS -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
  <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
  <link href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="css/form-elements.css">

</head>
<body>



  <div class="container-fluid">
    <center>
      <div class="jumbotron">

        <div class="form-group ">

          <form class="form-horizontal" role="form" method="post" action="SearchUserServlet" >

              <label class="control-label col-md-4">Search User</label>
              <div class="col-md-6">
                <input type="text" name="user-search-name" placeholder="Name of the User..." class="user-search--name form-control">
              </div>

              <button type="button" class="btn btn-sm btn-primary btn-create col-md-2" >Search</button>



          </form>


          </div>

        </div>
      </center>
    </div>



</body>
</html>
