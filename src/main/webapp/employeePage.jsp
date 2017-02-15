<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Menu</title>
</head>

<body>
	<div class="login">
		<h1>Employee Page</h1>
		<button type="button"
			onclick="location.href='viewEmployeetickets.jsp'"
			class="btn btn-primary btn-block btn-large">View My Tickets</button>
		<br />
		<button type="button" onclick="location.href='deleteTicket.jsp'"
			class="btn btn-primary btn-block btn-large">Delete Tickets</button>
		<br />
		<button type="button" onclick="location.href='assignEmployee.jsp'"
			class="btn btn-primary btn-block btn-large">Reassign Ticket</button>
		<br />
	</div>

	<script src="js/index.js"></script>

</body>
</html>