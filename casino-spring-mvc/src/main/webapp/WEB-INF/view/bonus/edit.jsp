<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Edit bonus</title>
    <jsp:include page="../shared/_head.jsp" />
    <spring:url value="/account/register" var="registerLink" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <form:form method="POST" class="row" modelAttribute="viewModel">
        <div class="col-md-1">

        </div>
        <div class="col-md-5">
          <h1>Bonus Edit!</h1>
          <!-- <br/> -->
          <div class="row">
            <h3>Id</h3>
            <c:out value="${viewModel.getId()}"/>
          </div>
          <br/>
          <div class="row">
            <label for="nameString">Name</label>
            <form:input class="form-control" type="text" id="nameString" path="name"/>
            <form:errors path="name" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <label for="enableBool">Enabled </label>
            <form:checkbox path="isEnabled" value=" Enabled" id="enableBool"/>  
            <form:errors path="isEnabled" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <label for="countInt">Count</label>
            <form:input class="form-control" type="number" id="countInt" path="triggerCount"/>
            <form:errors path="triggerCount" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <label for="countInt">Term</label>
            <form:input class="form-control" type="text" id="termString" path="toTerm"/>
            <form:errors path="toTerm" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <label for="lotsIdsString">Lots ids</label>
            <form:input class="form-control" type="text" id="lotsIdsString" path="lotsIds"/>
            <form:errors path="lotsIds" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <label for="numberKoef">Koef</label>
            <form:input class="form-control" type="number" step="0.01" id="numberKoef" path="bonusKoef"/>
            <form:errors path="bonusKoef" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <h3>Expire Type</h3>
            <c:out value="${viewModel.getStringExpireType()}" />
          </div>
          <br/>
          <div class="row">
            <h3>Trigger Type</h3>
            <c:out value="${viewModel.getStringTriggerType()}" />
          </div>
          <br/>
          <div class="row">
            <form:button type="submit" class="btn btn-primary">Edit</form:button>
          </div>
          <!-- <br/> -->
          <!-- <p></p> -->
        </div>
        <div class="col-md-4">
        </div>
      </form:form>
    </main>
  </body>
</html>
