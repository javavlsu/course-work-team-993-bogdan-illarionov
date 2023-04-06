<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Login</title>
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
          <h1>Login!</h1>
          <!-- <br/> -->
          <div class="row">
            <label for="loginString">Login</label>
            <form:input class="form-control" type="text" id="loginString" path="username"/>
          </div>
          <br/>
          <div class="row">
            <label for="psdString">Password</label>
            <form:input class="form-control" type="text" id="psdString" path="password"/>
          </div>
          <br/>
          <div class="row">
            <form:button type="submit" class="btn btn-primary">LogIn</form:button>
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
