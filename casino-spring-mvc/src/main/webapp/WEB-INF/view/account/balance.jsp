<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <title>Balance</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <form:form method="POST" class="row" modelAttribute="viewModel">
        <div class="col-md-1">

        </div>
        <div class="col-md-5">
          <h1>Balance!</h1>
          <br/>
          <div class="row">
            <label for="accountNumber">Account:</label>
            <form:input class="form-control" type="text" id="accountNumber" path="account"/>
            <form:errors path="account" cssClass="error"/>
          </div>
          <br/>
          <div class="row">
            <label for="balanceDelta">Add</label>
            <form:input class="form-control" type="number" id="balanceDelta" step="0.01" path="positiveBalanceDelta"/>
            <form:errors path="positiveBalanceDelta" cssClass="error"/>
          </div>
          <br/>
          <!-- onclick="location.href='/basket/add?itemId=@Model.Item.Key.Value&&providerId=@Model.Provider.Key.Value'" -->
          <div class="row">
            <form:button type="submit" class="btn btn-primary">Update</form:button>
          </div>
        </div>
        <div class="col-md-4">
        </div>
      </form:form>
  </body>
</html>
