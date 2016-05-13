<html xmlns:jsp="http://java.sun.com/JSP/Page">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!--including JSTL to the page -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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


                                 <textarea name="original-text" class="form-control" id="original-text" rows="5" cols="10">
                                     <%
                                        String s1= (String)request.getAttribute("original");
                                        request.setAttribute("s1",s1);
                                    %>
                                    <c:if test="${s1 != null}"/>
                                        <c:out value="${s1}"/>

                                 </textarea>


                                </div>

                                <div class="col-sm-6">

                                    <textarea class="form-control"  name="translated-text" id="text_trans" rows="5" cols="10">

                                    <%
                                        String s2= (String)request.getAttribute("translated");
                                        request.setAttribute("s2",s2);
                                    %>
                                        <c:if test="${s2 != null}"/>
                                        <c:out value="${s2}"/>

                                    </textarea>
                                </div>
                        </div>



                    <div class="form-group ">
                        <div class=" col-sm-6">
                            <select class="form-control" name="original-lang" id="original" style="z-index: 1; width: 200px; padding:0px; position:absolute;">

                                <%
                                    String buf_sel1=(String)request.getAttribute("selected_ol");
                                    request.setAttribute("buf_sel1",buf_sel1);

                                    ArrayList<String>  buffer=new ArrayList<String>();
                                    buffer= (ArrayList<String>)request.getAttribute("language_list");
                                    request.setAttribute("buffer",buffer);


                                %>

                                 <c:forEach varStatus="i" items="${buffer}">
                                     <%--<option value="<c:out value="${buffer[i.index]}"/>"></option>--%>
                                     <option>${buffer[i.index]}</option>
                                <%--   <c:choose>
                                       <c:when test="${buf_sel1 != null}">
                                           <option value="<c:out value="${buf_sel1}"/>"
                                       </c:when>
                                       <c:otherwise>

                                           <option value="<c:out value="${i}"/>"

                                       </c:otherwise>
                                   </c:choose>--%>

                             </c:forEach>

                                </select>

                            </div>

                            <div class=" col-sm-6">
                                <select class="form-control" name="translate-lang" id="translated" style="z-index: 1; width: 200px; padding:0px; position:absolute;">
                                    <%

                                        String buf_sel2=(String)request.getAttribute("selected_tl");
                                        request.setAttribute("buf_sel1",buf_sel2);

                                        ArrayList<String>  buffer2=new ArrayList<String>();
                                        buffer2= (ArrayList<String>)request.getAttribute("language_list");
                                        request.setAttribute("buffer2",buffer2);

                                    %>

                                    <c:forEach varStatus="i" items="${buffer2}">
                                        <%--<option value="<c:out value="${buffer2[i.index]}"/>"></option>--%>
                                        <option>${buffer2[i.index]}</option>
                                        <%--   <c:choose>
                                               <c:when test="${buf_sel1 != null}">
                                                   <option value="<c:out value="${buf_sel1}"/>"
                                               </c:when>
                                               <c:otherwise>

                                                   <option value="<c:out value="${i}"/>"

                                               </c:otherwise>
                                           </c:choose>--%>

                                    </c:forEach>
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