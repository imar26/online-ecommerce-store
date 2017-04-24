<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome to Online E-Commerce Store.  
</h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a href="${contextPath}/login.htm">Login</a>
<a href="${contextPath}/signup.htm">Signup</a>
</body>
</html>
