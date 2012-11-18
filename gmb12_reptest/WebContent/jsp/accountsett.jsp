<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="gmbaccount">
	<form action="accountsett_change" method="post">
		<fieldset>
			<legend>Account-Settings</legend>
			<input id="PARAM_MID" type="hidden" name="PARAM_MID" value="${member.identifier}" />
			<label for="PARAM_NAME">Name</label><br />
			<input id="PARAM_NAME" type="text" name="PARAM_NAME" value="${member.name}"/>
			<br />
			<label for="PARAM_PASSWORD">Password</label><br />
			<input id="PARAM_PASSWORD" type="text" name="PARAM_PASSWORD" />
			<br />
			<button type="submit">change</button>
		</fieldset>
	</form>
	<br>
	<a href="<c:url value="/toindex" />">Back</a>
</div>