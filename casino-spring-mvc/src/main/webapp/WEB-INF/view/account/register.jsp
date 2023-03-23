<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Home</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <form:form method="POST" class="row">
        <div class="col-md-1">

        </div>
        <div class="col-md-5">
          <h1>Register!</h1>
          <br/>
          <label for="loginString">Login</label>
          <input class="form-control" type="text" id="loginString"/>
          <label for="psdString">Password</label>
          <input class="form-control" type="text" id="psdString"/>
          <br/>
          <!-- onclick="location.href='/basket/add?itemId=@Model.Item.Key.Value&&providerId=@Model.Provider.Key.Value'" -->
          <button class="btn btn-primary" type="button" onclick="location.href='/casino-spring-mvc/account/tmplogin'">
            Register
          </button>
        </div>
        <div class="col-md-4">
        </div>
      </form:form>
    </main>
  </body>
</html>
