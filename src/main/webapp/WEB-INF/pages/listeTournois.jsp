<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ensicaen.tennis.bean.Tournoi" %>

<%
    List<Tournoi> tournois = (List<Tournoi>) request.getAttribute("tournois");
%>

<html>
<head>
    <title>Liste des Tournois</title>
</head>
<body>
<h2>Liste des tournois</h2>

<% if (tournois == null || tournois.isEmpty()) { %>
    <p>Aucun tournoi disponible.</p>
<% } else { %>
    <ul>
        <% for (Tournoi t : tournois) { %>
            <li>
                <strong><%= t.getNom() %></strong> — <%= t.getDate() %>
                <form action="inscrireTournoi" method="post" style="display:inline;">
                    <input type="hidden" name="tournoi" value="<%= t.getId() %>" />
                    <button type="submit">S'inscrire</button>
                </form>
            </li>
        <% } %>
    </ul>
<% } %>

<p><a href="dashboard.jsp">Retour au tableau de bord</a></p>
</body>
</html>
