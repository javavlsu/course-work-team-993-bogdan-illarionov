<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>Edit outcome</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <sec:authorize access="hasAnyAuthority('Admin')">
        <form:form method="POST" class="row" modelAttribute="viewModel">
        <div class="col-md-5">
          <h1>Outcome!</h1>
          <div class="row">
            <label for="outcomeId">Id</label>
            <form:input class="form-control" value="${outcome.getId()}"  type="number" id="outcomeId" path="outcomeId" readonly="true"/>
          </div>
          <br/>
          <div class="row">
            <label for="valueString">Value</label>
            <form:input class="form-control" value="${outcome.getValue()}" type="text" id="valueString" path="value" readonly="true"/>
          </div>
          <div class="row">
            <label for="koef">Koef</label>
            <form:input class="form-control" value="${outcome.getKoef()}" type="number" step="0.01" id="koef" path="koef"/>
            <form:errors path="koef" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <form:button type="submit" class="btn btn-primary">Save</form:button>
          </div>
        </div>
        <div class="col-md-4">
        </div>
      </form:form>
      </sec:authorize>
    </main>
  </body>
</html>
