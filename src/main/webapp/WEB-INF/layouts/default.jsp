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
<title>Chat to Push</title>

<spring:url value="resources/js/jquery.js" var='jsJquery' />
<spring:url value="resources/js/main.js" var='jsMain' />
<spring:url value="resources/js/channels.js" var='jsChannelsForm' />
<spring:url value="resources/js/users.js" var='jsUsersForm' />
<spring:url value="resources/js/highcharts.js" var='jsHighCharts' />
<spring:url value="resources/js/exporting.js" var='jsExporting' />
<spring:url value="resources/js/stats.js" var='jsStats' />

<spring:url value="resources/js/jquery.easytabs.min.js" var='jsEasyTabs' />


<spring:url value="resources/js/jquery.bootpag.min.js" var='jsBootpag' />
<spring:url value="resources/js/bootstrap.min.js" var='jsBootstrap' />


<spring:url value="resources/css/style.css" var="cssStyle" />
<spring:url value="resources/css/styles.css" var="cssStyles" />
<spring:url value="resources/css/bootstrap.css" var="cssBootstrap" />

<script type="text/javascript" src="${jsJquery}"></script>
<script type="text/javascript" src="${jsMain}"></script>
<script type="text/javascript" src="${jsChannelsForm}"></script>
<script type="text/javascript" src="${jsUsersForm}"></script>
<script type="text/javascript" src="${jsHighCharts}"></script>
<script type="text/javascript" src="${jsExporting}"></script>
<script type="text/javascript" src="${jsStats}"></script>

<script type="text/javascript" src="${jsEasyTabs}"></script>

<script type="text/javascript" src="${jsBootpag}"></script>
<script type="text/javascript" src="${jsBootstrap}"></script>

<link rel="stylesheet" type="text/css" media="screen" href="${cssStyle}" />
<link rel="stylesheet" type="text/css" media="screen" href="${cssStyles}" />
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
