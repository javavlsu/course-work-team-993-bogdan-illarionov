<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Home</title>
    <jsp:include page="../shared/_head.jsp" />
    <spring:url value="/account/register" var="registerLink" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <form:form method="POST" class="row" modelAttribute="loginModel">
        <div class="col-md-1">

        </div>
        <div class="col-md-5">
          <h1>Register!</h1>
          <br/>
          <label for="loginString">Login</label>
          <form:input class="form-control" type="text" id="loginString" path="username"/>
          <label for="psdString">Password</label>
          <form:input class="form-control" type="text" id="psdString" path="password"/>
          <br/>
          <!-- onclick="location.href='/basket/add?itemId=@Model.Item.Key.Value&&providerId=@Model.Provider.Key.Value'" -->
          <button class="btn btn-primary" type="button" onclick="location.href='/casino-spring-mvc/account/tmplogin'">
            Register
          </button>
          <p><form:button type="submit" class="btn">Save</form:button></p>
        </div>
        <div class="col-md-4">
        </div>
      </form:form>
    </main>
  </body>
</html>
