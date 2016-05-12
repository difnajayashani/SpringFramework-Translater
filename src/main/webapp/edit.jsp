<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 4/22/16
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

  <title>Add New User</title>



  <!-- CSS -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
  <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
  <link href="css/style.css" rel="stylesheet">
  <link rel="stylesheet" href="css/form-elements.css">

  <!--javascript to populate the bootstrap table -->
    <!-- Javascript -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="js/scripts.js"></script>
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>

  <%----------------------------------------javascript functions---------------------------------------------------------------%>
  <script type="text/javascript">



    <!--javascript to check two passwords are equal -->
    function passwordsEqual(fld1,fld2) {
      var error2 = "";

      if (fld1.value == "") {
        fld1.style.background = 'Yellow';
        error2 = "You have to confirm the password.\n";
        alert(error2);
        return false;

      }

      else if ((fld1.value) != (fld2.value)) {
        error = "Two passwords have to be Equal. \n";
        fld1.style.background = 'Yellow';
        alert(error);
        return false;

      }  else {
        fld1.style.background = 'White';
      }
      return true;
    }


    $(document).ready(function(){
      $(".user_name").change(function(){
        var uname = $(this).val();
        if(uname.length >= 3){
          $(".status").html("<font color=gray> Checking availability...</font>");
          $.ajax({
            type: "POST",
            url: "CheckAvailability.java",
            data: "user_name="+ uname,
            success: function(msg){

              $(".status").ajaxComplete(function(event, request, settings){

                $(".status").html(msg);

              });
            }
          });
        }
        else{
            alert("Error in deleting !" );
          $(".status").html("<font color=red>Username should be <b>3</b> character long.</font>");
        }

      });
    });

    function validatePassword(){
        alert("Error in deleting !");
    }

    $(document).ready(function(){
      $("#uni").click(function(){
      var val = 'difna';

//        alert("Error in deleting !" +val);
          $.ajax({

              type:"POST",
              url:"DeleteUserServlet",
              data:{"val":val},
              success:function(msg){
                  alert(msg);
                  if(msg==1){
                      alert("Deleted user !");

                  }else{
                      alert("Error in deleting !");
                  }
              }
          })
        //$(".status").html("<font color=gray> Checking availability...</font>");
      });
    });


  </script>
  <!--------------------------------------------------------------------------------------------------------------------------------------->

</head>


<body>

<div class="form-group">
  <button type="button"  id="uni">Open Modal</button>
</div>


<div class="form-group">
  <center>
    <span class="status"></span>
  </center>
</div>

<div class="form-group">
    <input type="text" name="username" placeholder="Username..." class="user_name form-control" id="form-username" onchange="validatePassword()">


</div>



</body>

</html>