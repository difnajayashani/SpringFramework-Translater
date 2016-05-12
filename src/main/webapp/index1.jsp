
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>


<body>
<h2>Hello World!</h2>


<c:set var="salary" scope="session" value="${2000*2}"/>
<c:out value="${salary}"/>


<h1>Please login</h1>

<form action="loginAuthenticate.jsp" >

    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name="password"/><br/>

    <input type="submit" />
</form>
<font color="red"><c:if test="${not empty param.errMsg}">
    <c:out value="${param.errMsg}" />
</c:if></font>


</body>
</html>
