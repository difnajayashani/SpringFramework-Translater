<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page">


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Translator Page</title>

    <!-- CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="css/form-elements.css">



    <script>
        function myFunction(){
            var e = document.getElementById("text_origi").value;
            var e2 = document.getElementById("text_trans").value;

            document.getElementById("text_origi").value = e2;

            document.getElementById("text_trans").value = e;

        }
    </script>
</head>


<body >

<div class="row">
    <div class="container-fluid">
        <center>
            <div class="jumbotron">

                    <center>

                        <form class="form-horizontal" role="form" method="post" action="MyServletTranslate" >
                            <div class="form-group ">
                                <div class="col-sm-6">

                                <%
                                    out.println("<textarea name=\"original-text\" class=\"form-control\" rows=\"5\" cols=\"10\"  id=\"text_origi\" >");
                                    String s1= (String)request.getAttribute("original");
                                    if (s1 != null){
                                        out.println(s1);
                                    }

                                    out.println("</textarea>");
                                %>
                                </div>

                                <div class="col-sm-6">

                                    <textarea class="form-control"  name="translated-text" id="text_trans" rows="5" cols="10">

                                    <% String s2= (String)request.getAttribute("translated");
                                        if (s2 != null){
                                            out.println(s2);
                                        }


                                    %>
                                </textarea>
                                </div>
                        </div>



                    <div class="form-group ">
                        <div class=" col-sm-6">
                            <select class="form-control" name="original-lang" id="original" style="z-index: 1; width: 200px; padding:0px; position:absolute;">

                                <%

                                    String buf_sel1=(String)request.getAttribute("selected_ol");
                                    ArrayList<String>  buffer=new ArrayList<String>();
                                    buffer= (ArrayList<String>)request.getAttribute("language_list");

                                    for(int i=0;i<buffer.size();i++){

                                        if( buf_sel1 !=null ){
                                            out.println("<option>" + buf_sel1 + "</option>");
                                            break;
                                        }
                                        else

                                            out.println("<option>"+buffer.get(i)+"</option>");

                                    }
                                %>


                                </select>

                            </div>

                            <div class=" col-sm-6">
                                <select class="form-control" name="translate-lang" id="translated" style="z-index: 1; width: 200px; padding:0px; position:absolute;">
                                    <%

                                        String buf_sel2=(String)request.getAttribute("selected_tl");
                                        ArrayList<String>  buffer2=new ArrayList<String>();
                                        buffer2= (ArrayList<String>)request.getAttribute("language_list");
                                        for(int i=0;i<buffer2.size();i++){
                                            if( buf_sel2 !=null ){
                                                out.println("<option>" + buf_sel2 + "</option>");
                                                break;
                                            }
                                            else

                                                out.println("<option>"+buffer.get(i)+"</option>");
                                        }

                                    %>
                                    </select>

                            </div>


                        </div>


                    <!-- Form buttons -->


                        <div class="form-group "></div>
                        <div class="form-group "></div>
                        <div class="form-group ">



                            <div class=" col-sm-6">


                                <input type="submit" class="btn btn-primary  " value=Translate  name="submit"/>

                                <button type="button" class="btn btn-primary"  onclick="myFunction()">Swap the Text</button>

                                <input type="reset" class="btn btn-primary" value="Reset"/>


                            </div>
                            <div class=" col-sm-6"></div>



                        </div>



                    </form>

                </center>
            </div>


         </center>

    </div>

</div>

<div class="row">
    <div class="form-group ">
        <div id="logout">
            <a href="http://localhost:8080/Bootstrap_Translater/LogoutServlet">Logout</a>
        </div>
    </div>

    <div class="form-group ">
        <p  style="color:blue"> Logged in as
            <%=request.getAttribute("name")%>
        </p>
    </div>
</div>
</body>


</html>