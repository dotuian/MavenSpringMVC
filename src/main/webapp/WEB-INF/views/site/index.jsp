<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Maven + Spring MVC Sample</title>

<spring:url value="/resources/css/site.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<body>
<div class="container" th:fragment="content">

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 3 MVC Project</a>
		</div>


		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Link</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">用户<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<spring:url value="/site/changePassword"/>">修改密码</a></li>
						<li><a href="<spring:url value="/site/histroy"/>">浏览历史</a></li>
						<li><a href="<spring:url value="/site/profile"/>">用户信息</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="<spring:url value="/site/logout"/>">退出登录</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->


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
		<p>${user.userid} | ${user.username} | ${user.age} | ${user.sex}</p>
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

</div>

</body>
</html>
