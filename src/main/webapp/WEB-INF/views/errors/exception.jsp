<%@ page contentType="text/html; charset=UTF-8"%>  

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>错误页面</title>

<spring:url value="/resources/css/site.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

</head>

<body>


<div class="container">

	<div class="row">
		<div class="col-md-4">
		    <h1>Error Page</h1>
		    <p>Application has encountered an error. Please contact support on ...</p>
			<p>Failed URL : ${url}</p>
			<p>Exception: ${exception.message}</p>
			<c:forEach items="${exception.stackTrace}" var="ste">
				${ste} 
    		</c:forEach>
		</div>
	</div>

	<hr>
	<footer>
		
	</footer>
</div>




	<spring:url value="/resources/js/function.js" var="coreJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${coreJs}"></script>
	<script src="${bootstrapJs}"></script>
</body>

</html>

