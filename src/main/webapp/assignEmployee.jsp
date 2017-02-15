<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Employee to a ticket</title>
</head>
<body>
<div class="login" style="margin-top:-300px;">

<form action="/tickets/assign_employee" method="GET">
	  <h3>Email Id :</h3>   <input type="email" name="EmailId" required placeholder="yourmail@yourdomain.com" required autofocus/>
	    <h3>Password :</h3> <input type="password" name="Password" required/>
	    <h3>Issue Id:</h3><input type="text" name="TicketId" required/>
	    <h3>Employee Id:</h3>
		<button type="submit" class="btn btn-primary btn-block" style="margin-top:10px;"><h4>Assign Employee</h4></button>
	</form>
		
	</div>
</body>
</html>