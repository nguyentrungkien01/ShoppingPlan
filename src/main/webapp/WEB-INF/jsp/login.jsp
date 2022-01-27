<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Welcome Page</title>
    </head>
    <body>
        <h1>Hello world</h1>
        <ul>
        <c:forEach var ="cat" items ="${categories}">
            <li>${cat.name}</li>
        </c:forEach>
        </ul>
    </body>
</html>