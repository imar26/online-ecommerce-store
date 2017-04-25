<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Sellers</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(function() {
		$(document).on('click', '.activate', function(e) {
			e.preventDefault();
			var aid = $(this).parent().parent().data('id');
			//alert(aid);
			$.ajax({
		        url: 'activateSeller.htm',		        
		        contentType: "application/json; charset=utf-8",
		        data: { 'aid': aid },
		        type: 'GET',
		        cache: false,
		        success: function (response) {
			        $('.status_'+response).text("Approved");
			        $('.action_'+response).find("a").removeClass("activate").addClass("deactivate").text("Deactivate Seller");
		        }
		    });			
		});
		$(document).on('click', '.deactivate', function(e) {
			e.preventDefault();
			var aid = $(this).parent().parent().data('id');
			//alert(aid);
			$.ajax({
				url : 'deactivateSeller.htm',
				contentType : "application/json; charset=utf-8",
				data : {
					'aid' : aid
				},
				type : 'GET',
				cache : false,
				success : function(response) {
					$('.status_'+response).text("Pending");
			        $('.action_'+response).find("a").removeClass("deactivate").addClass("activate").text("Activate Seller");
				}
			});
		});
	});
</script>
<style>
	body {
		margin: 10px;
	}
	.container {
		width: 90%;
		margin: 0 auto;
		padding-left: 15px;
		padding-right: 15px;
	}
	.row {
		margin-left: -15px;
		margin-right: -15px;
	}
	.col-md-6 {
	    width: 50%;
	    float: left;
	    padding-right: 15px;
    	padding-left: 15px;
    	position: relative;    	
	}
	.row:after {
		content: "";
		display: table;
		clear: both;
	}
	.pagination {
		text-align: center;
	}
	table {
		width: 100%;
	}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/logout.htm">Logout</a>
	<a href="${contextPath}/admin/admin-home.htm">Go back</a>
	<a href="${contextPath}/admin/active-sellers.htm">Active Sellers</a>
	<a href="${contextPath}/admin/pending-sellers.htm">Pending Sellers</a>
	<br />

	<br />
	<table id="table" border="1" cellpadding="5">
		<tr>
			<th>Company Name</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email ID</th>
			<th>Phone Number</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="seller" items="${sellers}">
			<tr data-id="${seller.personID}">
				<td>${seller.companyName}</td>
				<td>${seller.firstName}</td>
				<td>${seller.lastName}</td>
				<td>${seller.email.emailAddress}</td>
				<td>${seller.phone.phoneNo}</td>
				<td class="status_${seller.personID}"><c:choose>
						<c:when test="${seller.status == false}">Pending</c:when>
						<c:otherwise>Approved</c:otherwise>
					</c:choose></td>
				<td class="action_${seller.personID}"><c:choose>
						<c:when test="${seller.status == false}">
							<a href="#" class="activate">Activate Seller</a>
						</c:when>
						<c:otherwise>
							<a href="#" class="deactivate">Deactivate Seller</a>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	<div class="container">
		<div class="row">
			<div class="pagination">
			    <a href="${contextPath}/admin/view-sellers.htm?side=back">&laquo;</a>
			    <a href="${contextPath}/admin/view-sellers.htm?side=next">&raquo;</a>
			</div>
		</div>
	</div>		
</body>
</html>