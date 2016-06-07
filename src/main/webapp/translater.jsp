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
            var e = document.getElementById("original-text").value;
            var e2 = document.getElementById("text_trans").value;

            document.getElementById("original-text").value = e2;

            document.getElementById("text_trans").value = e;

        }
    </script>

    <script>
        function resetFunction() {
            document.getElementById("translater").reset();
        }
    </script>
</head>


<body >

<fmt:setLocale value="En"/>
<fmt:bundle basename="labelNames">

<div class="row">
    <div class="container-fluid">
        <center>
            <div class="jumbotron">

                    <center>

                        <form class="form-horizontal" role="form" method="post" action="MyServletTranslate" id="translater">
                            <div class="form-group ">
                                <div class="col-sm-6">
                                    <%
                                        String s1= (String)request.getAttribute("original");
                                        request.setAttribute("s1",s1);
                                    %>
                                    <c:if test="${not empty s1}">
                                        <textarea name="original-text" class="form-control" id="original-text" rows="5"
                                                  cols="10">${s1}</textarea>
                                    </c:if>
                                    <c:if test="${empty s1}">
                                        <textarea name="original-text" class="form-control" id="original-text" rows="5"
                                                  cols="10"></textarea>
                                    </c:if>


                                </div>

                                <div class="col-sm-6">
                                    <%
                                        String s2= (String)request.getAttribute("translated");
                                        request.setAttribute("s2",s2);
                                    %>
                                    <c:if test="${not empty s1}">
                                        <textarea class="form-control"  name="translated-text" id="text_trans" rows="5"
                                                  cols="10">${s2}</textarea>
                                    </c:if>
                                    <c:if test="${empty s1}">
                                        <textarea class="form-control"  name="translated-text" id="text_trans" rows="5"
                                                  cols="10"></textarea>
                                    </c:if>

                                </div>
                        </div>



                    <div class="form-group ">
                        <div class=" col-sm-6">
                            <select class="form-control" name="original-lang" id="original" style="z-index: 1;
                             width: 200px; padding:0px; position:absolute;">

                                <%
                                    String buf_sel1=(String)request.getAttribute("selected_ol");
                                    request.setAttribute("buf_sel1",buf_sel1);

                                    ArrayList<String>  buffer=new ArrayList<String>();
                                    buffer= (ArrayList<String>)request.getAttribute("language_list");
                                    request.setAttribute("buffer",buffer);


                                %>

                                 <c:forEach varStatus="i" items="${buffer}">


                                    <c:choose>
                                       <c:when test="${buf_sel1 != null}">

                                           <option selected="selected"><%--<c:out value="${buf_sel1}"/>--%>${buf_sel1}
                                           </option>
                                           <option>${buffer[i.index]}</option>

                                       </c:when>
                                       <c:otherwise>

                                           <option>${buffer[i.index]}</option>

                                       </c:otherwise>
                                   </c:choose>

                                 </c:forEach>

                                </select>

                            </div>

                            <div class=" col-sm-6">
                                <select class="form-control" name="translate-lang" id="translated" style="z-index: 1;
                                 width: 200px; padding:0px; position:absolute;">
                                    <%

                                        String buf_sel2=(String)request.getAttribute("selected_tl");
                                        request.setAttribute("buf_sel2",buf_sel2);

                                        ArrayList<String>  buffer2=new ArrayList<String>();
                                        buffer2= (ArrayList<String>)request.getAttribute("language_list");
                                        request.setAttribute("buffer2",buffer2);

                                    %>

                                    <c:forEach varStatus="i" items="${buffer2}">

                                          <c:choose>
                                               <c:when test="${buf_sel2 != null}">
                                                   <option selected="selected"><%--<c:out value="${buf_sel1}"/>--%>
                                                   ${buf_sel2}</option>
                                                   <option>${buffer2[i.index]}</option>
                                               </c:when>
                                               <c:otherwise>

                                                   <option>${buffer2[i.index]}</option>

                                               </c:otherwise>
                                           </c:choose>

                                    </c:forEach>
                                    </select>

                            </div>


                        </div>


                    <!-- Form buttons -->

                        <div class="form-group "></div>
                        <div class="form-group "></div>
                        <div class="form-group ">



                            <div class=" col-sm-6">


                                <button type="submit" class="btn btn-primary" id="btnTranslate" ><fmt:message
                                        key="translater.button.translate"/></button>

                                <button type="button" class="btn btn-primary"  onclick="myFunction()"><fmt:message
                                        key="translater.button.swaptext"/></button>


                                <input class="btn btn-primary"  type="button" onclick="resetFunction()" value="Reset" >


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
            <a href="http://localhost:8080/LogoutServlet"><fmt:message key="translater.label.logout"/></a>
        </div>
    </div>

    <div class="form-group ">
        <p  style="color:blue"><strong><fmt:message key="translater.label.loggedinuser"/>
            <%
                request.setAttribute("name", request.getAttribute("name"));
            %>
            <c:set var="name" scope="request" value="${name}"/>
            <c:out value="${name}"/>
        </strong>
        </p>
    </div>
</div>

</fmt:bundle>
</body>


</html>