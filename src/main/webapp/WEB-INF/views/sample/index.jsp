<%@ page contentType="text/html; charset=UTF-8"%>  

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC Sample</title>

<spring:url value="/resources/css/site.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 3 MVC Project</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<p>
			<c:if test="${not empty username}">
				Hello ${username}
			</c:if>

			<c:if test="${empty username}">Welcome!</c:if>
		</p>
		<p>
			${user.userid} | ${user.username} | ${user.age} | ${user.sex}
		</p>
	</div>
</div>

<div class="container">

	<c:out value="${attributeName}"></c:out>

	<div class="row">
		<div class="col-md-4">
			<h2>字符数组</h2>
			<c:forEach items="${stringList}" var="stringVar">
				${string} 
    		</c:forEach>

		</div>
		<div class="col-md-4">
			<h2>整数数组</h2>
			<c:forEach items="${intArray}" var="intVar">
				${intVar} 
    		</c:forEach>

		</div>
	</div>

	<hr>
	<footer>
		<p>&copy; Mkyong.com 2015</p>
	</footer>
</div>

<spring:url value="/resources/js/function.js" var="coreJs" />
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>

</body>
</html>
