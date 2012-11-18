<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/res/css/style.css" />" />

<title>Gmb</title>
</head>
<body>
	<h1>Gmb Home7</h1>
	<article>
		<ul>
			<!--  
			<sp:loggedIn test="false">
				<li><a href="<c:url value="/toindex/" />">Account</a></li>
			</sp:loggedIn>

			<sp:loggedIn>
				<li><a href="<c:url value="/tologin/" />">Login</a></li>
			</sp:loggedIn>
-->
			<li><a href="<c:url value="/tologin" />">Login</a></li>
			<li><a href="<c:url value="/toregistration" />">Registration</a></li>
			<li><a href="<c:url value="/home" />">refresh</a></li>
		</ul>
	</article>

	<br />
</body>
</html>