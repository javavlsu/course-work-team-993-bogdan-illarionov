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

    <div class="content">
      <h1>Welcome</h1>
      <br/>
      <br/>
      <div class="row">
        <div class="col-md-3">
          <h1><u>Main lots</u></h1>
        </div>
      </div>
      <div class="row">
        <c:forEach items="${lots}" var="lot">
          <spring:url value="/lots/${lot.getId()}" var="openLink" />
          <div class="col-md-3">
            <a href="${openLink}">
              <div class="row">
                <img
                  class="small-title-image"
                  src="${lot.link}"
                  alt="lot-image"
                />
              </div>
              <div class="row">
                <h2><c:out value="${lot.getName()}" /></h2>
              </div>
            </a>
          </div>
          <div class="col-md-1"></div>
        </c:forEach>
      </div>
      <br/>
      <br/>
      <div class="row">
        <div class="col-md-3">
          <h1><u>Last bets</u></h1>
        </div>
      </div>
      <br/>
      <div class="row">
        <table class="table bg-light text-dark">
          <thead>
            <tr>
              <th>#</th>
              <th>Login</th>
              <th>Lot</th>
              <th>Status</th>
              <th>Sum</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${bets}" var="bet">
              <tr>
                <td><c:out value="${bet.getId()}" /></td>
                <td><c:out value="${bet.user.login}" /></td>
                <td><c:out value="${bet.outcome.lot.name}" /></td>
                <td><c:out value="${bet.getBetStatus().toString()}" /></td>
                <td><c:out value="${bet.sum}" /></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>
