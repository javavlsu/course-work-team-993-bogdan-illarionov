<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Home</title>
    <jsp:include page="shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="shared/_nav.jsp" />
    <h1>Hello world</h1>
    <p>${pageContext.request.contextPath}</p>
    <div class="row">
      <div class="container">
        <h1>${title}</h1>
        <p>
          <c:if test="${not empty msg}"> About: ${msg} </c:if>
        </p>
        <p>
          <a class="btn btn-primary btn-lg" href="#" role="button"
            >Learn more</a
          >
        </p>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <h2>Heading</h2>
          <p>Text</p>
          <p>
            <a class="btn btn-default" href="#" role="button">Action</a>
          </p>
        </div>
      </div>

      <hr />
      <footer>
        <p>&copy; josdem 2015</p>
      </footer>
    </div>
  </body>
</html>
