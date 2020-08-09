<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>List</title>
</head>
<body>
<div style="width: 400px; height: 600px; margin: 200px auto;">
    <h1>Register</h1>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <label>
            Phone Number: <input name="phoneNumber" type="text"/>
        </label>
        <br/>
        <label>
            Name: <input name="name" type="text">
        </label>
        <br/>
        <label>
            Password: <input id="password" name="password" type="password">
        </label>
        <br/>
        <label>
            Repeat password: <input id="repeat-password" type="password">
        </label>
        <br/>

        <c:if test="${param['error'] != null}">
            <p>Invalid data</p>
            <br/>
        </c:if>

        <input type="submit" value="Register"/>
    </form>
</div>
</body>
</html>
