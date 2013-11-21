<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div>
  <spring:message var="title" code="error_uncaughtexception_title" htmlEscape="false" />
    <h2>${fn:escapeXml(title)}</h2>
    <p>
      <spring:message code="error_uncaughtexception_problemdescription" />
    </p>
    <c:if test="${not empty exception}">
    
        <h4>
          <spring:message code="exception_details" />
        </h4>
        <spring:message var="message" code="exception_message" htmlEscape="false" />
        <spring:message var="stacktrace" code="exception_stacktrace" htmlEscape="false" />
          <c:forEach items="${exception.stackTrace}" var="trace">
            <c:out value="${trace}" />
            <br />
          </c:forEach>
      
    </c:if>
</div>