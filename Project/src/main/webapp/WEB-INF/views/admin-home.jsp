<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Home Page</title>
</head>
<body>
	<%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            String role = (String) session.getAttribute("role");
            if (role == "admin") {
    %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<h1>
		Hi <span style="text-transform: capitalize;">${admin.username},</span>
	</h1>

	<a href="${contextPath}/admin/view-sellers.htm">Manage Sellers</a>
	<br />
	<a href="${contextPath}/admin/manage-categories.htm">Manage
		Categories</a>
	<%
            }
    %>
</body>
</html>