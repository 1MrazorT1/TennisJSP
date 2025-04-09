<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="fr.ensicaen.tennis.bean.Adherent" %>
<%@ page session="true" %>
<%
    Adherent adherent = (Adherent) session.getAttribute("adherent");
    if (adherent == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<h2>Welcome, <%= adherent.getNom() %>!</h2>
<p>Your email: <%= adherent.getEmail() %></p>
<p><a href="listeTournois">S’inscrire à un tournoi</a></p>
<p><a href="mesTournois">Voir mes tournois inscrits</a></p>
<p><a href="logout.jsp">Logout</a></p>
</body>
</html>
