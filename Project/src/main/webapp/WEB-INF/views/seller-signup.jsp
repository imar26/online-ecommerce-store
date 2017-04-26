<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seller Signup</title>
</head>
<body>
	<%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            String role = (String) session.getAttribute("role");
            if (role == "seller") {
    %>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/signup.htm">Go Back</a><br/>	
	<h1>Register a new seller</h1>
	<form:form action="seller/sellersignup.htm" commandName="seller" method="post">
		<table>
			<tr>
				<td>
					First Name:
				</td>
				<td>
					<form:input path="firstName" required="required" />
					<font color="red"><form:errors path="firstName" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Last Name:
				</td>
				<td>
					<form:input path="lastName" required="required" />
					<font color="red"><form:errors path="lastName" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Company Name:
				</td>
				<td>
					<form:input path="companyName" required="required" />
					<font color="red"><form:errors path="companyName" /></font>
				</td>
			</tr>
			<tr>
				<td>
					User Name:
				</td>
				<td>
					<form:input path="username" required="required" />
					<font color="red"><form:errors path="username" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Password:
				</td>
				<td>
					<form:password path="password" required="required" />
					<font color="red"><form:errors path="password" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Email ID:
				</td>
				<td>
					<form:input path="email.emailAddress" type="email" required="required" />
					<font color="red"><form:errors path="email.emailAddress" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Phone no:
				</td>
				<td>
					<form:input path="phone.phoneNo" maxlength="10" required="required" />
					<font color="red"><form:errors path="phone.phoneNo" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Address Type:
				</td>
				<td>
					<form:input path="address.addressType" disabled="true" value="Office" />
					<font color="red"><form:errors path="address.addressType" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Street:
				</td>
				<td>
					<form:input path="address.street" required="required" />
					<font color="red"><form:errors path="address.street" /></font>
				</td>
			</tr>
			<tr>
				<td>
					City:
				</td>
				<td>
					<form:input path="address.city" required="required" />
					<font color="red"><form:errors path="address.city" /></font>
				</td>
			</tr>
			<tr>
				<td>
					State:
				</td>
				<td>
					<form:input path="address.state" required="required" />
					<font color="red"><form:errors path="address.state" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Zip Code:
				</td>
				<td>
					<form:input path="address.zip" required="required" maxlength="5" />
					<font color="red"><form:errors path="address.zip" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Country:
				</td>
				<td>
					<form:input path="address.country" required="required" />
					<font color="red"><form:errors path="address.country" /></font>
				</td>
			</tr>
					
			<tr>
				<td colspan="2"><input type="submit" name="sellersignup" value="Register Seller" /></td>
			</tr>
		</table>
	</form:form>
	<%
            }
	%>
</body>
</html>