<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <title>Profile</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <form:form method="POST" class="row" modelAttribute="viewModel">
        <div class="col-md-1">

        </div>
        <div class="col-md-5">
          <h1>Profile!</h1>
          <form:hidden path = "login"/>
          <div class="row">
            <label for="psdString">Password</label>
            <form:input class="form-control" type="text" id="psdString" path="password"/>
          </div>
          <br/>
          <br/>
          <div class="row">
            <label for="psdString">Email</label>
            <form:input class="form-control" type="text" id="emailString" path="email"/>
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
    </main>
  </body>
</html>
