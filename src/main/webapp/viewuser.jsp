<html xmlns:jsp="http://java.sun.com/JSP/Page">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

  <title>Online Translater Home </title>

    <!-- CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/form-elements.css">



</head>

<body>

<%--<div class="container">--%>

<%--    <div class="row">--%>
        <div class="col-sm-10 col-sm-offset-1 ">
            <div class="panel panel-default panel-table">

             <%--panel heading --%>

            <div class="panel-heading">


                <div class="row">
                    <div class="col col-xs-3"></div>
                    <div class="col col-xs-6">
                        <h2 class="panel-title"><center><strong>Registered Users</strong></center></h2>
                    </div>
                    <div class="col col-xs-3">
                        <input type="text" id="user-search" name="user-search" placeholder="Search User..."
                               class="user-search form-control" >
                    </div>
                    <div class="col col-xs-1">
                        <button id="searchbtn" class="btn btn-sm btn-primary "><i class=" fa fa-search w3-xxxlarge"></i></button>

                    </div>
                </div>
            </div>


            <div  id="toolbar">
                <button id="remove1" class="btn btn-danger" disabled>
                    <i class="glyphicon glyphicon-remove"></i> Delete
                </button>

                <a href="adduser.jsp">
                    <button type="button" class="btn btn-sm btn-primary btn-create" >Add New</button>
                </a>




            </div>

            <%--modelto appear when removing a user --%>
            <!-- Modal -->
                 <div class="modal fade in" id="myModal1" role="dialog">
                     <div class="modal-dialog">

                    <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h3 class="modal-title">Delete</h3>
                            </div>
                        <div class="modal-body">
                           Delete the user <label id="lblUname"></label>
                        </div>
                        <div class="modal-footer">


                            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                            <button  class="btn red"  id="btnDelt">Confirm</button>





                        </div>
                    </div>

                </div>
            </div>
<!----------------------------------------------------------------------------------------------------------------------------!>
                 <!-- Modal for editing the user-->
                 <div class="modal fade" id="myModal2"  role="dialog"
                      aria-labelledby="myModalLabel" aria-hidden="true">
                     <div class="modal-dialog">
                         <div class="modal-content">
                             <!-- Modal Header -->
                             <div class="modal-header">
                                 <button type="button" class="close"
                                         data-dismiss="modal">
                                     <span aria-hidden="true">&times;</span>
                                     <span class="sr-only">Close</span>
                                 </button>
                                 <h4 class="modal-title" id="myModalLabel">
                                     Update User Details
                                 </h4>
                             </div>

                             <!-- Modal Body -->
                             <div class="modal-body">

                                 <form class="form-horizontal" role="form" >


                                     <div class="form-group">
                                         <label class="col-sm-4 control-label">First name *</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update-first-name" class="form-control"
                                                    id="update-first-name" required/>

                                         </div>
                                     </div>


                                     <div class="form-group">
                                         <label class="col-sm-4 control-label" >Last name</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update-last-name"
                                                    class="form-last-name form-control" id="update-last-name">
                                         </div>
                                     </div>


                                     <div class="form-group">

                                         <%@include file="datepicker_update.jsp" %>

                                     </div>

                                     <div class="form-group">
                                         <label for="update-country" class="col-sm-4 control-label" >Country *</label>
                                         <div class="col-sm-8">
                                             <select class="form-control" name="update-country" id="update-country" required>
                                                 <option>--Select--</option>
                                                 <option value="Sri Lanka">Sri Lanka</option>
                                                 <option value="India">India</option>
                                                 <option value="Japan">Japan</option>
                                                 <option value="Australia">Australia</option>
                                             </select>

                                         </div>
                                     </div>


                                     <div class="form-group">
                                         <label for="update-city" class="col-sm-4 control-label">City*</label>
                                         <div class="col-sm-8">
                                             <select class="form-control" id="update-city" name="update-city" required>
                                                 <option>--Select--</option>
                                             </select>
                                         </div>

                                     </div>

                                     <div class="form-group">
                                         <label class="col-sm-4 control-label">Email(eg:xxx@gmail.com)*</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update-form-email"
                                                    class="form-email form-control" id="update-form-email"
                                                    pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3}))$" required>
                                         </div>
                                     </div>

                                     <div class="form-group">
                                         <label class="col-sm-4 control-label">Mobile (94xxxxxxxxx)*</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update-form-mobile"
                                                    class="form-mobile form-control" id="update-form-mobile"
                                                    pattern="^\(?(\+94)\)?([0-9]{9})$"  required>
                                         </div>
                                     </div>

                                     <div class="form-group ">
                                         <label class="col-sm-4 control-label" >Username *</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update-username"  class="user_name form-control"
                                                    id="update-form-username" readonly/>

                                         </div>
                                     </div>


                                     <div class="form-group">
                                         <center>
                                             <span class="status"></span>
                                         </center>
                                     </div>

                                     <div class="form-group">
                                         <label class="col-sm-4 control-label" >Password *</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update-password"
                                                    class="form-password form-control"  id="update-password"
                                                    onchange=" validatePassword(update-password)" required></span>
                                         </div>

                                     </div>

                                     <div class="form-group">
                                         <label  class="col-sm-4 control-label" >Confirm pw *</label>
                                         <div class="col-sm-8">
                                             <input type="text" name="update_cnpassword"
                                                    class="form-password form-control" id="cform-password-confirm"
                                                    onchange=" passwordsEqual(update_cnpassword,update-password)" required>
                                         </div>

                                     </div>

                                     <div class="form-group">
                                         <label for="ugroup" class="col-sm-4 control-label"  >Role *</label>
                                         <div class="col-sm-8">
                                             <select class="form-control" name="ugroup" id="ugroup" required>
                                                 <option>--Select--</option>
                                                 <option value="Administrator">Administrator</option>
                                                 <option value="Customer Care">Customer Care</option>
                                                 <option value="Translater">Translater</option>

                                             </select>

                                         </div>
                                     </div>


                                 </form>

                             </div>

                             <!-- Modal Footer -->
                             <div class="modal-footer">
                                 <button type="button" class="btn btn-default"
                                         data-dismiss="modal">
                                     Close
                                 </button>
                                 <button type="submit" class="btn  btn-primary" id="updatebtn">Update details</button>


                             </div>
                         </div>
                     </div>
                 </div>


        <%--panel body to insert the table --%>
        <div class="panel-body" id="table">

        </div>


    </div>
   </div>
 <%--</div>--%>
<%--</div>--%>


</body>

</html>
