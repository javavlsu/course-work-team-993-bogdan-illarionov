<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>Bets</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">
      <c:if test="${isShow}">
        <br />
        <div class="row">
          <table class="table bg-light text-dark">
            <thead>
              <tr>
                <th>id</th>
                <th>lot</th>
                <th>outcome</th>
                <th>sum</th>
                <th>status</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${bets}" var="bet">
                <tr>
                  <td><c:out value="${bet.getId()}" /></td>
                  <td>
                    <c:out value="${bet.getOutcome().getLot().getName()}" />
                  </td>
                  <td><c:out value="${bet.getOutcome().getValue()}" /></td>
                  <td>
                    <c:out value="${bet.getSum()} -> ${bet.getBetStatus() ==
                    \"Loss\" ? 0 : bet.getBetStatus() == \"Unknown\" ? \"??\"
                    :bet.getOutcome().getKoef() * bet.getSum() }" />
                  </td>
                  <td><c:out value="${bet.getBetStatus().toString()}" /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="row">
          <div class="col-md-4"></div>
          <div class="col-md-5">
            <div class="row">
              <div class="col-md-3">
                <spring:url value="/bets?num=${prev}" var="prevLink" />
                <c:if test="${prev != 0}">
                  <a class="btn btn-info" href="${prevLink}"><h1>&#60;</h1></a>
                </c:if>
              </div>
              <div class="col-md-3">
                <h2 class="text-dark"><c:out value="${num}" /></h2>
              </div>
              <div class="col-md-3">
                <spring:url value="/bets?num=${next}" var="nextLink" />
                <c:if test="${next != max}">
                  <a class="btn btn-info" href="${nextLink}"><h1>&#62;</h1></a>
                </c:if>
              </div>
            </div>
          </div>
          <div class="col-md-3"></div>
        </div>
      </c:if>
    </main>
  </body>
</html>
