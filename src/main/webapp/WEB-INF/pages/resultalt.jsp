<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SpringMVCPractice</title>
</head>
<body>
<br>
<form action="/deletephotos" method="POST">
    <table border='3' cellpadding='5'>
        <tr>
            <th>Delete photo</th>
            <th>PhotoID</th>
            <th>Photo</th>
        </tr>
        ${phototable}
    </table>
    <p><input type="submit"></p>
</form>
<form action="/">
    Return start page:
    <input type="submit"/>
</form>
</body>
</html>
