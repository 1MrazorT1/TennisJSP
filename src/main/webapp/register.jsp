<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="signup" method="post">
    Email: <input type="text" name="email"><br>
    Name: <input type="text" name="nom"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Register">
</form>
<p>Already have an account? <a href="login.jsp">Login here</a>.</p>
</body>
</html>
