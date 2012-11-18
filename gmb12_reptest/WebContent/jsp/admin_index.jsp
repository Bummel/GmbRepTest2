<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.salespoint-framework.org/web/taglib"
	prefix="sp"%>

<html>
<head>

<title></title>
</head>
<body>

<div class="olist">
<h1>Admin Index</h1>
<article>
	<ul>
		<li><a href="<c:url value="/logout" />">Logout</a></li>
		<li><a href="<c:url value="/toaccount" />">Account-Verwaltung</a></li>
		<li><a href="<c:url value="/" />">Home</a></li>
	</ul>
</article>
</div>

<div class="userlist">
	<h2>Mitglieder-Liste</h2>
	<br />
	<sp:forEach var="member" items="${members}">

${member.name}
	<form action="tofaccount" method="post">
			<input id="PARAM_MID" type="hidden" name="PARAM_MID" value="${member.identifier}" />
			<button type="submit">edit</button>
	</form>
<c:choose>
<c:when test="${!member.activated}">
	<form action="account_activate" method="post">
			<input id="PARAM_MID" type="hidden" name="PARAM_MID" value="${member.identifier}" />
			<button type="submit">activate</button>
	</form>
</c:when>
</c:choose>
		<!-- <spring:url var="url" value="/tofaccount/{mid}">
			<spring:param name="mid" value="${member.identifier}" />
		</spring:url>
		<a href="${url}"> ${member.name} <br>
		</a> -->

		<!--  <article class="member">${member.name}</article>-->
	</sp:forEach>
</div>

</body>
</html>