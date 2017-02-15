<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="//d85wutc1n854v.cloudfront.net/live/css/screen_preview_legacy.css">
<title>View Tickets</title>
</head>
<body>
<form action="/tickets/view_user_tickets" method="GET">
	   <input type="hidden" name="EmailId" required value="${LOGGED_IN_USER.emailId}"/>
	 <input type="hidden" name="Password" required value="${LOGGED_IN_USER.password}"/>
	    <button type="submit"><h4>view</h4></button>
	</form>

<h3>User Details</h3>
	
	<table border="1">
		<thead>
			<tr>
				<th>id</th>
				<th>User_id</th>
				<th>Subject</th>
				<th>Description</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}" varStatus="i">
				<tr>
					<td>${c.id}</td>
					<td>${c.userId.getId()}</td>
					<td>${c.subject}</td>
					<td>${c.description}</td>
					<td>${c.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
</body>
</html>
