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
    <h1>Lots!</h1>

    <c:forEach items="${lots}" var="lot">
      <div>
        <h2><c:out value="${lot.getName()}" /></h2>
        <p>
          <c:out value="${lot.getDescription()}" />
        </p>
      </div>
    </c:forEach>
  </body>
</html>
