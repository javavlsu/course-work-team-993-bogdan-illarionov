<%@page contentType = "text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>Mange lot outcomes</title>
    <jsp:include page="../shared/_head.jsp" />
  </head>
  <body>
    <jsp:include page="../shared/_nav.jsp" />
    <main class="content">

        <br/>
        <div class="row form-group">
            <sec:authorize access="hasAnyAuthority('Player')">
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
                               <td><c:out value="${bet.getOutcome().getLot().getName()}" /></td>
                               <td><c:out value="${bet.getOutcome().getValue()}" /></td>
                               <td><c:out value="${bet.getSum()}" /></td>
                               <td><c:out value="${bet.getBetStatus().toString()}" /></td>

                            </tr>
                        </c:forEach>
                    </tbody>
                   <table>
           </sec:authorize>
        </div>
      </main>
  </body>
</html>
