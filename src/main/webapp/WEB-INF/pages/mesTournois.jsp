<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.ensicaen.tennis.bean.Tournoi" %>

<%
    List<Tournoi> tournois = (List<Tournoi>) request.getAttribute("tournois");
%>

<html>
<head>
    <title>Mes Tournois</title>
</head>
<body>
<h2>Tournois auxquels vous êtes inscrit</h2>

<% if (tournois == null || tournois.isEmpty()) { %>
    <p>Vous n'êtes inscrit à aucun tournoi.</p>
<% } else { %>
    <ul>
        <% for (Tournoi t : tournois) { %>
            <li>
                <strong><%= t.getNom() %></strong> — Date : <%= t.getDate() %>
                <form action="desinscrireTournoi" method="post" style="display:inline;">
                    <input type="hidden" name="idTournoi" value="<%= t.getId() %>" />
                    <button type="submit">Se désinscrire</button>
                </form>
            </li>
        <% } %>
    </ul>
<% } %>

<p><a href="dashboard.jsp">Retour au tableau de bord</a></p>
</body>
</html>
