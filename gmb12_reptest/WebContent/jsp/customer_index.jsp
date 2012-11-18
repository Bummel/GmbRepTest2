
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>Customer Index</h1>
<article>
	<ul>
		<li><a href="<c:url value="/logout/" />">Logout</a></li>
		<li><a href="<c:url value="/toaccount" />">Account-Verwaltung</a></li>
		<li><a href="<c:url value="/" />">Home</a></li>
	</ul>
</article>