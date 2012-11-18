<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="gmblogin">
	<form action="login" method="post">
		<fieldset>
			<legend>Login</legend>
			<label for="PARAM_IDENTIFIER">User</label><br />
			<input id="PARAM_IDENTIFIER" type="text" name="PARAM_IDENTIFIER" />
			<br />
			<label for="PARAM_PASSWORD">Password</label><br />
			<input id="PARAM_PASSWORD" type="text" name="PARAM_PASSWORD" />
			<br />
			<button type="submit">login</button>
		</fieldset>
	</form>
</div>