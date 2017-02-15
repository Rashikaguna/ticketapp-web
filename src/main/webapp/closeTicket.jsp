<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" media="screen" href="//d85wutc1n854v.cloudfront.net/live/css/screen_preview_legacy.css">
<title>Close Ticket</title>
</head>
<body>
<form action="/tickets/update_close" method="GET">
	    <input type="hidden" name="EmailId" required value="${LOGGED_IN_USER.emailId}" />
	     <input type="hidden" name="Password" required  value="${LOGGED_IN_USER.password}"/>
	    <h3>Ticket Id:</h3><input type="text" name="TicketId" required/><br>
		<button type="submit"><h4>Close the Ticket</h4></button>
	</form>
</body>
</html>
