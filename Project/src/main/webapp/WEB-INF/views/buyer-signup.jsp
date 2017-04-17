<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buyer Signup</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$("#checkbox").on('click', function() {
			var street1 = $("#street1").val();
			var city1 = $("#city1").val();
			var state1 = $("#state1").val();
			var zip1 = $("#zip1").val();
			var country1 = $("#country1").val();
			if($(this).is(":checked")) {
				$("#street2").val(street1);
				$("#city2").val(city1);
				$("#state2").val(state1);
				$("#zip2").val(zip1);
				$("#country2").val(country1);
			} else {
				$("#street2").val("");
				$("#city2").val("");
				$("#state2").val("");
				$("#zip2").val("");
				$("#country2").val("");
			}
		});		
	});
</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/signup.htm">Go Back</a><br/>
	<h1>Register a new buyer</h1>
	<form:form action="buyer/buyersignup.htm" commandName="user" method="post">
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
					<form:input path="address[0].addressType" disabled="true" value="Billing" />
					<font color="red"><form:errors path="address[0].addressType" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Street:
				</td>
				<td>
					<form:input id="street1" path="address[0].street" required="required" />
					<font color="red"><form:errors path="address[0].street" /></font>
				</td>
			</tr>
			<tr>
				<td>
					City:
				</td>
				<td>
					<form:input id="city1" path="address[0].city" required="required" />
					<font color="red"><form:errors path="address[0].city" /></font>
				</td>
			</tr>
			<tr>
				<td>
					State:
				</td>
				<td>
					<form:input id="state1" path="address[0].state" required="required" />
					<font color="red"><form:errors path="address[0].state" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Zip Code:
				</td>
				<td>
					<form:input id="zip1" path="address[0].zip" required="required" maxlength="5" />
					<font color="red"><form:errors path="address[0].zip" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Country:
				</td>
				<td>
					<form:input id="country1" path="address[0].country" required="required" />
					<font color="red"><form:errors path="address[0].country" /></font>
				</td>
			</tr>
			<tr>
				<td>
					<form:checkbox id="checkbox" path="" value="yes" />Same as billing address
				</td>
			</tr>
			<tr>
				<td>
					Address Type:
				</td>
				<td>
					<form:input path="address[1].addressType" disabled="true" value="Shipping" />
					<font color="red"><form:errors path="address[1].addressType" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Street:
				</td>
				<td>
					<form:input id="street2" path="address[1].street" required="required" />
					<font color="red"><form:errors path="address[1].street" /></font>
				</td>
			</tr>
			<tr>
				<td>
					City:
				</td>
				<td>
					<form:input id="city2" path="address[1].city" required="required" />
					<font color="red"><form:errors path="address[1].city" /></font>
				</td>
			</tr>
			<tr>
				<td>
					State:
				</td>
				<td>
					<form:input id="state2" path="address[1].state" required="required" />
					<font color="red"><form:errors path="address[1].state" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Zip Code:
				</td>
				<td>
					<form:input id="zip2" path="address[1].zip" required="required" maxlength="5" />
					<font color="red"><form:errors path="address[1].zip" /></font>
				</td>
			</tr>
			<tr>
				<td>
					Country:
				</td>
				<td>
					<form:input id="country2" path="address[1].country" required="required" />
					<font color="red"><form:errors path="address[1].country" /></font>
				</td>
			</tr>			
			<tr>
				<td colspan="2"><input type="submit" value="Register Buyer" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>