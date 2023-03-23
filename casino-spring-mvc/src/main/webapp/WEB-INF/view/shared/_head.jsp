<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/css/custom.css" var="customCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${customCss}" rel="stylesheet" />