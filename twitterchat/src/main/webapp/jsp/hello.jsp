<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<html>
	<head>
		<title>Hello Twitter</title>
	</head>
	<body>
		<h3>Hello, ${twitterProfile.name}!</h3>
		
		<h4>These are your Twitter friends:</h4>

		<ul>
			<c:forEach items="${friends}" var="friend">
   			 <li> ${friend.name} </li>
			</c:forEach>
		</ul>
	</body>
</html>