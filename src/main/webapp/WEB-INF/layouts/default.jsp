<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!DOCTYPE HTML>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
<head>
	<title>Feed to Push</title>
	
	<spring:url value="resources/js/jquery.js" var='jsJquery' />
	<spring:url value="resources/js/form.js" var='jsForm' />
	<spring:url value="resources/css/style.css" var="cssStyle" />
	<spring:url value="resources/css/bootstrap.css" var="cssBootstrap" />
	
	<script type="text/javascript" src="${jsForm}"> </script>
	<script type="text/javascript" src="${jsJquery}"> </script>
	<link rel="stylesheet" type="text/css" media="screen" href="${cssStyle}" />
	<link rel="stylesheet" type="text/css" media="screen" href="${cssBootstrap}" />
	
	<spring:message code="application_name" var="app_name"
		htmlEscape="false" />
	<title><spring:message code="welcome_h3" arguments="${app_name}" /></title>
</head>

<body>
	
	<tiles:insertAttribute name="header" ignore="true" />
	<tiles:insertAttribute name="central" ignore="true" />
	<tiles:insertAttribute name="footer" ignore="true" />
	
</body>
</html>
