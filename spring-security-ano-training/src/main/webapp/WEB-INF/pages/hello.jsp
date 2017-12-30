<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Spring Security</title>
</head>
<body>
<h2>Spring Security Tutorial Hello World Example !!!</h2>
<h4>${welcomeMessage}</h4>
<h4>
    <a href="admin">Admin Page</a> || <a href="<c:url value="logout" />">Logout</a>
</h4>
</body>
</html>