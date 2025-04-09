<%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="false"/>
</jsp:include>

<div class="container-fluid m-3">
	<div class="row border my-title">
		<h1>Gestion du club de tennis de l'Ensicaen</h1>
	</div>
	<div class="row mt-3">
		<a href="console/">Console H2 database</a>
	</div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />
